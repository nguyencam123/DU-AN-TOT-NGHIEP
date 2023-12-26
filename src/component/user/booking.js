import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Form, Image, Input, Modal, Rate, Row, Select, Space, Table, Typography, message } from "antd";
import { CloseOutlined, CompassOutlined, DeleteOutlined, EyeOutlined, LoadingOutlined, RotateLeftOutlined, RotateRightOutlined, ShopOutlined, SwapOutlined, ZoomInOutlined, ZoomOutOutlined } from "@ant-design/icons";
import { useNavigate, useParams } from "react-router-dom";
import moment from 'moment';
import dayjs from 'dayjs';
import { fetchBookingUserId } from "../../features/admin/adminThunk";
import { cancelBooking } from "../../features/product/productThunk";
import TextArea from "antd/es/input/TextArea";
import * as Yup from 'yup';
import { MDBCardImage } from "mdb-react-ui-kit";
import { addCommentByUser } from "../../features/owner_homestay/getbooking/commentThunk";

dayjs.locale('vi');
const { Title } = Typography;
const { Search } = Input;

const desc = ['Tệ', 'Không hài lòng', 'Bình thường', 'Hài lòng', 'Tuyệt vời'];

export const BookingUser = () => {
  const { id } = useParams();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchBookingUserId(id));
  }, []);
  const [isViewmodal, setIsviewmodal] = useState(false);
  const [isRefusalModal, setIsRefusalModal] = useState(false);
  const [isCommentModel, setIsCommentModel] = useState(false);
  const [node, setNode] = useState('');
  const [bookingDeatil, setBookingDeatil] = useState({});
  const [formErrors, setFormErrors] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [file, setFile] = useState([]);
  const [selectedImages, setSelectedImages] = useState([]);
  const [value, setValue] = useState(3);
  const [comment, setComment] = useState('');
  const [commentError, setCommentError] = useState('');
  const [commentErrorText, setCommentErrorText] = useState('');
  // const [Rate,setRate]=useState(5)
  const userDetail = JSON.parse(localStorage.getItem('userDetail'));
  const UserID = userDetail?.data.id;

  const handleFileChange = (event) => {
    const files = event.target.files;
    let selectedFile = event.target.files;
    let fileList = [...selectedFile];
    const imagesArray = fileList.map((file) => ({
      name: file.name,
      url: URL.createObjectURL(file),
    }));

    setSelectedImages((prevImages) => [...prevImages, ...imagesArray]);
    setFile(fileList)
    const filesArray = Array.from(files).map((file) => ({
      name: file.name,
      size: file.size,
      type: file.type,
    }));
  };

  const handleRemoveImage = (index) => {
    const newImages = [...selectedImages];
    file.splice(index, 1);
    newImages.splice(index, 1);
    setSelectedImages(newImages);
    // document.getElementById('image').value = null;
  };
  const showModalView = (booking) => {
    setBookingDeatil(booking)
    setIsviewmodal(true);
  };
  const showModalComment = (booking) => {
    setBookingDeatil(booking)
    setIsCommentModel(true)
    console.log(booking)
  }
  const showRefusalView = (booking) => {
    setBookingDeatil(booking)
    setIsRefusalModal(true);
  };
  const handleCancel = () => {
    setIsRefusalModal(false)
    setIsviewmodal(false);
    setIsCommentModel(false);
  };
  const calculateDate = (createdDate) => {
    return dayjs(createdDate).add(1, 'day')
  };
  /**
   * validate
   */

  const handleCancelBooking = async () => {
    if (!node || node.length <= 20) {
      setFormErrors('bạn cần nhập lý do từ chối và lý do nhiều hơn 20 ký tự')
    } else {
      setIsLoading(true)
      await message.info(
        'Đang tiến hành hủy bạn vui lòng đợi một vài giây nhé!', 5
      );
      await dispatch(cancelBooking(bookingDeatil.id, node));
      message.info(
        'Hủy thành công', 5
      );
      setIsLoading(false)
      setIsRefusalModal(false)
      dispatch(fetchBookingUserId(id));
    }
  }
  /**
   * 
   * @param {Comment} e 
   */
  const handleComment = () => {
    setIsCommentModel(true)
  }
  const handleChangeNote = (e) => {
    setNode(e.target.value)
  }
  const addComment = async () => {
    if (file.length < 1) {
      setCommentError('Bạn cần chọn ít nhất 1 file')
    } else if (!comment) {
      setCommentErrorText('Bạn cần nhập đánh giá')
    } else {
      setIsLoading(true)
      message.info(
        'Đang tiến hành đánh giá bạn vui lòng đợi một vài giây nhé!', 5
      );
      await dispatch(addCommentByUser(bookingDeatil.homestay.id, comment, UserID, file, value));
      message.info(
        'Đánh giá thành công!'
      );
      setIsLoading(false)
    }
  }

  const booking = useSelector((state) => state.admin.booking)

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
          {booking.map((booking) => {
            const currentDate = new Date().getTime();
            const startDate = booking?.startDate?.valueOf();
            const endDate = booking?.endDate?.valueOf();
            return (
              <div style={{
                width: '100%', height: 210,
                backgroundColor: '#ffffff', borderRadius: 8,
                boxShadow: '0 0 3px 1px #ACAEB1', marginTop: 20,
                padding: '2px 2px 2px 2px', display: 'flex'
              }}>
                <div style={{ width: '30%' }}>
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
                <div style={{ marginLeft: 10, borderLeft: '1px solid #ACAEB1', padding: '8px 8px 2px 15px', width: '25%' }}>
                  <div style={{ display: 'flex', color: 'rgb(5, 165, 105)' }}>{booking.status === 'HUY' ? 'Homestay đã hủy' : 'Đã thanh toán'}</div>
                  <div >
                    <div style={{ fontSize: 22, color: 'rgb(231, 9, 14)' }}>{booking.totalPrice} VNĐ</div>
                    <div style={{ fontSize: 22 }}>
                      {booking.status === 'HUY'
                        ? ''
                        : <Button style={{ backgroundColor: 'rgb(231, 9, 14)', color: 'white', width: 180 }} onClick={() => showRefusalView(booking)} >Hủy homestay</Button>
                      }
                      <Button style={{ backgroundColor: 'green', color: 'white', width: 180 }} onClick={() => showModalView(booking)}>Xem chi tiết homestay</Button>
                      {currentDate > endDate + 1 ?
                        <Button style={{ backgroundColor: 'green', color: 'white', width: 180 }} onClick={() => showModalComment(booking)}>Đánh giá homestay</Button>
                        : <div />}
                    </div>
                  </div>
                </div>
              </div>
            )
          })}
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
        maskClosable={false}
        okButtonProps={
          isLoading
            ? {
              disabled: true,
              icon: <LoadingOutlined />,
              loading: true,
            }
            : {}
        }
        cancelButtonProps={
          isLoading
            ? {
              disabled: true,
            }
            : {}
        }
      >
        <Form>
          <Form.Item
            label='Lý do từ chối'
            name='node'
            validateStatus={formErrors ? 'error' : ''}
            help={formErrors}
          >
            <TextArea onChange={(data) => handleChangeNote(data)} />
          </Form.Item>
        </Form>
      </Modal>
      <Modal
        title='Đánh giá homestay'
        open={isCommentModel}
        onOk={() => addComment()}
        onCancel={() => handleCancel()}
        maskClosable={false}
        width={900}
        okButtonProps={
          isLoading
            ? {
              disabled: true,
              icon: <LoadingOutlined />,
              loading: true,
            }
            : {}
        }
        cancelButtonProps={
          isLoading
            ? {
              disabled: true,
            }
            : {}
        }
      >
        <Form>
          <div>
            <Title level={5}>Phân loại sản phẩm</Title>
            <div style={{ display: 'flex', marginBottom: 20 }}>
              <MDBCardImage style={{ width: 60 }} src={bookingDeatil.homestay?.images[0].imgUrl} position="top" alt="..." />
              <Title level={5}>&emsp;{bookingDeatil.homestay?.name}</Title>

            </div>
          </div>
          <hr />
          <Form.Item
            label='Đánh giá'
            name='comment'
            validateStatus={commentErrorText ? 'error' : ''}
            help={commentErrorText}
          >
            <TextArea onChange={(e) => setComment(e.target.value)} />
          </Form.Item>
          <div style={{ marginTop: 20 }}>
            <Space>
              <div>Chất lượng sản phẩm</div>
              <Rate tooltips={desc} onChange={setValue} value={value} />
              {value ? <span>{desc[value - 1]}</span> : ''}
            </Space>
            <br /><br />
            <div style={{ display: 'flex' }}>
              <label htmlFor='image' style={{ marginTop: 5 }}>Tải các hình ảnh mà bạn được trải nghiệm ở homestay </label>&ensp;
              <label htmlFor='image' style={{ cursor: 'pointer', border: '1px solid black', borderRadius: 8, padding: '6px 15px 6px 15px' }}>
                Chọn tệp
              </label>
              <input
                type='file'
                id='image'
                multiple
                accept='image/*'
                onChange={handleFileChange}
                style={{ display: 'none' }}
              />
              <div style={{ marginLeft: 8, marginTop: 5 }}>Đã có {selectedImages.length} file được chọn</div>
            </div>
            <div style={{ color: 'red' }}>{commentError}</div>
          </div>
          <div>
            {selectedImages.map((image, index) => (
              <div key={index} style={{ display: 'flex', marginTop: 10, border: '1px solid black', borderRadius: 8, width: '100%', height: 100, padding: 5 }}>
                <img src={image.url} alt={`selected-${index}`} style={{ border: '1px solid white', borderRadius: 8 }} />
                <div style={{ display: 'flex', justifyContent: 'center', marginTop: 30 }}>{`File Name: ${image.name}`}</div>
                <div style={{ marginLeft: 'auto', display: 'flex', justifyContent: 'center', marginRight: 20, marginTop: 30 }}>
                  <DeleteOutlined onClick={() => handleRemoveImage(index)} />
                </div>
              </div>
            ))}
          </div>
        </Form>
      </Modal>
    </div >
  )
}