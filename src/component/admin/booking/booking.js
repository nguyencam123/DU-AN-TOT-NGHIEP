import { EditTwoTone, EyeOutlined, EyeTwoTone } from "@ant-design/icons";
import { Input, Row, Select, Table, Typography, Form, Space } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { getBooking, getBookingByName, getBookingByNameHomestay, getBookingByPhoneNumber } from "../../../features/admin/adminThunk";
import { useEffect, useState } from "react";
import moment from 'moment';


const { Title } = Typography;
const { Search } = Input;


function BookingForm() {
  const dispatch = useDispatch();
  const columns = [
    {
      title: 'Tên tài khoản',
      dataIndex: 'user',
      key: 'userName',
      render: (data) => {
        return data.name
      }
    },
    {
      title: 'Tên Homestay',
      dataIndex: 'homestay',
      key: 'homestayName',
      render: (data) => {
        return data.name
      }
    },
    {
      title: 'Ngày đặt',
      dataIndex: 'createdDate',
      key: 'createdDate',
      render: (data) => {
        return moment(data).locale('vi').format('LL')
      }
    },
    {
      title: 'Số đêm',
      dataIndex: 'numNight',
      key: 'numNight'
    },
    {
      title: 'Trạng thái',
      dataIndex: 'status',
      key: 'status',
      render: (data) => {
        if (data === 'HUY') {
          return 'Hủy'
        }
        if (data === 'THANH_CONG') {
          return 'Thành công'
        }
        if (data === 'KHONG_THANH_CONG') {
          return 'Không thành công'
        }
      }
    },
    {
      title: 'Hình thức thanh toán',
      dataIndex: 'typeBooking',
      key: 'phoneNumber',
      render: (data) => {
        if (data === 'DAT_COC') {
          return 'Đặt cọc'
        }
        if (data === 'THANH_TOAN_TRUOC') {
          return 'Thanh toán'
        }
      }
    },
    {
      title: 'Tổng tiền',
      dataIndex: 'totalPrice',
      key: 'totalPrice'
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size='middle'>
          <a><EyeTwoTone /></a>
          <a><EditTwoTone /></a>
        </Space>
      )
    }
  ];
  const listFilter = [
    {
      name: 'Tất cả',
      value: ''
    },
    {
      name: 'Hủy',
      value: 0
    },
    {
      name: 'Thành công',
      value: 1
    },
    {
      name: 'Không thành công',
      value: 2
    }
  ]
  const [selectedStatus, setSelectedStatus] = useState({
      name: 'Tất cả',
      value: ''
  })
  const handleChangeStatus = (value) => {
    if (value === 1) {
      setSelectedStatus({
        name: 'Thành công',
        value: 1
      })
      dispatch(getBooking(1));
    } else if (value === 0) {
      setSelectedStatus({
        name: 'Hủy',
        value: 0
      })
      dispatch(getBooking(0));
    } else if (value === 2) {
      setSelectedStatus({
        name: 'Không thành công',
        value: 2
      })
      dispatch(getBooking(2));
    } else {
      dispatch(getBooking());
      setSelectedStatus({
        name: 'Tất cả',
        value: ''
      })
    }
  }
  const searchByNameHomestay = (value, _e, info) => {
    dispatch(getBookingByNameHomestay(value));
  }
  const searchByNameBooking = (value, _e, info) => {
    dispatch(getBookingByName(value));
  }
  const searchByPhoneNumber = (value, _e, info) => {
    dispatch(getBookingByPhoneNumber(value));
  }
  useEffect(() => {
    dispatch(getBooking(1));
  }, []);
  const booking = useSelector((state) => state.admin.booking)
    return(
  <div style={{ marginTop: '30px' }}>
          <Title level={2}>Quản trị booking</Title>
      <Title level={4}>Danh mục</Title>
      <Row>
        <Form.Item label="Trạng thái" style={{ float: 'left' }}>
          <Select
            style={{ width: 143 }}
              options={listFilter.map(filter => ({ value: filter.value, label: filter.name }))}
              defaultValue={selectedStatus.value}
              onChange={handleChangeStatus}
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên" style={{ float: 'left', marginLeft: ' 50px' }}>
          <Search
            placeholder="Tên homestay"
            allowClear
            size="medium"
              enterButton="search"
              onSearch={searchByNameHomestay}
          />
          </Form.Item>
          <Form.Item label="Tìm kiếm theo số điện thoại liên lạc" style={{ float: 'left', marginLeft: ' 20px' }}>
          <Search
            placeholder="Số điện thoại"
            allowClear
            size="medium"
              enterButton="search"
              onSearch={searchByPhoneNumber}
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên người liên lạc" style={{ float: 'left', marginLeft: ' 0px' }}>
          <Search
            placeholder="Tên người liên lạc"
            allowClear
            size="medium"
              enterButton="search"
              onSearch={searchByNameBooking}
          />
        </Form.Item>
      </Row>
          <Table columns={columns} dataSource={booking} />
    </div>
    )
}
export default BookingForm

