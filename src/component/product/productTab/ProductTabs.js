import React, { useEffect, useState } from 'react';
import { Card, Row, Col, Rate, Typography } from 'antd';
import { useSelector } from 'react-redux';

import imgproduct from '../../../assets/svg/Rectangle 153.svg'
import { EnvironmentOutlined } from "@ant-design/icons";

const { Title } = Typography


const ProductTabs = (props) => {
    const { Meta } = Card;
    const products = useSelector((state) => state.product.products);
    const loading = useSelector((state) => state.product.loading);
    const error = useSelector((state) => state.product.error);
    useEffect(() => {
        setProducts(products.slice(0, 6));
    }, [products]);
    console.log(products)
    const [productlist, setProducts] = useState([]);
    const loadMore = () => {
        const startIndex = productlist.length;
        const endIndex = startIndex + 8;
        setProducts([...productlist, ...products.slice(startIndex, endIndex)]);
    };
    return (
        <section style={{ padding: '0 200px 0 200px' }}>
            {
                loading ? (
                    <div className="d-flex justify-content-center">
                        <div className="spinner-border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </div>
                    </div>
                ) : null
            }
            {error ? <p>Error: {error}</p> : null}
            <Row style={{}}>
                {productlist.map((product) => (
                    <Col
                        xs={{ span: 24 }}
                        sm={{ span: 12 }}
                        md={{ span: 8 }}
                        lg={{ span: 6 }}
                        style={{ marginTop: '50px' }}
                        key={product.id}
                    >
                        <Card
                            hoverable
                            style={{
                                width: 270
                            }}
                            cover={<img alt="example" src={imgproduct} />}
                        >
                            <Meta title={product.homestay_Name} />
                            <div>
                                <Rate allowHalf disabled defaultValue={product.star} />
                                <div style={{ display: 'flex' }}>
                                    <EnvironmentOutlined style={{ marginTop: 5 }} />&ensp;
                                    {product.province_Name}
                                </div>
                                <Title level={3} style={{ color: 'red' }}>{product.price}VNĐ</Title>
                            </div>
                        </Card>
                    </Col>
                ))}
            </Row>
            {productlist.length < products.length && (
                <div className="d-flex justify-content-center" style={{ marginTop: '20px' }}>
                    <button onClick={loadMore}
                        style={{
                            borderRadius: 8, backgroundColor: '#5392F9',
                            color: 'white', width: 380, height: 45, fontSize: 18, borderColor: '#5392F9'
                        }}>Xem thêm các chỗ nghỉ({props.city})</button>
                </div>
            )}
        </section>
    )
}
export default ProductTabs