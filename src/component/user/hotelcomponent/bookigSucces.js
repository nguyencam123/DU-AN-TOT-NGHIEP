import { CheckCircleOutlined, CheckCircleTwoTone } from "@ant-design/icons"
import { Breadcrumb, Button, Col, Row, message } from "antd"
import { Content, Footer } from "antd/es/layout/layout"
import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import { useNavigate, useParams } from "react-router-dom"
import { addBooking, checkBooked, sendBill, updateBooking } from "../../../features/product/productThunk"

export const BookingSuccess = () => {
  const dispatch = useDispatch();
  const urlParams = new URLSearchParams(window.location.search)
  const userDetail = JSON.parse(localStorage.getItem('userDetail'));
  const userID = userDetail?.data.id;
  const navigate = useNavigate();
  const handleReturn = () => {
    navigate('/')
  }

  useEffect(() => {
    dispatch(updateBooking(bookingId));
    dispatch(sendBill(bookingId));
    dispatch(checkBooked());
  }, 5);
  const info = urlParams.get('vnp_OrderInfo');
  const bookingId = urlParams.get('bookingId');


  const handleBooking = () => {
    navigate(`/booking/${userID}`)
  }
  return (
    <>
      <Content
        className="site-layout"
        style={{
          padding: '0 100px',
          marginTop: '30px',
          color: 'black'
        }}
      >

        <div style={{ backgroundColor: 'white', width: '50%', height: '500px', marginLeft: '25%', marginTop: '100px', marginBottom: '100px' }}>
          <Row style={{ display: 'flex', justifyContent: 'center', marginBottom: '10px' }}><CheckCircleOutlined style={{ marginTop: '70px', fontSize: '100px', color: 'green' }} /></Row>
          <Row style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
            <h4>Thanh toán thành công</h4>
          </Row>
          <Row style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
            <div style={{ fontSize: '20px' }}>
              Bạn có thể xem chi tiết booking trong <a style={{ color: 'blue' }} onClick={() => handleBooking()}>đơn hàng của tôi</a>
            </div>
          </Row>
          <Row style={{ display: 'flex', justifyContent: 'center', marginBottom: '30px' }}>
            <div style={{ fontSize: '20px' }}>Cảm ơn bản đã tin tưởng TraivelVivu</div>
          </Row>
          <Row style={{ display: 'flex', justifyContent: 'center', }}>
            <Button style={{ fontSize: '20px', width: '300px', height: '50px', fontWeight: '700', backgroundColor: 'lightblue', color: 'white' }} onClick={() => handleReturn()}>Trở về trang chủ</Button>
          </Row>
        </div>
      </Content>

      <Footer
        style={{
          textAlign: 'center',
        }}
      >
      </Footer>
    </>
  )

}