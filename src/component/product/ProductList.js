import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect } from 'react';
import { Spin, Card, Row, Col } from 'antd';
import { fetchProductsAsync } from '../../features/product/createproductThunks';
import slice1 from "../../assets/img/iphone12.png"
import { Carousel } from 'antd';
const contentStyle = {
    height: '660px',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
};
function ProductList() {
    const dispatch = useDispatch();
    const products = useSelector((state) => state.product.products);
    const loading = useSelector((state) => state.product.loading);
    const error = useSelector((state) => state.product.error);
    const { Meta } = Card;
    useEffect(() => {
        dispatch(fetchProducts());
    }, []);

    React.useEffect(() => {
        dispatch(fetchProductsAsync());
    }, [dispatch]);

    return (

        <div>
            <Carousel autoplay>
                <div>
                    <h3 style={contentStyle}><img src={slice1} style={{ width: '100%' }} /></h3>
                </div>
                <div>
                    <h3 style={contentStyle}>2</h3>
                </div>
                <div>
                    <h3 style={contentStyle}>3</h3>
                </div>
                <div>
                    <h3 style={contentStyle}>4</h3>
                </div>
            </Carousel><br /><br />
            {loading ? <Spin className="example" size="large" /> : null}
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