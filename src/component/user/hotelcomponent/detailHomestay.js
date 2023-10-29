
import React from 'react';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Rate, Button, Image } from 'antd';
import { EnvironmentOutlined } from '@ant-design/icons'
const { Header, Content, Footer } = Layout;


export const DetailHomestay = () => {

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
          <Breadcrumb.Item>Home</Breadcrumb.Item>
          <Breadcrumb.Item>List</Breadcrumb.Item>
          <Breadcrumb.Item>App</Breadcrumb.Item>
        </Breadcrumb>
        <div
          style={{
            padding: 24,
            minHeight: 380,

          }}
        >
          <Row>
            <Col>
              <h4>Homestay name</h4>
              <span>Homestay name</span>
            </Col>
          </Row>
          <Row style={{ marginTop: '10px' }}>
            <Col span={8}>
              <EnvironmentOutlined style={{ fontSize: '10px', alignItems: 'center' }} />
              <span style={{ fontSize: '12px', marginTop: '3px' }}>Ho hoan kiem abc xyzn ,asf</span>
              <div style={{ fontSize: '18px', alignItems: 'center', marginTop: '10px' }}>Khachs san abcxyz mo ta zyx</div>
            </Col>
            <Col span={6} push={10} >
              <div>
                <div style={{ fontSize: '12px', marginBottom: '0' }}>Giá mỗi phòng mỗi đêm từ</div>
                <div style={{ fontSize: '24', color: 'rgb(255, 94, 31)', lineHeight: '28px', fontWeight: '700', marginTop: '-5px' }}>800000 <span style={{ fontSize: '22' }}>VND</span> </div>
              </div>
              <Button style={{ width: '100%', backgroundColor: 'rgb(255, 94, 31)' }}>Chọn phòng</Button>
            </Col>
          </Row>
          <Row style={{ marginTop: '15px' }}>
            <Col span={14}>
              <Image
                style={{ borderRadius: '10px' }}
                width={565}
                height={300}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
            </Col>
            <Col span={4} style={{ marginLeft: '12px' }}>
              <Image
                style={{ borderRadius: '10px', marginBottom: '10px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '7px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '15px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
            </Col>
            <Col span={4} push={1}>
              <Image
                style={{ borderRadius: '10px', marginBottom: '10px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '7px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '15px' }}
                width={190}
                height={95}
                src="http://res.cloudinary.com/dcwkiozwf/image/upload/v1698477662/homestay_images/naaveb7ytdgyolqe3n1e.jpg"
              />
            </Col>
          </Row>
          <Row style={{ marginTop: '10px' }}>
            <Col span={5} style={{ backgroundColor: 'white', borderRadius: '5px', height: '150px' }} >
              <div style={{ margin: '10px 0px' }}>
                <h4 style={{ marginLeft: '15px', marginTop: '10px', fontSize: '16px' }}>Tiện nghi chính</h4>
                <div style={{ marginLeft: '15px', fontSize: '12px', fontWeight: '500' }}>
                  <div>Wifi</div>
                  <div>Pool</div>
                  <div>Thang may</div>
                  <div>Phong Gyms</div>
                </div>
              </div>
            </Col>
            <Col span={18} push={1} style={{ backgroundColor: 'white', borderRadius: '5px', height: '150px' }} >
              <div style={{ margin: '10px 0px' }}>
                <h4 style={{ margin:'10px 0px 15px 15px', fontSize: '16px' }}>Cảm nghĩ của du khách</h4>
                <Row>
                  <Col span={10}>
                    <div style={{ width: '320px', height:'80px', marginLeft: '15px', fontSize: '12px', fontWeight: '500', boxShadow: '0px 2px 5px rgba(3,18,26,0.15)' }}>
                      <div style={{marginBottom:'5px'}}>Anh Tran Quang Huy</div>
                      <span>Khach san voi Blue sky la qua tuyet voi</span>
                    </div>
                  </Col>
                  <Col span={10} push={2}>
                    <div style={{ width: '320px',height:'80px', marginLeft: '15px', fontSize: '12px', fontWeight: '500', boxShadow: '0px 2px 5px rgba(3,18,26,0.15)' }}>
                      <div style={{marginBottom:'5px'}}>Anh Tran Quang Huy</div>
                      <span>Khach san voi Blue sky la qua tuyet voi</span>
                    </div>
                  </Col>
                </Row>
              </div>
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