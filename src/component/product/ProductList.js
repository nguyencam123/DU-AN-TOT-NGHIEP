import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect, useState } from 'react';
import { Spin, Card, Row, Col, Typography } from 'antd';
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import imgsection from "../../assets/svg/Ellipse 1.svg"
import imgslide2 from "../../assets/svg/Ellipse 2.svg"
import imgslide3 from "../../assets/svg/Ellipse 3.svg"
import imgslide4 from "../../assets/svg/Ellipse 4.svg"
import beach from "../../assets/svg/beach_access_FILL0_wght400_GRAD0_opsz24.svg"
import kayaking from "../../assets/svg/kayaking_FILL0_wght400_GRAD0_opsz24.svg"
import slideform from "../../assets/svg/Rectangle 7.svg"
import "./ProductList.css"
import { Carousel } from 'antd';

const Title = Typography
const contentStyle = {
    height: '45%',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
    display: 'flex'
};
const sectionStyle = {
    marginTop: '300px',
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
    const [currentPage, setCurrentPage] = useState(0);

    const handleNextPage = () => {
        setCurrentPage(currentPage + 1);
    };

    const handlePrevPage = () => {
        setCurrentPage(currentPage - 1);
    };
    const YourData = [
        { img: imgsection, title: 'Hồ Chí Minh' },
        { img: imgslide2, title: 'Hà Nội' },
        { img: imgslide3, title: 'Hải Phòng' },
        { img: imgslide4, title: 'Đà Lạt' },
        { img: imgslide4, title: 'Nha Trang' },
        { img: imgslide4, title: 'Hội An' },
        { img: imgslide4, title: 'Hội An' },
        { img: imgslide4, title: 'Hội An' },
        { img: imgslide4, title: 'Đà Lạt' }
        // Thêm các dữ liệu khác tương tự
    ];
    const itemsPerPage = 7;
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    const itemsToShow = YourData.slice(startIndex, endIndex); // Thay YourData bằng dữ liệu thực tế của bạn

    return (

        <div>

            <div>
                <h3 style={contentStyle}>
                    <div style={{ position: 'relative' }}>
                        <img src={slideform} style={{ width: '100%' }} />
                        <div style={{
                            position: 'absolute',
                            left: '50%',
                            bottom: -300,
                            transform: 'translate(-50%, -50%)',
                            alignItems: 'center',
                            width: '60%',
                            height: '246px',
                            borderRadius: '8px',
                            backgroundColor: '#FFE5CA',
                            minHeight: '100px',
                            minWidth: '150px'
                        }}></div>
                    </div>
                </h3>
            </div>



            <section style={sectionStyle}>
                <div className='texthead' style={{ textAlign: 'center' }}>
                    Các Điểm Đến Thu Hút Nhất Việt Nam
                </div>
                <div className='allItem' style={{ alignItems: 'center', display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                    {currentPage > 0 && <div onClick={handlePrevPage}><LeftOutlined /></div>}
                    {itemsToShow.map((item, index) => (
                        <div className='picturetrval' style={{ textAlign: 'center', width: '10%' }} key={index}>
                            <div className='image-container'>
                                <img src={item.img} style={{ borderRadius: '50px' }} alt='Hình ảnh' />
                                <div className='hover-text'>
                                    <Title level={5}>{item.title}</Title>
                                </div>
                            </div>
                        </div>
                    ))}
                    {(currentPage + 1) * itemsPerPage < YourData.length && <div onClick={handleNextPage}><RightOutlined /></div>}
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