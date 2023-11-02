import React, { useEffect, useState } from 'react';
import { Card, Row, Col, Rate, Typography } from 'antd';
import { useSelector } from 'react-redux';
import Fuse from 'fuse.js';
import imgproduct from '../../../assets/svg/Rectangle 153.svg'
import { EnvironmentOutlined } from "@ant-design/icons";

const { Title } = Typography
const { Meta } = Card;


const ProductTabs = (props) => {
    const products = useSelector((state) => state.product.products);
    const loading = useSelector((state) => state.product.loading);
    const error = useSelector((state) => state.product.error);

    const [productlist, setProducts] = useState([]);

    useEffect(() => {
        const fuse = new Fuse(products, {
            keys: ['province.name'],
            includeScore: true,
            threshold: 0.4, // Điều chỉnh ngưỡng tìm kiếm tùy chọn
            ignoreLocation: true,
            distance: 100,
        });

        const results = fuse.search(props.city);

        const filteredProducts = results.map(result => result.item).filter(item => item !== undefined);


        setProducts(filteredProducts.slice(0, 6));
    }, [products, props.city]);
    const loadMore = () => {
        const startIndex = productlist.length;
        const endIndex = startIndex + 8;
        const newProducts = [...productlist, ...products.slice(startIndex, endIndex)];

        const fuse = new Fuse(newProducts, {
            keys: ['province.name'],
            includeScore: true,
            threshold: 0.4,
            ignoreLocation: true,
            distance: 100,
        });

        const results = fuse.search(props.city);

        const filteredProducts = results.map(result => result.item);

        setProducts(filteredProducts);
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
                            cover={<img alt="example" src={product.images[0]?.imgUrl} />} // Dùng ?. để kiểm tra nếu imgUrl không tồn tại
                        >
                            <Meta title={product.name} />
                            <div>
                                <br />
                                <div style={{ display: 'flex' }}>
                                    <EnvironmentOutlined style={{ marginTop: 5 }} />&ensp;
                                    {product.province.name}
                                </div>
                                <Title level={3} style={{ color: 'red' }}>{product.price}VNĐ</Title>
                            </div>
                        </Card>

                    </Col>
                ))}
            </Row>
            {
                productlist.length < products.length && productlist.length > 0 && (
                    <div className="d-flex justify-content-center" style={{ marginTop: '20px' }}>
                        <button onClick={loadMore}
                            style={{
                                borderRadius: 8, backgroundColor: '#5392F9',
                                color: 'white', width: 380, height: 45, fontSize: 18, borderColor: '#5392F9'
                            }}>Xem thêm các chỗ nghỉ({props.city})</button>
                    </div>
                )
            }

        </section>
    );
}
export default ProductTabs