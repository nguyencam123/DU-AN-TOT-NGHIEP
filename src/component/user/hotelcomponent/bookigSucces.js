import { CheckCircleOutlined, CheckCircleTwoTone } from "@ant-design/icons"
import { Breadcrumb, Button, Col, Row } from "antd"
import { Content, Footer } from "antd/es/layout/layout"
import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import { useNavigate, useParams } from "react-router-dom"
import { addBooking } from "../../../features/product/productThunk"

export const BookingSuccess = () => {
  const dispatch = useDispatch();
  const urlParams = new URLSearchParams(window.location.search)
  const navigate = useNavigate();
  const handleReturn = () => {
    navigate('/')
  }
  const info = urlParams.get('vnp_OrderInfo');
  const name = info?.substring(0, info.indexOf('+'));
  const phoneNumber = info?.substring(info.indexOf('+') + 1, info.indexOf(','));
  const email = info?.substring(info.indexOf(',') + 1, info.lastIndexOf('+'));
  const startDate = info?.substring(info.lastIndexOf('+') + 1, info.lastIndexOf(','));
  const endDate = info?.substring(info.lastIndexOf(',') + 1, info.indexOf('='));
  const homestayId = info?.substring(info.indexOf('=') + 1, info.lastIndexOf('='));
  const userId = info?.substring(info.lastIndexOf('=') + 1);
  console.log(info);
  const booking = {
    userId: userId,
    totalPrice: urlParams.get('vnp_Amount'),
    startDate: startDate,
    endDate: endDate,
    name: name,
    email: email,
    phoneNumber: phoneNumber,
    homestayId: homestayId
  }

  useEffect(() => {
    dispatch(addBooking(booking));
  }, []);
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
          <Row style={{ marginLeft: '44%', marginBottom:'10px' }}><CheckCircleOutlined style={{ marginTop: '70px', fontSize: '100px', color: 'green' }} /></Row>
          <Row style={{marginLeft:'210px',marginBottom:'20px'}}>
            <h4>Thanh toán thành công</h4>
          </Row>
            <Row style={{marginLeft:'100px',marginBottom:'20px'}}>
              <div style={{fontSize:'20px'}}>
                Bạn có thể xem chi tiết booking trong <a style={{color:'blue'}}>đơn hàng của tôi</a>
              </div>
            </Row>
            <Row style={{marginLeft:'180px', marginBottom:'30px'}}>
              <div style={{fontSize:'20px'}}>Cảm ơn bản đã tin tưởng TraivelVivu</div>
          </Row>
          <Row style={{marginLeft:'185px'}}>
              <Button style={{fontSize:'20px', width:'300px', height:'50px',fontWeight:'700', backgroundColor:'lightblue', color:'white'}} onClick={() => handleReturn()}>Trở về trang chủ</Button>
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