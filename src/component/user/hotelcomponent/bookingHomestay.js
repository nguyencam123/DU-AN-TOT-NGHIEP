
import React from 'react';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Rate, Button, Image, Progress, Space } from 'antd';
import { ClockCircleTwoTone, EnvironmentOutlined, FileTextTwoTone, InfoCircleTwoTone, StarTwoTone } from '@ant-design/icons'
import { Form, Table } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
const { Header, Content, Footer } = Layout;


export const BookingHomestay = () => {

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
          <Breadcrumb.Item>Booking</Breadcrumb.Item>
          <Breadcrumb.Item>App</Breadcrumb.Item>
        </Breadcrumb>
        <div
          style={{
            paddingTop: 50,
            paddingRight: 100,
            paddingBottom: 50,
            paddingLeft: 100,
            minHeight: 380,

          }}
        >
          <Row>
            <Col span={24}>
              <h3 style={{ fontWeight: '700' }}>
                Đặt Homestay
              </h3>
              <div style={{ color: 'rgb(104, 113, 118)', fontSize: '16px', fontWeight: '700' }}>
                Hãy chắc chắn rằng tất cả thông tin trên trang này là chính xác trước khi tiến hành thanh toán.
              </div>
            </Col>
          </Row>
          <Row style={{ marginTop: '25px' }}>
            <Col span={17} >
              <h5 style={{ fontWeight: '700' }}>
                Chi tiết liên hệ (Cho Vé điện tử/Phiếu xác nhận)
              </h5>
              <div style={{ backgroundColor: 'white', borderRadius: '10px' }}>
                <Form style={{ padding: '20px' }}>
                  <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label style={{ fontWeight: '700' }}>Họ và tên</Form.Label>
                    <Form.Control type="text" placeholder="Họ và tên" />
                    <Form.Text className="text-muted">
                      *Nhập tên như trên CMND/ hộ chiếu
                    </Form.Text>
                  </Form.Group>

                  <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label style={{ fontWeight: '700' }}>Số điện thoại</Form.Label>
                    <Form.Control type="text" placeholder="Số điện thoại" />
                    <Form.Text className="text-muted">
                      VD : 09683741834
                    </Form.Text>
                  </Form.Group>
                  <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label style={{ fontWeight: '700' }}>Email</Form.Label>
                    <Form.Control type="email" placeholder="Email" />
                    <Form.Text className="text-muted">
                      VD: email@example.com
                    </Form.Text>
                  </Form.Group>
                </Form>
              </div>
            </Col>
            <Col span={6} push={1} style={{marginTop:'30px'}}>
              <div style={{ backgroundColor: 'white', borderRadius:'10px' }}>
                <Row style={{ margin: '25px 0px 0px 15px' }}>
                  <Col span={4}>
                  <Image
                    src='https://d1785e74lyxkqq.cloudfront.net/_next/static/v2/6/6aa2fd01a9460e1a71bb0efb713f0212.svg'
                    style={{ marginTop: '10px' }}
                  />
                  </Col>
                  <Col span={20}>
                    <div>Homestay Name</div>
                    <div>Homestay Name</div>
                  </Col>
                </Row>
              </div>
            </Col>
          </Row>
          <Row style={{ marginTop: '25px' }}>
            <Col span={17}>
              <h6 style={{ fontWeight: '700' }}>
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
                      692.000 VND
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
                      620.000 VND
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
                      72.000 VND
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