import React, { useState } from 'react'; // Đảm bảo bạn đã import useState
import { useDispatch, useSelector } from 'react-redux';
import { loginUser, logoutUser } from '../../features/user/userThunk';
import "./Login.css";
import { Button } from 'antd';
import { FacebookOutlined, TwitterOutlined, GoogleOutlined, InstagramOutlined } from '@ant-design/icons'
import {
    MDBContainer,
    MDBTabs,
    MDBTabsItem,
    MDBTabsLink,
    MDBTabsContent,
    MDBTabsPane,
    MDBBtn,
    MDBIcon,
    MDBInput,
    MDBCheckbox
}
    from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import { notification } from 'antd';

const Context = React.createContext({
    name: 'Default',
});
function LoginComponent() {
    const navigate = useNavigate()
    const dispatch = useDispatch();
    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const isAdmin = useSelector((state) => state.user.isAdmin);
    const issupperAdmin = useSelector((state) => state.user.issupperAdmin);


    const [username, setUsername] = useState(''); // Thêm state cho username
    const [password, setPassword] = useState(''); // Thêm state cho password
    const [size, setSize] = useState('large');
    const openNotificationadmin = () => {
        notification.open({
            message: 'Thông báo',
            description:
                'chào mừng bạn đến với trang quản trị trang web',
            onClick: () => {
                console.log('Notification Clicked!');
            },
        });
    };
    const handleLogin = async () => {
        // Gọi loginUser thunk và truyền vào username và password
        dispatch(loginUser(username, password));
        if (isLoggedIn) {
            navigate('/')
        } else if (isAdmin) {
            navigate('/admin/createform')
            openNotificationadmin()
        }
    };



    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    const handleLogout = () => {
        dispatch(logoutUser());
    };
    const [justifyActive, setJustifyActive] = useState('tab1');;

    const handleJustifyClick = (value) => {
        if (value === justifyActive) {
            return;
        }

        setJustifyActive(value);
    };

    return (
        <div>
            <MDBContainer className="p-3 my-5 d-flex flex-column w-50">

                <MDBTabs pills justify className='mb-3 d-flex flex-row justify-content-between'>
                    <MDBTabsItem>
                        <MDBTabsLink onClick={() => handleJustifyClick('tab1')} active={justifyActive === 'tab1'}>
                            Login
                        </MDBTabsLink>
                    </MDBTabsItem>
                    <MDBTabsItem>
                        <MDBTabsLink onClick={() => handleJustifyClick('tab2')} active={justifyActive === 'tab2'}>
                            Register
                        </MDBTabsLink>
                    </MDBTabsItem>
                </MDBTabs>

                <MDBTabsContent>

                    <MDBTabsPane show={justifyActive === 'tab1'}>

                        <div className="text-center mb-3">
                            <p>Sign in with:</p>

                            <div className='d-flex justify-content-between mx-auto' style={{ width: '40%' }}>
                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <FacebookOutlined />
                                </MDBBtn>
                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <TwitterOutlined />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <GoogleOutlined />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <InstagramOutlined />
                                </MDBBtn>
                            </div>

                            <p className="text-center mt-3">or:</p>
                        </div>

                        <MDBInput wrapperClass='mb-4' label='Username' type='email' value={username}
                            onChange={(e) => setUsername(e.target.value)} />
                        <MDBInput wrapperClass='mb-4' label='Password' type='password' value={password}
                            onChange={(e) => setPassword(e.target.value)} />

                        <div className="d-flex justify-content-between mx-4 mb-4">
                            <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
                            <a href="!#">Forgot password?</a>
                        </div>
                        <Button type="primary" size={size} style={{ width: '430px' }} onClick={handleLogin}>
                            Sign in
                        </Button>
                        <button onClick={handleLogout}>Logout</button>

                        <p className="text-center">Not a member? <a href="#!">Register</a></p>

                    </MDBTabsPane>

                    <MDBTabsPane show={justifyActive === 'tab2'}>

                        <div className="text-center mb-3">
                            <p>Sign un with:</p>

                            <div className='d-flex justify-content-between mx-auto' style={{ width: '40%' }}>
                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <FacebookOutlined />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <TwitterOutlined />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <GoogleOutlined />
                                </MDBBtn>

                                <MDBBtn tag='a' color='none' className='m-1' style={{ color: '#1266f1' }}>
                                    <InstagramOutlined />
                                </MDBBtn>
                            </div>

                            <p className="text-center mt-3">or:</p>
                        </div>

                        <MDBInput wrapperClass='mb-4' label='Name' id='form1' type='text' />
                        <MDBInput wrapperClass='mb-4' label='Username' id='form1' type='text' />
                        <MDBInput wrapperClass='mb-4' label='Email' id='form1' type='email' />
                        <MDBInput wrapperClass='mb-4' label='Password' id='form1' type='password' />

                        <div className='d-flex justify-content-center mb-4'>
                            <MDBCheckbox name='flexCheck' id='flexCheckDefault' label='I have read and agree to the terms' />
                        </div>

                        <MDBBtn className="mb-4 w-100">Sign up</MDBBtn>

                    </MDBTabsPane>

                </MDBTabsContent>

            </MDBContainer>
        </div>
    );
}

export default LoginComponent;