import './header.css';
import 'bootstrap/dist/css/bootstrap.css';
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
  StarOutlined,
  BankOutlined
} from '@ant-design/icons';
import { Link } from 'react-router-dom';
import { logoutUser } from '../../features/user/userThunk';
import { Avatar, Badge, Input, Button } from 'antd';
import { useSelector, useDispatch } from 'react-redux';
import React, { useEffect, useState } from 'react';
import { Modal, Typography } from 'antd';
import { useNavigate } from 'react-router-dom';
import LoginComponent from '../../component/login/Login';
import iconplane from '../../assets/svg/Iconka-Business-Finance-Plane.512.png';
import logotravel from '../../assets/svg/Rectangle 3.svg';
import { MDBIcon } from 'mdb-react-ui-kit';

const { Title } = Typography;

const HeaderUser = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showDropdown, setShowDropdown] = useState(false);
  const navigate = useNavigate();
  const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
  const userDetail = JSON.parse(localStorage.getItem('userDetail'));
  const userId = userDetail?.data.id; const statusUser = JSON.parse(localStorage.getItem('userDetail'))?.success;
  const dispatch = useDispatch();
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
    if (statusUser) {
      setIsModalOpen(false);
    }
  }, [statusUser]);
  const handleDropdownToggle = () => {
    if (!statusUser) {
      showModal(); // Redirect to /login if not logged in
    } else {
      setShowDropdown(!showDropdown); // Toggle the dropdown if logged in
    }
  };
  const logout = () => {
    dispatch(logoutUser());
    navigate('/login');
  };
  const booking = () => {
    navigate(`/booking/${userId}`);
  };
  const cart = () => {
    navigate(`/shopingcart/${userId}`);
  };
  return (
    <header>
      <Navbar collapseOnSelect expand='lg'>
        <Container>
          <Navbar.Brand href='https://html.design/preview/?theme=timups'>
            {/* <div className="parent-box">
                            <div className="box-fly-icon">
                                <div className="parent-plane-icon"><img className="plane-icon" src={iconplane} style={{ color: 'white' }} /></div>
                            </div>
                            <Title level={2} style={{ color: 'blue' }}>Travel</Title>
                        </div> */}
            <img
              src='https://res.cloudinary.com/dcwkiozwf/image/upload/v1700721918/homestay_images/lebvlnajzlmrpxxjps8i.png'
              style={{ with: '70px', height: '70px' }}
            />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls='responsive-navbar-nav' />
          <Navbar.Collapse id='responsive-navbar-nav'>
            <Nav className='me-auto'>
              <Link to={'/'} className='textnabar'>
                <strong>Trang chủ</strong>
              </Link>
              <Link to={'/home-stay'} className='textnabar'>
                <strong>Homestay</strong>
              </Link>
              <Link to={'/phieu-giam-gia'} className='textnabar'>
                <strong>Phiếu giảm giá</strong>
              </Link>
              <Link to={'/hop-tac'} className='textnabar'>
                <strong>Hợp tác với chúng tôi</strong>
              </Link>
            </Nav>
            <div className='headercart'>
              <div className='cartAndPerson' style={{ display: 'flex' }}>
                {/* <div className='search-bar-container'>
                  {searchVisible ? (
                    <div className='search-input-container'>
                      <Input
                        placeholder='Tìm kiếm...'
                        value={searchValue}
                        onChange={handleInputChange}
                      />
                      <Button type='primary' onClick={handleSearch}>
                        Tìm kiếm
                      </Button>
                    </div>
                  ) : (
                    <SearchOutlined
                      className='search-icon'
                      onClick={handleSearchClick}
                      style={{ fontSize: '26px' }}
                    />
                  )}
                </div> */}
                &emsp;
                <Link to='/shopingcart' className='headercart'>
                  <span className='picturecart'>
                    <span className='cc'>
                      <Badge count={0} showZero>
                        <ShoppingCartOutlined
                          style={{ color: 'black', fontSize: '26px' }}
                        />
                      </Badge>
                    </span>
                  </span>
                </Link>
                &emsp;
                {statusUser ? (
                  <span
                    className='pictureperson'
                    onClick={handleDropdownToggle}
                  >
                    <UserOutlined
                      style={{ color: 'black', fontSize: '26px' }}
                    />
                  </span>
                ) : null}
                {statusUser ? ( // Render dropdown only if the user is logged in
                  <div
                    className={`dropdown-menu ${showDropdown ? 'show' : ''}`}
                    style={{
                      backgroundColor: '#FFFFFF',
                      width: 200,
                      marginLeft: 'auto',
                      marginRight: 80
                    }}
                  >
                    <button
                      type='button'
                      className='btn btn-primary'
                      style={{ color: 'black' }}
                      onClick={logout}
                    >
                      <UserOutlined /> Đăng xuất
                    </button>
                    <button
                      type='button'
                      className='btn btn-primary'
                      style={{ color: 'black' }}
                      onClick={booking}
                    >
                      <FileDoneOutlined /> Đơn đặt hàng
                    </button>
                    <Link to={'/userComment'}>
                    <button
                      type='button'
                      className='btn btn-primary'
                      style={{ color: 'black' }}
                    >
                      <CommentOutlined /> Nhận xét của tôi
                    </button>
                    </Link>
                    <Link to={'/RulesWhile'}>
                      <button
                        type='button'
                        className='btn btn-primary'
                        style={{ color: 'black' }}

                      >
                        <BankOutlined />Những luật trong khi đặt homestay
                      </button>
                    </Link>
                    <Link to={'/supporteduser'}>
                      <button
                        type='button'
                        className='btn btn-primary'
                        style={{ color: 'black', display: 'flex' }}

                      >
                        <MDBIcon fas icon='question' style={{ marginTop: 4 }} />
                        &nbsp; Những câu hỏi thắc mắc
                      </button>
                    </Link>
                    <Link
                      to='/user/propreties'
                      style={{ textDecoration: 'none' }}
                    >
                      <button
                        type='button'
                        className='btn btn-primary'
                        style={{ color: 'black' }}
                      >
                        Hồ sơ của tôi
                      </button>
                    </Link>
                  </div>
                ) : (
                  <div>
                    {' '}
                    <Link to='/login'>
                      <Button
                        type='primary'
                        style={{ backgroundColor: '#FF7D63', color: 'white' }}
                      >
                        Đăng nhập
                      </Button>
                    </Link>{' '}
                    &emsp;
                    <Link to='/register'>
                      <Button
                        type='primary'
                        style={{ backgroundColor: '#FF7D63', color: 'white' }}
                      >
                        Đăng ký
                      </Button>
                    </Link>
                  </div>
                )}
              </div>
            </div>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <Modal
        title='welcom'
        style={{ top: 20 }}
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        width={800}
      >
        <LoginComponent />
      </Modal>
    </header>
  );
};
export default HeaderUser;
