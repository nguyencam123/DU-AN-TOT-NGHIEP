
import React, { useEffect } from 'react';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Rate, Button, Image, Progress, Space } from 'antd';
import { ClockCircleTwoTone, EnvironmentOutlined, FileTextTwoTone, InfoCircleTwoTone, StarTwoTone } from '@ant-design/icons'
import { Form, Table } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';
import { getOneProduct } from '../../../features/product/productThunk';
import { useDispatch, useSelector } from 'react-redux';
const { Header, Content, Footer } = Layout;


export const BookingReviewHomestay = () => {
  const params = useParams();
  useEffect(() => {
    dispatch(getOneProduct(params.id));
  }, []);
  const dispatch = useDispatch();
  const detailHomestay = useSelector((state) => state.product.productDetails);
  const navigate = useNavigate();
  const handleReviewBookingHomestay = (id) => {
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
        <Breadcrumb
          style={{
            margin: '-20px 24px',
            fontSize: '12px'
          }}
        >
          <Breadcrumb.Item>Homestay</Breadcrumb.Item>
          <Breadcrumb.Item>Review booking</Breadcrumb.Item>
          <Breadcrumb.Item>{detailHomestay.name}</Breadcrumb.Item>
        </Breadcrumb>
        <div
          style={{
            paddingTop: 50,
            paddingRight: 100,
            paddingBottom: 50,
            paddingLeft: 100,
            minHeight: 380

          }}
        >
          <Row>
            <Col span={24}>
              <h3 style={{ fontWeight: '700' }}>
                Bạn vui lòng kiểm tra lại đặt chỗ
              </h3>
              <div style={{ color: 'rgb(104, 113, 118)', fontSize: '16px', fontWeight: '700' }}>
                Vui lòng xem lại chi tiết đặt phòng của bạn trước khi tiếp tục đến bước thanh toán
              </div>
            </Col>
          </Row>
          <Row style={{ marginTop: '25px' }}>
            <Col span={17} >
              <h5 style={{ fontWeight: '700' }}>
                Homestay
              </h5>
              <div style={{ backgroundColor: 'white', borderRadius: '10px' }}>
                <Row style={{ margin: '10px' }} style={{ borderBottom: '1px solid', padding: '0px 10px 10px 10px', }}>
                  <Col span={5}>
                    <Image
                      src={detailHomestay.images[0].imgUrl}
                      style={{ marginTop: '10px' }}
                      width={128}
                      height={128}
                    />
                  </Col>
                  <Col span={17} push={0} >
                    <Row style={{ borderBottom: '1px solid', paddingBottom: '10px' }}>
                      <Col span={1} style={{ marginTop: '20px' }}>
                        <Image
                          src='https://d1785e74lyxkqq.cloudfront.net/_next/static/v2/6/6aa2fd01a9460e1a71bb0efb713f0212.svg'
                          width={20}
                          height={20}
                        />
                      </Col>
                      <Col span={16} push={0}>
                        <div style={{ marginTop: '15px' }}>
                          <span style={{ marginTop: '20px', color: 'rgb(3, 18, 26)', fontSize: '20px', fontWeight: '700' }}> {detailHomestay.name}</span>
                        </div>
                      </Col>
                      <hr />
                    </Row>
                    <Row>
                      <Col span={8}>
                        <div style={{ color: 'rgb(104, 113, 118)', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}>  Ngày nhận phòng:</div>
                        <div style={{ color: 'black', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}> 9 Nov 2023 </div>
                        <div style={{ color: 'rgb(104, 113, 118)', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}> Từ 14:00</div>
                      </Col>
                      <Col span={8}>
                        <div style={{ color: 'rgb(104, 113, 118)', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}>  Ngày trả phòng:</div>
                        <div style={{ color: 'black', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}> 10 Nov 2023 </div>
                        <div style={{ color: 'rgb(104, 113, 118)', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}> Trước 12:00</div>
                      </Col>
                      <Col span={8}>
                        <div style={{ color: 'rgb(104, 113, 118)', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}>  Số đêm nghỉ</div>
                        <div style={{ color: 'black', fontWeight: '600', lineHeight: '28px', fontSize: '16px' }}> 1 đêm </div>
                      </Col>
                    </Row>
                  </Col>
                </Row>
                <Row>
                  <div style={{ margin: '10px 0px 0px 15px' }}>
                    <div style={{ fontSize: '18px', fontWeight: '600', marginBottom: '10px' }}>
                      Số phòng : 20
                    </div>
                    <div>
                      <Image
                        src={detailHomestay.images[1].imgUrl}
                        width={70}
                        height={70}
                      />
                      <Image
                        src={detailHomestay.images[2].imgUrl}
                        width={70}
                        height={70}
                      />
                      <Image
                        src={detailHomestay.images[3].imgUrl}
                        width={70}
                        height={70}
                      />
                    </div>
                  </div>
                </Row>
              </div>
            </Col>
            <Col span={6} push={1} style={{ marginTop: '8px' }}>
              <div style={{ backgroundColor: 'white', borderRadius: '10px' }}>
                <Row style={{ marginTop: '25px' }}>
                  <Col span={22} push={1} style={{ borderBottom: '1px solid' }}>
                    <div style={{ fontSize:'20px', fontWeight:'700'}}>
                      Chi tiết người liên lạc
                    </div>
                  </Col>
                </Row>
                <Col span={22} push={1} style={{marginTop:'5px', paddingBottom:'10px'}}>
                    <div style={{ fontSize:'16px', fontWeight:'600', lineHeight:'28px', color:'rgb(3, 18, 26)'}}>
                      Chi tiết người liên lạc
                  </div>
                  <div style={{ fontSize:'16px', fontWeight:'600', lineHeight:'28px', color:'rgb(3, 18, 26)'}}>
                  +84835120388
                  </div>
                  <div style={{ fontSize:'16px', fontWeight:'600', lineHeight:'28px', color:'rgb(3, 18, 26)'}}>
                  tranquanghuy3103@gmail.com
                  </div>
                  </Col>
              </div>
            </Col>
          </Row>

          <Row style={{ marginTop: '25px' }}>
            <Col span={17}>
              <Row style={{ backgroundColor: 'white', borderRadius: '5px', minHeight: '10px' }}>
                <Col span={6} style={{ backgroundImage: 'linear-gradient(92deg, rgba(81, 149, 227, 0.5) -60%, #D6F1FF 40%, rgba(214, 241, 255, 0.7) 100%)' }}>
                  <h5 style={{ marginTop: '10px', textAlign: 'center' }}>Chính Sách Lưu Trú</h5>
                </Col>
                <Col span={18}>
                  <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                    <div style={{ lineHeight: '12px', marginTop: '3px' }}><ClockCircleTwoTone style={{ fontSize: '12px' }} /> <b>Thời gian nhận/trả Homestay</b></div>
                    <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Giờ nhận phòng: <b>Từ 12:00</b></span>
                    <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Giờ trả phòng: <b>Trước 14:00</b></span>
                  </div>
                  <hr style={{ width: '96%', marginLeft: '2%' }} />
                  <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                    <div style={{ lineHeight: '12px', marginTop: '3px' }}><ClockCircleTwoTone style={{ fontSize: '12px' }} /><b> Chính sách hủy phòng</b></div>
                    <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Việc hủy phòng trước ngày abcxyz sẽ được hoàn toàn miễn phí. Sau ngày abcxyz bạn sẽ phải mất một khoản tiền khi hủy phòng</span>
                  </div>
                  <hr style={{ width: '96%', marginLeft: '2%' }} />
                  <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                    <div style={{ lineHeight: '16px', marginTop: '3px' }}><FileTextTwoTone style={{ fontSize: '12px' }} /><b> Chính sách hủy phòng</b></div>
                    <div style={{ lineHeight: '16px', marginLeft: '17px', }}>Please note that your children might be charged when check-in at the hotel. Please call the hotel before your check-in date for further information.
                      Vui long luu y, tre em co the bi thu them phi khi nhan phong tai khach san. Vui long lien he khach san truoc khi nhan phong de biet them thong tin chi tiet.</div>
                  </div>
                  <hr style={{ width: '96%', marginLeft: '2%' }} />
                  <div style={{ margin: '10px 15px 0px 10px' }}>
                    <div style={{ lineHeight: '12px', margin: '0px 0px 15px 5px' }}><b> Thông tin chung</b></div>
                    <Table striped hover size="sm">
                      <tbody>
                        <tr>
                          <td>Thời gian nhận trả phòng</td>
                          <td>Từ 12:00 - trước 14:00</td>
                        </tr>
                        <tr>
                          <td>Diện tích</td>
                          <td>50m2</td>
                        </tr>
                        <tr>
                          <td>Bữa sáng miễn phí</td>
                          <td>Có</td>
                        </tr>
                        <tr>
                          <td>Bể bơi</td>
                          <td>Có</td>
                        </tr>
                      </tbody>
                    </Table>

                  </div>
                </Col>
              </Row>
              <h6 style={{ fontWeight: '700', marginTop: '10px' }}>
                Chi tiết giá
              </h6>
              <div style={{ backgroundColor: 'white', borderRadius: '10px' }}>
                <Row>
                  <Col span={10}>
                    <div style={{ padding: '20px 0px 5px 20px', fontSize: '18px', fontWeight: '700' }}>
                      Thành tiền
                    </div>
                  </Col>
                  <Col span={8} push={4}>
                    <div style={{ padding: '20px 0px 5px 20px', fontSize: '18px', fontWeight: '700', float: 'right' }}>
                    {detailHomestay.price + detailHomestay.price*11/100} VND
                    </div>
                  </Col>
                </Row>
                <Row style={{ marginTop: '15px' }}>
                  <Col span={1}>
                    <InfoCircleTwoTone style={{ fontSize: '26px', padding: '20px 0px 5px 20px' }} />
                  </Col>
                  <Col span={20} push={1} >
                    <div style={{ paddingTop: '15px', fontWeight: '700', fontSize: '16px', color: 'rgb(1, 148, 243)' }}>
                      Thuế và phí là các khoản được TravelVIVU chuyển trả cho khách sạn. Mọi thắc mắc về thuế và hóa đơn,
                      vui lòng tham khảo Điều khoản và Điều kiện của TravelVIVU để được giải đáp
                    </div>
                    <hr />
                  </Col>
                </Row>
                <Row style={{ padding: '5px 0px 5px 20px' }}>
                  <Col span={10}>
                    <div style={{ fontWeight: '600', fontSize: '18px' }}>
                      Homestay name (1 Đêm)
                    </div>
                  </Col>
                  <Col span={8} push={4}>
                    <div style={{ fontWeight: '600', fontSize: '18px', float: 'right' }}>
                    {detailHomestay.price} VND
                    </div>
                  </Col>
                </Row>
                <Row style={{ padding: '5px 0px 15px 20px' }}>
                  <Col span={10}>
                    <div style={{ fontWeight: '600', fontSize: '18px' }}>
                      Thuế và phí
                    </div>
                  </Col>
                  <Col span={8} push={4}>
                    <div style={{ fontWeight: '600', fontSize: '18px', float: 'right' }}>
                      {detailHomestay.price*11/100} VND
                    </div>
                  </Col>
                </Row>
              </div>
              <Row style={{ padding: '15px 0px 15px 0px' }}>
                <Col span={8}>
                  <div style={{ fontSize: '14px', fontWeight: '500' }}>
                    Khi nhấn vào nút này bạn
                    công nhận mình đã đọc và
                    đồng ý với các Điều khoản & Điều kiện và
                    Chính sách quyền riêng tư của TravelVIVU
                  </div>
                </Col>
                <Col span={16} >
                  <div style={{ float: 'right', marginTop: '5px' }}>
                    <Button style={{ color: 'white', fontWeight: '500', fontSize: '14px', backgroundColor: 'rgb(255, 94, 31)', width: '85px', height: '40px' }}>Tiếp tục</Button>
                  </div>
                </Col>
              </Row>
            </Col>
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