import imgsale from '../../../assets/svg/Rectangle 184.svg'
import imghd from '../../../assets/img/Screenshot 2023-10-14 212504.png'
import { Layout, Typography, Checkbox, Col, Divider, Row } from 'antd';
import {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBCardImage,
    MDBBtn
} from 'mdb-react-ui-kit';
const { Title } = Typography
const { Sider, Content } = Layout;
const Endow = () => {
    return (
        <section>
            <div style={{ display: 'flex', justifyContent: 'center' }}>
                <img src={imgsale} />
            </div>
            <div style={{ padding: '50px 200px 50px 200px' }}>
                <Layout>

                    <Layout hasSider>
                        <Sider style={{ backgroundColor: '#ffffff', borderRadius: 10, padding: '5px 5px 10px 10px', width: '30%', height: '100%', marginTop: 50 }}><Title level={4}>
                            Ưu đãi
                        </Title>
                            <div>
                                <Checkbox>Phiếu giảm giá</Checkbox>
                                <Checkbox>Khuyến mại có thời hạn </Checkbox>
                                <Checkbox>Khuyến mại đặc biệt</Checkbox>
                            </div>
                        </Sider>
                        <Content style={{ marginLeft: 50 }}>
                            <Row
                                gutter={{
                                    xs: 8,
                                    sm: 16,
                                    md: 24,
                                    lg: 32,
                                }}
                            >
                                <Col className="gutter-row" span={8} style={{ marginTop: 50 }}>
                                    <MDBCard >
                                        <MDBCardImage src='https://mdbootstrap.com/img/new/standard/nature/184.webp' position='top' alt='...' />
                                        <MDBCardBody>
                                            <MDBCardTitle>GIẢM ĐẾN ₫ 500,000</MDBCardTitle>
                                            <MDBCardText>
                                                *Không yêu cầu số tiền tối thiểu.
                                            </MDBCardText>
                                            <MDBBtn href='#'>Nhận coupon</MDBBtn>
                                        </MDBCardBody>
                                    </MDBCard>
                                </Col>
                            </Row>
                        </Content>
                    </Layout>
                </Layout>
                <div style={{
                    width: '100%', height: 450, marginBottom: 30,
                    backgroundColor: '#ffffff', marginTop: 40,
                    borderRadius: 10, padding: '40px 40px 0px 40px', textAlign: 'center'
                }}><div>
                        <Title level={2}>Cách Áp Dụng Phiếu Giảm Giá</Title>
                    </div>
                    <div><img src={imghd} style={{ width: '90%' }} /></div>
                </div>
            </div>
        </section>
    )
}
export default Endow