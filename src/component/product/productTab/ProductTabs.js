import React, { useEffect, useState } from 'react';
import { Card, Row, Col, Rate } from 'antd';
import { useSelector } from 'react-redux';
const ProductTabs = (props) => {
  const { Meta } = Card;
  const products = useSelector((state) => state.product.products);
  const loading = useSelector((state) => state.product.loading);
  const error = useSelector((state) => state.product.error);
  useEffect(() => {
    setProducts(products.slice(0, 6));
  }, [products]);

  const [productlist, setProducts] = useState([]);

  const loadMore = () => {
    const startIndex = productlist.length;
    const endIndex = startIndex + 6;
    setProducts([...productlist, ...products.slice(startIndex, endIndex)]);
  };
  console.log(products)
  return (
    <>
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
      <Row style={{ marginLeft: '100px' }}>
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
                width: 240,
              }}
              cover={<img alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" />}
            >
              <Meta title={product.name} description={product.price} />
              <Rate allowHalf disabled defaultValue={product.star} />
            </Card>
          </Col>
        ))}
      </Row>
      {productlist.length < products.length && (
        <div className="d-flex justify-content-center" style={{ marginTop: '20px' }}>
          <button onClick={loadMore} style={{ borderRadius: 8, backgroundColor: '#FF7D63', color: 'white' }}>Xem thêm</button>
        </div>
      )}
    </>
  )
}
export default ProductTabs