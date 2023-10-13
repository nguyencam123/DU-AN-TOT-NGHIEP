import imgsale from '../../../assets/svg/Rectangle 184.svg'
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
            <div>
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
            </div>
        </section>
    )
}
export default Endow