import imgsale from '../../../assets/svg/Rectangle 184.svg'
import imghd from '../../../assets/img/Screenshot 2023-10-14 212504.png'
import { Layout, Typography, Checkbox, Col, Row, Pagination } from 'antd';
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
import { Link } from 'react-router-dom';
const { Title } = Typography
const { Sider, Content } = Layout;
const Endow = () => {
    const [priceFilter, setPriceFilter] = useState({
        under100k: true,
        between100kAnd1M: false,
        above1M: false,
    });
    const [checkInDate, setCheckInDate] = useState(null);
    const [checkOutDate, setCheckOutDate] = useState(null);
    const dispatch = useDispatch();
    const [current, setCurrent] = useState(1);
    const onChangePage = (page) => {
        setCurrent(page);
        dispatch(fetchSearchProductsForPromotion(checkInDate?.valueOf(), checkOutDate?.valueOf(), parseInt(page) - 1));
    };
    const formatCurrency = (value) => {
        // Sử dụng Intl.NumberFormat để định dạng giá trị tiền tệ
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
        }).format(value);
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
                        <Sider style={{ backgroundColor: '#ffffff', borderRadius: 10, padding: '5px 5px 10px 10px', width: '30%', height: '100%', marginTop: 50, maxWidth: 400 }}><Title level={4}>
                            Ưu đãi khuyến mãi
                        </Title>
                            <div >
                                <Checkbox checked={priceFilter.under100k}
                                    onChange={(e) => setPriceFilter({ ...priceFilter, under100k: e.target.checked })}
                                >
                                    dưới 100.000 VNĐ
                                </Checkbox>
                                <Checkbox checked={priceFilter.between100kAnd1M}
                                    onChange={(e) => setPriceFilter({ ...priceFilter, between100kAnd1M: e.target.checked })}
                                >
                                    100.000 đến 1.000.000 VNĐ
                                </Checkbox>
                                <Checkbox checked={priceFilter.above1M}
                                    onChange={(e) => setPriceFilter({ ...priceFilter, above1M: e.target.checked })}
                                >
                                    trên 1.000.000
                                </Checkbox>
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
                                {productPromotion?.data && productPromotion?.data.length > 0 ? (
                                    productPromotion.data.map((items) => {
                                        const currentDate = new Date().getTime();
                                        const startDate = items.promotion?.startDate?.valueOf();
                                        const endDate = items.promotion?.endDate?.valueOf();
                                        const promotionValue = items.promotion?.value;

                                        if (
                                            items.promotion !== null &&
                                            startDate < currentDate &&
                                            currentDate < endDate &&
                                            ((priceFilter.under100k && promotionValue < 100000) ||
                                                (priceFilter.between100kAnd1M && promotionValue >= 100000 && promotionValue <= 1000000) ||
                                                (priceFilter.above1M && promotionValue > 1000000))
                                        ) {
                                            return (
                                                <Col className="gutter-row" span={8} style={{ marginTop: 50 }} key={items.id}>
                                                    <Link to={
                                                        `/homestay/detail/${items.id}?startDate=${checkInDate?.valueOf()}&endDate=${checkOutDate?.valueOf()}&numNight=1`
                                                    }>
                                                        <MDBCard>
                                                            <MDBCardImage src={items.images[0]?.imgUrl} position="top" alt="..." />
                                                            <MDBCardBody>
                                                                <MDBCardTitle style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>{items.name}</MDBCardTitle>
                                                                <MDBCardTitle style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>Khuyến mãi {items.promotion?.name}</MDBCardTitle>
                                                                <MDBCardTitle>GIẢM ĐẾN {formatCurrency(items.promotion?.value)}</MDBCardTitle>
                                                                <MDBCardText>
                                                                    *Không yêu cầu số tiền tối thiểu.
                                                                </MDBCardText>
                                                                <MDBBtn href="#">Đặt ngay</MDBBtn>
                                                            </MDBCardBody>
                                                        </MDBCard>
                                                    </Link>
                                                </Col>
                                            );
                                        }
                                        return null; // Return null for items that don't meet the date criteria
                                    })
                                ) : (
                                    <p>Nơi bạn tìm kiếm không có homestay nào</p>
                                )}
                            </Row>
                            <div style={{ float: 'right', marginTop: 20 }}>
                                <Pagination current={current} onChange={onChangePage} total={productPromotion?.totalPages * 10} />
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