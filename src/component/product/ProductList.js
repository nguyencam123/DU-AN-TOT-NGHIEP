import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect } from 'react';
import { Spin, Card, Row, Col } from 'antd';
import { fetchProductsAsync } from '../../features/product/createproductThunks';
import slice1 from "../../assets/img/Screenshot 2023-09-08 174300.png"
import slice2 from "../../assets/img/Screenshot 2023-09-08 174708.png"
import slice3 from "../../assets/img/Screenshot 2023-09-08 174807.png"
import imgsection from "../../assets/svg/sailing_FILL0_wght400_GRAD0_opsz24.svg"
import apartment from "../../assets/svg/apartment_FILL0_wght400_GRAD0_opsz24.svg"
import favorite from "../../assets/svg/favorite_FILL0_wght400_GRAD0_opsz24.svg"
import explore from "../../assets/svg/explore_FILL0_wght400_GRAD0_opsz24.svg"
import beach from "../../assets/svg/beach_access_FILL0_wght400_GRAD0_opsz24.svg"
import kayaking from "../../assets/svg/kayaking_FILL0_wght400_GRAD0_opsz24.svg"
import "./ProductList.css"
import { Carousel } from 'antd';
const contentStyle = {
    height: '100%',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
};
const sectionStyle = {
    height: "323px",
    width: "100%",
};
const awesection1 = {
    position: "relative",
}
const overlay = {
    position: "absolute",
    top: "0",
    left: "0",
    width: "100%",
    height: "100%",
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    zIndex: "2" // Giá trị này phải cao hơn so với phần ảnh
}
function ProductList() {
    const dispatch = useDispatch();
    const products = useSelector((state) => state.product.products);
    const loading = useSelector((state) => state.product.loading);
    const error = useSelector((state) => state.product.error);
    const { Meta } = Card;
    useEffect(() => {
        dispatch(fetchProducts());
    }, []);

    return (

        <div>
            <Carousel autoplay>
                <div>
                    <h3 style={contentStyle}><img src={slice1} style={{ width: '100%' }} /></h3>
                </div>
                <div>
                    <h3 style={contentStyle}><img src={slice2} style={{ width: '100%' }} /></h3>
                </div>
                <div>
                    <h3 style={contentStyle}><img src={slice3} style={{ width: '100%' }} /></h3>
                </div>
            </Carousel>
            <section className='awesection1' style={sectionStyle}>
                <div className="textOverlay" style={{ position: 'absolute', zIndex: '1', display: 'flex', justifyContent: 'center', alignItems: 'center', width: '100%', height: '100%' }}>
                    <div className='border-inpicture'><img src={imgsection} style={{ width: '110px', marginLeft: '12px', color: 'white' }} />
                        <div className='text-inpictủe' style={{ marginTop: '40px', fontSize: '25px', marginLeft: '10px' }}>
                            Hành trình
                        </div>
                    </div>
                    <div className='border-inpicture-1'><img src={apartment} style={{ width: '110px', marginLeft: '12px', color: 'white' }} />
                        <div className='text-inpictủe' style={{ marginTop: '40px', fontSize: '25px', marginLeft: '10px' }}>
                            Cao cấp
                        </div>
                    </div>
                    <div className='border-inpicture-2'><img src={favorite} style={{ width: '110px', marginLeft: '12px', color: 'white', marginTop: '15px' }} />
                        <div className='text-inpictủe' style={{ marginTop: '30px', fontSize: '25px', marginLeft: '10px' }}>
                            Tuần trăng mật
                        </div>
                    </div>
                    <div className='border-inpicture-2'><img src={explore} style={{ width: '110px', marginLeft: '12px', color: 'white', marginTop: '10px' }} />
                        <div className='text-inpictủe' style={{ marginTop: '40px', fontSize: '25px', marginLeft: '10px' }}>
                            Khám phá
                        </div>
                    </div>
                    <div className='border-inpicture-2'><img src={beach} style={{ width: '110px', marginLeft: '12px', color: 'white', marginTop: '10px' }} />
                        <div className='text-inpictủe' style={{ marginTop: '40px', fontSize: '25px', marginLeft: '10px' }}>
                            Sinh thái
                        </div>
                    </div>
                    <div className='border-inpicture-2'><img src={kayaking} style={{ width: '110px', marginLeft: '12px', color: 'white', marginTop: '10px' }} />
                        <div className='text-inpictủe' style={{ marginTop: '40px', fontSize: '25px', marginLeft: '10px' }}>
                            Đi biển
                        </div>
                    </div>
                </div>
            </section>
            <br /><br />
            {loading ? <div class="d-flex justify-content-center">
                <div class="spinner-border" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
            </div> : null}
            {error ? <p>Error: {error}</p> : null}
            <Row style={{ marginLeft: '100px' }}>
                {products.map((product) => (
                    <Col xs={{ span: 24 }} sm={{ span: 12 }} md={{ span: 8 }} lg={{ span: 6 }} style={{ marginTop: '50px' }}>
                        <Card
                            hoverable
                            style={{
                                width: 240,
                            }}
                            cover={<img alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" />}
                        >
                            <Meta title={product.name} description={product.price} />
                        </Card>
                    </Col>
                ))}
            </Row>
        </div>
    );
}

export default ProductList