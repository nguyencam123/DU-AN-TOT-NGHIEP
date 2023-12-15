import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Form, Image, Input, Modal, Rate, Row, Select, Space, Table, Typography } from "antd";
import { CloseOutlined, CompassOutlined, EyeOutlined, RotateLeftOutlined, RotateRightOutlined, ShopOutlined, SwapOutlined, ZoomInOutlined, ZoomOutOutlined } from "@ant-design/icons";
import { useNavigate, useParams } from "react-router-dom";
import moment from 'moment';
import dayjs from 'dayjs';
import { fetchBookingUserId } from "../../features/admin/adminThunk";
import { cancelBooking } from "../../features/product/productThunk";
import TextArea from "antd/es/input/TextArea";

dayjs.locale('vi');
const { Title } = Typography;
const { Search } = Input;


export const BookingUser = () => {
  const { id } = useParams();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchBookingUserId(id));
  }, []);
  const [isViewmodal, setIsviewmodal] = useState(false);
  const [isRefusalModal, setIsRefusalModal] = useState(false);
  const [node, setNode] = useState('');
  const [bookingDeatil, setBookingDeatil] = useState({});

  const showModalView = (booking) => {
    setBookingDeatil(booking)
    setIsviewmodal(true);
  };
  const showRefusalView = (booking) => {
    setBookingDeatil(booking)
    setIsRefusalModal(true);
  };
  const handleCancel = () => {
    setIsRefusalModal(false)
    setIsviewmodal(false);
  };
  const calculateDate = (createdDate) => {
    return dayjs(createdDate).add(1, 'day')
  };
  const handleCancelBooking = () => {
    dispatch(cancelBooking(bookingDeatil.id, node));
    setIsRefusalModal(false)
    dispatch(fetchBookingUserId(id));
  }
  const handleChangeNote = (e) => {
    setNode(e.target.value)
  }
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
    <div
      className="site-layout"
      style={{
        padding: '0 100px',
        marginTop: '30px',
        color: 'black'
      }}
    >
      <div
        style={{
          paddingRight: 100,
          paddingBottom: 50,
          paddingLeft: 100,
          minHeight: 380

        }}
      >
        <div style={{ marginTop: '30px' }}>
          <Title level={2}>Homestay bạn đăng đặt</Title>
          <Title level={4}>Danh mục</Title>
          {booking.map((booking) => (
            <div style={{
              width: '100%', height: 210,
              backgroundColor: '#ffffff', borderRadius: 8,
              boxShadow: '0 0 3px 1px #ACAEB1', marginTop: 20,
              padding: '2px 2px 2px 2px', display: 'flex'
            }}>
              <div style={{ width: '60%' }}>
                <img src={booking.homestay.images[0]?.imgUrl} style={{ borderRadius: 8, height: 150, width: 255 }} />
                <div style={{ marginTop: 8, display: 'flex' }}>
                  <img src={booking.homestay.images[1]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                  <img src={booking.homestay.images[2]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                  <img src={booking.homestay.images[3]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                </div>
              </div>
              <div style={{ width: '50%', marginRight: 50 }}>
                <h1 style={{ fontSize: 18, marginTop: 10, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', width: 250 }}>Homestay : {booking.homestay?.name}</h1>
                <Rate allowHalf disabled defaultValue={booking.homestay.star} size='sm' /><br />
                <div style={{ display: 'flex', marginTop: 10 }}>
                  <Title style={{ fontSize: 16, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', width: 250, marginTop: 3 }}>Đia chỉ : {booking.homestay?.address}</Title></div>
                <h1 style={{ width: '100%', height: 20, backgroundColor: 'rgb(242, 243, 243)', borderRadius: 8, fontSize: 14, padding: '0 2px 0 2px' }}>
                  Ngày nhận phòng : {moment(booking?.startDate).locale('vi').format('LL')}
                </h1>
                <h1 style={{ width: '100%', height: 20, backgroundColor: 'rgb(242, 243, 243)', borderRadius: 8, fontSize: 14, padding: '0 2px 0 2px' }}>
                  Ngày trả phòng : {moment(booking?.endDate).locale('vi').format('LL')} <br />
                </h1>
              </div>
              <div style={{ marginLeft: 10, borderLeft: '1px solid #ACAEB1', padding: '8px 8px 2px 2px', width: '40%' }}>
                <div style={{ display: 'flex', color: 'rgb(5, 165, 105)' }}><ShopOutlined style={{ marginTop: 3, fontSize: 14 }} /> Ưu đãi dành riêng cho bạn...</div>
                <div style={{ marginLeft: '10px' }}>
                  <div style={{ fontSize: 22, color: 'rgb(231, 9, 14)' }}>{booking.homestay.price + booking.homestay.price * 11 / 100} VND</div>
                  <div style={{ fontSize: 22 }}>
                    <Button style={{ backgroundColor: 'rgb(231, 9, 14)', color: 'white' }} onClick={() => showRefusalView(booking)} >Hủy homestay</Button>
                    <Button style={{ backgroundColor: 'green', color: 'white' }} onClick={() => showModalView(booking)}>Xem chi tiết homestay</Button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

      <Modal
        title={
          <div style={{ fontSize: '22px' }}>Xem thông tin chi tiết homstay</div>
        }
        open={isViewmodal}
        onCancel={handleCancel}
        onOk={handleCancel}
        width={1100}
        style={{ fontSize: '40px' }}
      >
        <div style={{ fontSize: 18, fontWeight: 600 }}>
          <table>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Tên homestay </div> : {bookingDeatil?.homestay?.name}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Diện tích phòng</div> : {bookingDeatil?.homestay?.acreage}{' '}
                  (m2)
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Giá </div> : {bookingDeatil?.homestay?.price} (VNĐ)
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Số lượng người </div> :{' '}
                  {bookingDeatil?.homestay?.numberPerson} (Người)
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Số phòng</div> : {bookingDeatil?.homestay?.roomNumber}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Chính sách hủy phòng </div> :{' '}
                  {bookingDeatil?.homestay?.cancellationPolicy}
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Thời gian nhận phòng </div> :{' '}
                  {bookingDeatil?.homestay?.timeCheckIn}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Thời gian trả phòng </div> :{' '}
                  {bookingDeatil?.homestay?.timeCheckOut}
                </div>
                <br />
              </td>
            </tr>
            <tr>
              <td style={{ width: 600 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Ngày bắt đầu</div> :{' '}
                  {moment(bookingDeatil?.homestay?.startDate).locale('vi').format('LL')}
                </div>
                <br />
              </td>
              <td style={{ width: 500 }}>
                <div style={{ display: 'flex' }}>
                  <div style={{ width: 200 }}>Ngày kết thúc</div> :{' '}
                  {moment(bookingDeatil?.homestay?.endDate).locale('vi').format('LL')}
                </div>
                <br />
              </td>
            </tr>
          </table>
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Tiện ích </div> :{' '}
            {bookingDeatil?.homestay?.detailHomestays?.map((items) => (
              <div> {items.convenientHomestay?.name},</div>
            ))}
          </div>
          <br />
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Mô tả </div> : {bookingDeatil?.homestay?.desc}
          </div>
          <br />
          <div style={{ display: 'flex' }}>
            <div style={{ width: 200 }}>Địa chỉ </div> : {bookingDeatil?.homestay?.address}
          </div>
          <br />
          {
            Date.now() >= calculateDate(bookingDeatil?.createdDate) ?
              <div style={{ display: 'flex' }}>
                <div style={{ width: 200 }}>Số điện thoại liên lạc </div> : {bookingDeatil?.homestay?.ownerHomestay?.phoneNumber}
              </div>
              : ''
          }
          <div>
            Ảnh homstay :<br />
            <div
              style={{
                width: 1030,
                padding: 20,
                flexWrap: 'wrap',
                borderRadius: 10,
                display: 'flex',
                justifyContent: 'center',
                border: '1px solid black'
              }}
            >
              {bookingDeatil?.homestay?.images?.map((imageurl, index) => (
                <Image
                  key={index}
                  src={imageurl.imgUrl}
                  alt={`Homestay Image ${index}`}
                  style={{
                    maxWidth: '200px', // Đảm bảo ảnh không vượt quá chiều rộng của phần tử cha
                    margin: '0 10px 10px 0' // Thêm khoảng cách giữa các ảnh
                  }}
                  preview={{
                    toolbarRender: (
                      _,
                      {
                        transform: { scale },
                        actions: {
                          onFlipY,
                          onFlipX,
                          onRotateLeft,
                          onRotateRight,
                          onZoomOut,
                          onZoomIn
                        }
                      }
                    ) => (
                      <Space className='toolbar-wrapper'>
                        <SwapOutlined rotate={90} onClick={onFlipY} />
                        <SwapOutlined onClick={onFlipX} />
                        <RotateLeftOutlined onClick={onRotateLeft} />
                        <RotateRightOutlined onClick={onRotateRight} />
                        <ZoomOutOutlined
                          disabled={scale === 1}
                          onClick={onZoomOut}
                        />
                        <ZoomInOutlined
                          disabled={scale === 50}
                          onClick={onZoomIn}
                        />
                      </Space>
                    )
                  }}
                />
              ))}
            </div>
          </div>
        </div>
      </Modal>

      <Modal
        title='Hủy Homestay'
        open={isRefusalModal}
        onOk={() => handleCancelBooking()}
        onCancel={() => handleCancel()}
      >
        <Form>
          <Form.Item
            label='Lý do từ chối'
            name='refusalReason'
            rules={[
              {
                required: true,
                message: 'Vui lòng điền lý do từ chối'
              }
            ]}
          >
            <TextArea onChange={(data) => handleChangeNote(data)} />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  )
}