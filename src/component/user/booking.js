import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Form, Input, Rate, Row, Select, Space, Table, Typography } from "antd";
import { CloseOutlined, CompassOutlined, EyeOutlined } from "@ant-design/icons";
import { useNavigate, useParams } from "react-router-dom";
import moment from 'moment';
import { fetchBookingUserId } from "../../features/admin/adminThunk";
const { Title } = Typography;
const { Search } = Input;


export const BookingUser = () => {
  const { id } = useParams();
  console.log(id);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchBookingUserId(id));
  }, []);
  const booking = useSelector((state) => state.admin.booking)
  console.log(booking);
  const columns = [
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
      key: 'createdDate',
      render: (data) => {
        return moment(data).locale('vi').format('LL')
      }
    },
    {
      title: 'Ngày kêt thúc',
      dataIndex: 'endDate',
      key: 'endDtae',
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
      title: 'Tổng tiền',
      dataIndex: 'totalPrice',
      key: 'totalPrice'
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a style={{ color: '#1677ff' }}><CloseOutlined /></a>
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
  return (
    <div style={{ marginTop: '30px' }}>
      <Title level={2}>Homestay bạn đăng đặt</Title>
      <Title level={4}>Danh mục</Title>  
      <Table columns={columns} dataSource={booking} />
    </div>
  )
}