import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect, useState } from 'react';
import { Spin, Card, Row, Col, Typography, Input, Select, Button, Carousel } from 'antd';
import { LeftOutlined, RightOutlined, TeamOutlined } from "@ant-design/icons";
import imgsection from "../../assets/svg/Ellipse 1.svg"
import imgslide2 from "../../assets/svg/Ellipse 2.svg"
import imgslide3 from "../../assets/svg/Ellipse 3.svg"
import imgslide4 from "../../assets/svg/Ellipse 4.svg"
import beach from "../../assets/svg/beach_access_FILL0_wght400_GRAD0_opsz24.svg"
import kayaking from "../../assets/svg/kayaking_FILL0_wght400_GRAD0_opsz24.svg"
import slideform from "../../assets/svg/Rectangle 7.svg"
import "./ProductList.css"
import dayjs from 'dayjs';
import { DatePicker, Space } from 'antd';
const { Search } = Input;
const Title = Typography
const { RangePicker } = DatePicker;

const dateFormat = 'YYYY/MM/DD';
const contentStyle = {
    height: '45%',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
    display: 'flex'
};
const sectionStyle = {
    marginTop: '250px',
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
        { img: imgslide4, title: 'Đà Lạt' },
        { img: imgsection, title: 'Hồ Chí Minh' },
        { img: imgslide2, title: 'Hà Nội' },
        { img: imgslide3, title: 'Hải Phòng' },
        { img: imgslide4, title: 'Đà Lạt' },
        // Thêm các dữ liệu khác tương tự
    ];
    const itemsPerPage = 7;
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    const itemsToShow = YourData.slice(startIndex, endIndex); // Thay YourData bằng dữ liệu thực tế của bạn
    const onSearch: SearchProps['onSearch'] = (value, _e, info) => console.log(info?.source, value);

    return (

        <div>

            <div>
                <h3 style={contentStyle}>
                    <div style={{ position: 'relative' }}>
                        <img src={slideform} style={{ width: '100%', backgroundSize: 'auto' }} />
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
                            minWidth: '150px',
                            display: 'flex', /* Thêm thuộc tính display: flex để căn giữa nội dung bên trong */
                            flexDirection: 'column', /* Dọc xuống */
                            alignItems: 'center', /* Căn giữa theo chiều ngang */
                            justifyContent: 'space-between'
                        }}>          <Search placeholder="input search text" size="large" onSearch={onSearch} enterButton style={{ width: '80%', marginTop: '45px', fontSize: '20px' }} />
                            <div style={{ position: 'absolute', top: 60 }}>
                                <table>
                                    <tr>
                                        <td>
                                            <RangePicker size='large'
                                                defaultValue={[dayjs('2023/09/01', dateFormat), dayjs('2023/10/01', dateFormat)]}
                                                format={dateFormat}
                                            />&emsp;
                                        </td>
                                        <td>
                                            <Select
                                                defaultValue="lucy"
                                                style={{ width: '317px', minWidth: '150px' }} // Thêm maxWidth ở đây
                                                size='large'
                                                options={[
                                                    { value: 'jack', label: '2 người lớn một phòng' },
                                                    { value: 'lucy', label: 'Lucy' },
                                                    { value: 'Yiminghe', label: 'yiminghe' },
                                                    { value: 'disabled', label: 'Disabled', disabled: true },
                                                ]}
                                                suffixIcon={<TeamOutlined style={{ position: 'absolute', right: '10px' }} />}
                                            />
                                        </td>
                                    </tr>
                                </table>

                            </div>
                            <div>
                                <Button style={{ width: 430, height: 64, borderRadius: 16, backgroundColor: '#FF7D63', fontSize: '20', color: '#FFFFFF', top: 80 }}>Tìm kiếm</Button>
                            </div>
                        </div>
                    </div>
                </h3>
            </div>
            <section style={sectionStyle}>
                <div className='texthead' style={{ textAlign: 'center' }}>
                    Các Điểm Đến Thu Hút Nhất Việt Nam
                </div>
                <div className='allItem' style={{ alignItems: 'center', display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                    {currentPage > 0 && <div onClick={handlePrevPage}><LeftOutlined style={{ fontSize: '22px' }} /></div>}
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
                    {(currentPage + 1) * itemsPerPage < YourData.length && <div onClick={handleNextPage}><RightOutlined style={{ fontSize: '22px', position: 'absolute' }} /></div>}
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