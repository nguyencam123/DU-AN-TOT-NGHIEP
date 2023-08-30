import "./header.css"
import "bootstrap/dist/css/bootstrap.css";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { UserOutlined, ShoppingCartOutlined } from "@ant-design/icons";
import { Link } from "react-router-dom";
import { logoutUser } from "../../features/user/userThunk";
import { Avatar, Badge } from 'antd';
import { useSelector, useDispatch } from "react-redux";
import React, { useEffect, useState } from 'react';
import { Modal } from 'antd';
import { useNavigate } from "react-router-dom";
import LoginComponent from "../../component/login/Login";

const HeaderUser = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [showDropdown, setShowDropdown] = useState(false);
    const navigate = useNavigate()
    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const dispatch = useDispatch()
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
        navigate('/')
        setIsModalOpen(true)
    }
    return (
        <header>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="https://html.design/preview/?theme=timups">
                        CAMPHONES
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Link to={"/"} className="textnabar">
                                Home
                            </Link>
                            <Link to={"/watches"} className="textnabar">
                                Watches
                            </Link>
                            <Link to={"/about"} className="textnabar">
                                About
                            </Link>
                            <NavDropdown title="Contact US" id="collasible-nav-dropdown">
                                <NavDropdown.Item href="/action">Action</NavDropdown.Item>

                                <NavDropdown.Item href="/Anotheraction">
                                    Another action
                                </NavDropdown.Item>
                                <NavDropdown.Item href="/Something">Something</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="/Separatedlink">
                                    Separated link
                                </NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                        <div className="headercart">
                            <div className="cartAndPerson">
                                <Link to="/shopingcart" className="headercart">
                                    <span className="picturecart">
                                        <span className="cc">
                                            <Badge count={0} showZero>
                                                <ShoppingCartOutlined style={{ color: 'white', fontSize: '24px' }} />
                                            </Badge>
                                        </span>
                                    </span>
                                </Link>
                                &emsp;
                                <span className="pictureperson" onClick={handleDropdownToggle}>
                                    <UserOutlined style={{ color: 'white', fontSize: '24px' }} />
                                </span>
                                {isLoggedIn ? ( // Render dropdown only if the user is logged in
                                    <div className={`dropdown-menu ${showDropdown ? "show" : ""}`} >
                                        <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                                            Đăng xuất
                                        </button>
                                        <Link to="/user/propreties" style={{ textDecoration: 'none' }}>
                                            <button type="button" className="btn btn-primary" style={{ color: 'black' }}>
                                                Thông tin người dùng
                                            </button>
                                        </Link>
                                    </div>
                                ) : null}
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