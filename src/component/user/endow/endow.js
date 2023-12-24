import imgsale from '../../../assets/svg/Rectangle 184.svg'
import imghd from '../../../assets/img/Screenshot 2023-10-14 212504.png'
import { Layout, Typography, Checkbox, Col, Divider, Row, Pagination } from 'antd';
import {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBCardImage,
    MDBBtn
} from 'mdb-react-ui-kit';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchSearchProductsForPromotion } from '../../../features/product/searchProductThunk';
const { Title } = Typography
const { Sider, Content } = Layout;
const Endow = () => {
    const [checkInDate, setCheckInDate] = useState(null);
    const [checkOutDate, setCheckOutDate] = useState(null);
    const dispatch = useDispatch();
    const [current, setCurrent] = useState(1);
    const onChangePage = (page) => {
        setCurrent(page);
    };
    useEffect(() => {
        // Set checkInDate to tomorrow
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        setCheckInDate(tomorrow);

        // Set checkOutDate to the day after tomorrow
        const dayAfterTomorrow = new Date();
        dayAfterTomorrow.setDate(dayAfterTomorrow.getDate() + 10);
        setCheckOutDate(dayAfterTomorrow);
    }, []);
    useEffect(() => {
        dispatch(fetchSearchProductsForPromotion(checkInDate?.valueOf(), checkOutDate?.valueOf(), parseInt(current) - 1));
    }, [checkInDate, checkOutDate]);
    const productPromotion = useSelector((state) => state.product.productPromotion);
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
                                {productPromotion.map((items) =>
                                    items.promotion !== null && (
                                        <Col className="gutter-row" span={8} style={{ marginTop: 50 }} key={items.id}>
                                            <MDBCard>
                                                <MDBCardImage src={items.images[0]?.imgUrl} position="top" alt="..." />
                                                <MDBCardBody>
                                                    <MDBCardTitle style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>{items.name}</MDBCardTitle>
                                                    <MDBCardTitle>GIẢM ĐẾN {items.promotion?.value}₫</MDBCardTitle>
                                                    <MDBCardText>
                                                        *Không yêu cầu số tiền tối thiểu.
                                                    </MDBCardText>
                                                    <MDBBtn href="#">Đặt ngay</MDBBtn>
                                                </MDBCardBody>
                                            </MDBCard>
                                        </Col>
                                    )
                                )}

                            </Row>
                            <div style={{ float: 'right', marginTop: 20 }}>
                                <Pagination current={current} onChange={onChangePage} total={productPromotion.length} />
                            </div>
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