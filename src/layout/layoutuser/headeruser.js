import "./header.css"
import "bootstrap/dist/css/bootstrap.css";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {
    UserOutlined,
    ShoppingCartOutlined,
    SearchOutlined,
    FileDoneOutlined,
    HeartOutlined,
    CommentOutlined,
    StarOutlined, BankOutlined
} from "@ant-design/icons";
import { Link } from "react-router-dom";
import { logoutUser } from "../../features/user/userThunk";
import { Avatar, Badge, Input, Button } from 'antd';
import { useSelector, useDispatch } from "react-redux";
import React, { useEffect, useState } from 'react';
import { Modal, Typography } from 'antd';
import { useNavigate } from "react-router-dom";
import LoginComponent from "../../component/login/Login";
import iconplane from "../../assets/svg/Iconka-Business-Finance-Plane.512.png"
import logotravel from "../../assets/svg/Rectangle 3.svg"

const { Title } = Typography

const HeaderUser = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [showDropdown, setShowDropdown] = useState(false);
    const navigate = useNavigate()
    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const dispatch = useDispatch()

    const [searchVisible, setSearchVisible] = useState(false);
    const [searchValue, setSearchValue] = useState('');

    const handleSearchClick = () => {
        setSearchVisible(!searchVisible);
        setSearchValue('');
    };

    const handleInputChange = (e) => {
        setSearchValue(e.target.value);
    };

    const handleSearch = () => {
        console.log('Searching for:', searchValue);
    };


    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    useEffect(() => {
        if (isLoggedIn) {
            setIsModalOpen(false)
        }
    }, [isLoggedIn])
    const handleDropdownToggle = () => {
        if (!isLoggedIn) {
            showModal(); // Redirect to /login if not logged in
        } else {
            setShowDropdown(!showDropdown); // Toggle the dropdown if logged in
        }
    };
    const logout = () => {
        dispatch(logoutUser())
        navigate('/login')
    }
    return (
        <header>

            <Navbar collapseOnSelect expand="lg" >
                <Container>
                    <Navbar.Brand href="https://html.design/preview/?theme=timups">
                        {/* <div className="parent-box">
                            <div className="box-fly-icon">
                                <div className="parent-plane-icon"><img className="plane-icon" src={iconplane} style={{ color: 'white' }} /></div>
                            </div>
                            <Title level={2} style={{ color: 'blue' }}>Travel</Title>
                        </div> */}
                        <img src={logotravel} style={{}} />
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Link to={"/"} className="textnabar">
                                <strong>trang chủ</strong>
                            </Link>
                            <Link to={"/khach-san"} className="textnabar">
                                <strong>Khách sạn</strong>
                            </Link>
                            <Link to={"/phieu-giam-gia"} className="textnabar">
                                <strong>Phiếu giảm giá</strong>
                            </Link>
                            <Link to={"/su-kien"} className="textnabar">
                                <strong>Sự kiện</strong>
                            </Link>
                        </Nav>
                        <div className="headercart">
                            <div className="cartAndPerson" style={{ display: "flex" }}>
                                <div className="search-bar-container">
                                    {searchVisible ? (
                                        <div className="search-input-container">
                                            <Input
                                                placeholder="Tìm kiếm..."
                                                value={searchValue}
                                                onChange={handleInputChange}
                                            />
                                            <Button type="primary" onClick={handleSearch}>
                                                Tìm kiếm
                                            </Button>
                                        </div>
                                    ) : (
                                        <SearchOutlined
                                            className="search-icon"
                                            onClick={handleSearchClick} style={{ fontSize: '26px' }}
                                        />
                                    )}
                                </div>
                                &emsp;
                                <Link to="/shopingcart" className="headercart">
                                    <span className="picturecart">
                                        <span className="cc">
                                            <Badge count={0} showZero>
                                                <ShoppingCartOutlined style={{ color: 'black', fontSize: '26px' }} />
                                            </Badge>
                                        </span>
                                    </span>
                                </Link>
                                &emsp;
                                {isLoggedIn ? (
                                    <span className="pictureperson" onClick={handleDropdownToggle}>
                                        <UserOutlined style={{ color: 'black', fontSize: '26px' }} />
                                    </span>
                                ) : null}

                                {isLoggedIn ? ( // Render dropdown only if the user is logged in
                                    <div className={`dropdown-menu ${showDropdown ? "show" : ""}`} style={{ backgroundColor: '#FFF6E5' }} >
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <UserOutlined /> Đăng xuất
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <FileDoneOutlined /> Đơn đặt hàng
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <HeartOutlined /> Danh sách yêu thích
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <CommentOutlined /> Nhận xét của tôi
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <StarOutlined /> Dành cho vip
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            <BankOutlined /> Những luật trong khi đặt phòng
                                        </button>
                                        <button type="button" className="btn btn-primary" style={{ color: 'black', display: 'flex' }} onClick={logout}>
                                            <div style={{ border: '1px solid black', fontSize: '20px', borderRadius: '50px', marginRight: 18, width: 25, height: 25 }}>
                                                <div style={{ marginLeft: 8 }}>?</div></div>
                                            Những câu hỏi thắc mắc
                                        </button>
                                        <Link to="/user/propreties" style={{ textDecoration: 'none' }}>
                                            <button type="button" className="btn btn-primary" style={{ color: 'black' }}>
                                                Hồ sơ của tôi
                                            </button>
                                        </Link>
                                    </div>
                                ) : <div>   <Link to="/login"><Button type="primary" style={{ backgroundColor: '#FF7D63', color: 'white' }}>Đăng nhập</Button></Link> &emsp;
                                    <Button type="primary" style={{ backgroundColor: '#FF7D63', color: 'white' }}>Đăng ký</Button>
                                </div>}
                            </div>
                        </div>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <Modal title="welcom" style={{ top: 20 }}
                open={isModalOpen} onOk={handleOk} onCancel={handleCancel} width={800} >
                <LoginComponent />
            </Modal>
        </header >
    )
}
export default HeaderUser