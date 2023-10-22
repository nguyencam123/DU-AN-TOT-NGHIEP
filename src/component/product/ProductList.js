import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../../features/product/productThunk';
import React, { useEffect, useState } from 'react';
import { Spin, Card, Row, Col, Typography, Input, Select, Button, Carousel, Tabs } from 'antd';
import { LeftOutlined, RightOutlined, TeamOutlined } from "@ant-design/icons";
import ProductTabs from './productTab/ProductTabs';
import slidekm from "../../assets/img/slidekm.png"
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
const { Title } = Typography
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
    const [currentLocation, setCurrentLocation] = useState('1'); // Initialize with 'Hồ Chí Minh'

    const handleTabChange = (key) => {
        setCurrentLocation(key);
    };
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

    const itemsToShow = YourData.slice(startIndex, endIndex);
    const onSearch: SearchProps['onSearch'] = (value, _e, info) => console.log(info?.source, value);

    const items = [
        {
            key: '1',
            label: 'Hồ Chí Minh',
            children: <ProductTabs city="Hồ Chí Minh" />,
        },
        {
            key: '2',
            label: 'Hà Nội',
            children: <ProductTabs city="Hà Nội" />,
        },
        {
            key: '3',
            label: 'Đà Nẵng',
            children: <ProductTabs city="Đà Nẵng" />,
        },
        {
            key: '4',
            label: 'Nha Trang',
            children: <ProductTabs city="Nha Trang" />,
        },
        {
            key: '5',
            label: 'Vũng Tàu',
            children: <ProductTabs city="Vũng Tàu" />,
        },
    ];

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'center' }}>
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
                            backgroundColor: 'white',
                            minHeight: '150px',
                            minWidth: '180px',
                            display: 'flex', /* Thêm thuộc tính display: flex để căn giữa nội dung bên trong */
                            flexDirection: 'column', /* Dọc xuống */
                            alignItems: 'center', /* Căn giữa theo chiều ngang */
                            justifyContent: 'space-between',
                            boxShadow: '0 0 3px 3px #ACAEB1'
                        }}>          <Search placeholder="input search text" size="large" onSearch={onSearch} enterButton style={{ width: '80%', marginTop: '45px', fontSize: '20px' }} />
                            <div style={{ position: 'absolute', top: 60 }}>

                                <Row gutter={{ xs: 8, sm: 16, md: 24, lg: 32 }}>
                                    <Col className="gutter-row" span={12}>
                                        <RangePicker size='large'
                                            defaultValue={[dayjs('2023/09/01', dateFormat), dayjs('2023/10/01', dateFormat)]}
                                            format={dateFormat} style={{ width: '340px', minWidth: '50px' }}
                                        />&emsp;
                                    </Col>
                                    <Col className="gutter-row" span={12}>
                                        <Select
                                            defaultValue="lucy"
                                            style={{ width: '340px', minWidth: '50px' }} // Thêm maxWidth ở đây
                                            size='large'
                                            options={[
                                                { value: 'jack', label: '2 người lớn một phòng' },
                                                { value: 'lucy', label: 'Lucy' },
                                                { value: 'Yiminghe', label: 'yiminghe' },
                                                { value: 'disabled', label: 'Disabled', disabled: true },
                                            ]}
                                            suffixIcon={<TeamOutlined style={{ fontSize: 18 }} />}
                                        />
                                    </Col>
                                </Row>
                            </div>
                            <div>
                                <Button style={{ width: 430, height: 64, borderRadius: 16, backgroundColor: 'rgb(83, 146, 249)', fontSize: '20', color: '#FFFFFF', top: 80 }}>Tìm kiếm</Button>
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
            <section style={{ padding: '0 150px 0 150px' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Title>Chương trình khuyến mại</Title>
                    <div><a href='/home-stay' style={{ fontSize: 18, textDecoration: 'none' }}>Xem tất cả ></a></div>
                </div>

                <Carousel style={{}}>
                    <div>
                        <div style={{ display: 'flex', justifyContent: 'center' }}>
                            <div style={{ width: 400, height: 185, marginRight: 40 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                            <div style={{ width: 400, height: 185, marginRight: 40 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                            <div style={{ width: 400, height: 185 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                        </div>
                    </div>
                    <div>
                        <div style={{ display: 'flex', justifyContent: 'center' }}>
                            <div style={{ width: 400, height: 185, marginRight: 40 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                            <div style={{ width: 400, height: 185, marginRight: 40 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                            <div style={{ width: 400, height: 185 }}><img src={slidekm} style={{ position: 'absolute', width: 400, height: 185 }} /></div>
                        </div>
                    </div>
                </Carousel>
            </section>
            <section style={{ marginTop: 30 }}>
                <div style={{ justifyContent: 'center', textAlign: 'center' }}>
                    <Title level={2}>Tiếng lành đồn xa</Title>
                </div>
                <div style={{ display: 'flex', justifyContent: 'center' }} >
                    <div style={{ width: 368, height: 294, border: '1px solid #e9ebee', backgroundColor: '#fff', textAlign: 'center', padding: '15px 15px 15px 10px', marginRight: 30, borderBottom: '4px solid #75a8f9' }} >
                        <Title level={4} style={{ color: '#5392f9' }}>Nhà nghỉ ở phố cổ<br /></Title><Title level={5} style={{ color: '#737373' }}>Hà Nội</Title><br />
                        <span style={{ fontSize: 22, fontFamily: 'mallory,Helvetica Neue,Helvetica,Arial,sans-serif', color: 'black' }}>Tôi hoàn toàn hài lòng khi nghỉ tại phố cổ. Cám ơn trvalVIVU rất nhiều.</span>
                        <div style={{ marginTop: 30, fontSize: 18 }}>
                            <p>- Perter đến từ Anh quốc</p>
                        </div>
                    </div>
                    <div style={{ width: 368, height: 294, border: '1px solid #e9ebee', backgroundColor: '#fff', textAlign: 'center', padding: '15px 15px 15px 10px', marginRight: 30, borderBottom: '4px solid #75a8f9' }} >
                        <Title level={4} style={{ color: '#5392f9' }}>khách sạn haritage<br /></Title><Title level={5} style={{ color: '#737373' }}>Đà nẵng</Title><br />
                        <span style={{ fontSize: 22, fontFamily: 'mallory,Helvetica Neue,Helvetica,Arial,sans-serif', color: 'black' }}>Tôi săn được giá đặc biệt giờ chót với TravalVIVU. Phòng ốc rộng rãi, giường ngủ thoải mái và ấm cúng.</span>
                        <div style={{ marginTop: 30, fontSize: 18 }}>
                            <p>- fukada đến từ Nhật Bản</p>
                        </div>
                    </div>
                    <div style={{ width: 368, height: 294, border: '1px solid #e9ebee', backgroundColor: '#fff', textAlign: 'center', padding: '15px 15px 15px 10px', borderBottom: '4px solid #75a8f9' }} >
                        <Title level={4} style={{ color: '#5392f9' }}>Khách sạn novotel<br /></Title><Title level={5} style={{ color: '#737373' }}>Phú QUốc</Title><br />
                        <span style={{ fontSize: 22, fontFamily: 'mallory,Helvetica Neue,Helvetica,Arial,sans-serif', color: 'black' }}>Sau khi nghỉ tại đây, mọi âu lo đều tan biến. Cám ơn trvalVIVU rất nhiều.</span>
                        <div style={{ marginTop: 30, fontSize: 18 }}>
                            <p>- Anh Wang đến từ Trung Quốc</p>
                        </div>
                    </div>
                </div>
            </section>
            <br /><br />
            <section style={{ marginBottom: 100 }}>
                <div style={{ textAlign: 'center' }}><Title level={2}>Những chỗ nghỉ nổi bật dành cho quý khách:</Title></div>
                <div><Tabs
                    defaultActiveKey="1"
                    centered
                    items={items}
                    onChange={handleTabChange}
                /></div>
            </section>
        </div>
    );
}

export default ProductList