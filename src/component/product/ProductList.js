import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect } from 'react';
import { Spin, Card, Row, Col } from 'antd';
import { fetchProductsAsync } from '../../features/product/createproductThunks';
import slice1 from "../../assets/img/Screenshot 2023-09-08 174300.png"
import slice2 from "../../assets/img/Screenshot 2023-09-08 174708.png"
import slice3 from "../../assets/img/Screenshot 2023-09-08 174807.png"
import imgsection from "../../assets/img/Screenshot 2023-09-08 174057.png"
import "./ProductList.css"
import { Carousel } from 'antd';
const contentStyle = {
    height: '660px',
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
                <div className="textOverlay" style={{ position: 'absolute', zIndex: '1' }}>
                    <div className='border-inpicture'>getText();</div>
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