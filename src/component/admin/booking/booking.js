import { EyeOutlined } from "@ant-design/icons";
import { Input, Row, Select, Table, Typography, Form, Space } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { getBooking } from "../../../features/admin/adminThunk";
import { useEffect } from "react";
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
      title: 'Email người đặt',
      dataIndex: 'email',
      key: 'email'
    },
    {
      title: 'Tên người đặt',
      dataIndex: 'name',
      key: 'name'
    },
    {
      title: 'Số điện thoại người đặt',
      dataIndex: 'phoneNumber',
      key: 'createdDate'
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
        <Space size="middle">
          <a style={{ color: '#1677ff' }}><EyeOutlined /></a>
        </Space>
      ),
    },
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
  useEffect(() => {
    dispatch(getBooking());
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
              defaultValue={listFilter[0].value}
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên" style={{ float: 'left', marginLeft: ' 50px' }}>
          <Search
            placeholder="Tên homestay"
            allowClear
            size="medium"
            enterButton="search"
          />
        </Form.Item>
        <Form.Item label="Tìm kiếm theo tên chủ homestay" style={{ float: 'left', marginLeft: ' 50px' }}>
          <Search
            placeholder="Tên chủ homestay"
            allowClear
            size="medium"
            enterButton="search"
          />
        </Form.Item>
      </Row>
          <Table columns={columns} dataSource={booking} />
    </div>
    )
}
export default BookingForm

