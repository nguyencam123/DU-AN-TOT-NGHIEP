import { EditTwoTone, EyeOutlined, EyeTwoTone } from "@ant-design/icons";
import { Input, Row, Select, Table, Typography, Form, Space, Button, Modal } from "antd";
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
      dataIndex: 'startDate',
      key: 'startDate',
      render: (data) => {
        return moment(data).locale('vi').format('LL')
      }
    },
    {
      title: 'Ngày hủy',
      dataIndex: 'startDate',
      key: 'startDate',
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
      title: 'Phương thức booking',
      dataIndex: 'typeBooking',
      key: 'typeBooking',
      render: (data) => {
        if (data === 'DAT_COC') {
          return 'Đặt cọc'
        }
        if (data === 'THANH_TOAN_TRUOC') {
          return 'Đã thanh toán'
        }
      }
    },
    {
      title: 'Tổng tiền',
      dataIndex: 'totalPrice',
      key: 'totalPrice'
    },
    {
      title: 'Số tiền phải chuyển cho chủ homestay',
      dataIndex: 'totalPrice',
      key: 'price',
      render: (data, record) => {
        if (record.typeBooking === 'DAT_COC') {
          return (data - (data-(data * 100/111))*2)
        }
        if (record.typeBooking === 'THANH_TOAN_TRUOC') {
          return (data * 100 / 111)
        }
      }
    },
    {
      title: 'Số tiền phải chuyển cho khách hàng',
      dataIndex: 'totalPrice',
      key: 'price',
      render: (data, record) => {
        if (record.status === 'HUY') {
          if (record.typeBooking === 'DAT_COC') {
            return (data - (data - (data * 100 / 111)) * 2)
          }
          if (record.typeBooking === 'THANH_TOAN_TRUOC') {
            return (data * 100 / 111)
          }
        }
      }
    },
    {
      title: 'Mã giao dịch với chủ homestay',
      dataIndex: 'adminTransactionCode',
      key: 'adminTransactionCode'
    },
    {
      title: 'Mã giao dịch booking hủy',
      dataIndex: 'customerTransactionCode',
      key: 'customerTransactionCode'
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size='middle'>
          <a onClick={() => showBooking(record)}><EyeTwoTone /></a>
          <a><EditTwoTone /></a>
        </Space>
      )
    }
  ];
  const listFilter = [
    {
      name: 'Tất cả',
      value: '4'
    },
    {
      name: 'Hủy',
      value: 0
    },
    {
      name: 'Thành công',
      value: 1
    }
  ]
  const [selectedStatus, setSelectedStatus] = useState({
    name: 'Tất cả',
    value: '4'
  })
  const [isViewModal, setIsViewModal] = useState(false)
  const [viewBooking, setViewBooking] = useState({})
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
    } else {
      dispatch(getBooking(1));
      setSelectedStatus({
        name: 'Tất cả',
        value: '4'
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
  const showBooking = (booking) => {
    setIsViewModal(true)
    setViewBooking(booking)
  }
  useEffect(() => {
    dispatch(getBooking(1));
  }, []);
  const booking = useSelector((state) => state.admin.booking)
  return (
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

      <Modal
        title={<div style={{ fontSize: '22px' }}>Xem thông tin chi tiết booking</div>}
        open={isViewModal}
        onCancel={() => setIsViewModal(false)}
        footer={[
          <Button key="back" onClick={() => setIsViewModal(false)}>
            Cancel
          </Button>,
        ]}
        width={1100}
        style={{ fontSize: '40px' }}
      >
        <div style={{ fontSize: 18, fontWeight: 600 }}>
          <table>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên homestay </div> : {viewBooking?.name}</div><br /></td>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên chủ homestay </div> : {viewBooking?.phoneNumber}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên homestay </div> : {viewBooking?.homestay?.name}</div><br /></td>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Tên chủ homestay </div> : {viewBooking?.homestay?.ownerHomestay?.name}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Giá            </div> : {viewBooking.totalPrice} (VNĐ)</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Số đêm </div> : {viewBooking.numberOfNight} (Đêm)</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Số phòng</div> : {viewBooking.roomNumber}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Chính sách hủy phòng </div> : {viewBooking?.homestay?.cancellationPolicy}</div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}>
                <div style={{ width: 200 }}>Ngày bắt đầu</div> : {moment(viewBooking.startDate).locale('vi').format('LL')}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}>
                <div style={{ width: 200 }}>Ngày kết thúc</div> : {moment(viewBooking.endDate).locale('vi').format('LL')}
              </div><br /></td>
            </tr>
            <tr>
              <td style={{ width: 600 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Mã giao dịch </div> : {viewBooking.customerTransactionCode}</div><br /></td>
              <td style={{ width: 500 }}><div style={{ display: 'flex' }}><div style={{ width: 200 }}>Mã giao dịch với chủ homestay         </div> : {viewBooking.adminTransactionCode}</div><br /></td>
            </tr>
          </table>
          <div style={{ display: 'flex' }}><div style={{ width: 200 }}>Hình thức đặt</div> : {viewBooking.typeBooking === 'DAT_COC' ? 'Đặt cọc' : 'Thanh toán trước'}</div><br />
          <div style={{ display: 'flex' }}><div style={{ width: 200 }}>Trạng thái       </div> : {viewBooking.status}</div><br />
        </div>
      </Modal>
    </div>
  )
}
export default BookingForm

