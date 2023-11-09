import React, { useState } from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBCard,
    MDBCardBody,
    MDBInput,
    MDBCheckbox,
    MDBIcon
}
    from 'mdb-react-ui-kit';
import { Button, DatePicker, notification } from 'antd';
import moment from 'moment';
import "./Login.css";
import { useNavigate } from 'react-router-dom';
const Register = () => {
    const navigate = useNavigate()
    const [name, setname] = useState('')
    const [birthday, setbirthday] = useState(856345)
    const [gender, setgender] = useState(true)
    const [address, setaddress] = useState('')
    const [phoneNumber, setphoneNumber] = useState(0)
    const [email, setemail] = useState('')
    const [username, setusername] = useState('')
    const [password, setpassword] = useState('')
    const [identificationNumber, setidentificationNumber] = useState('')
    const handleDateChangestart = (dates) => {
        setbirthday(moment(dates).valueOf());
    };
    const [api, contextHolder] = notification.useNotification();
    const openNotification = () => {
        notification.open({
            message: 'Thông báo',
            description:
                'Đăng ký thành công',
            onClick: () => {
                console.log('Notification Clicked!');
            },
        });
    };
    const formData = {
        name: name,
        birthday: birthday,
        gender: gender,
        address: address,
        phoneNumber: phoneNumber,
        email: email,
        username: username,
        password: password,
        identificationNumber: identificationNumber,
        point: 9
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        if (password.length < 8 || username.length < 8) {
            alert('Mật khẩu và tài khoản phải có ít nhất 8 ký tự');
            return;
        }
        else if (phoneNumber.toString().length !== 10) {
            alert('Số điện thoại phải có đúng 10 số');
            return;
        }
        else {
            fetch('http://localhost:8080/api/v1/login/registers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then(response => response.json())
                .then(data => {
                    openNotification()
                    navigate('/login')
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    };
    return (
        <MDBContainer fluid className='p-4'>

            <MDBRow>

                <MDBCol md='6' className='text-center text-md-start d-flex flex-column justify-content-center'>
                    <h1 className="my-5 display-3 fw-bold ls-tight px-3">
                        Với những ưu đãi <br />
                        <span className="text-primary">tốt nhất dành cho bạn</span>
                    </h1>
                    <p className='px-3' style={{ color: 'hsl(217, 10%, 50.8%)' }}>
                        Chào mừng bạn đến với trang web đặt homestay của chúng tôi! Chúng tôi cung cấp những trải nghiệm ấm cúng và tiện nghi tại các điểm đến tuyệt vời trên khắp Việt Nam. Tận hưởng những ưu đãi tốt nhất và tạo ra những kỷ niệm đáng nhớ cùng chúng tôi.
                    </p>
                </MDBCol>

                <MDBCol md='6'>

                    <MDBCard className='my-5'>
                        <MDBCardBody className='p-5'>

                            <form onSubmit={handleSubmit}>
                                <MDBRow>
                                    <MDBCol col='6'>
                                        <MDBInput
                                            wrapperClass='mb-4'
                                            label='Họ và Tên'
                                            id='name'
                                            type='text'
                                            onChange={(e) => setname(e.target.value)}
                                            required
                                        />
                                    </MDBCol>
                                    <MDBCol col='6'>
                                        <MDBInput
                                            wrapperClass='mb-4'
                                            label='Địa chỉ'
                                            id='address'
                                            type='text'
                                            required

                                            onChange={(e) => setaddress(e.target.value)}
                                        />
                                    </MDBCol>
                                </MDBRow>
                                <MDBRow>
                                    <MDBCol col='6'>
                                        <MDBInput
                                            wrapperClass='mb-4'
                                            label='Tài khoản'
                                            id='username'
                                            type='text'
                                            required

                                            onChange={(e) => setusername(e.target.value)}
                                        />
                                    </MDBCol>
                                    <MDBCol col='6'>
                                        <MDBInput
                                            wrapperClass='mb-4'
                                            label='Mật khẩu'
                                            id='password'
                                            type='password'
                                            required

                                            onChange={(e) => setpassword(e.target.value)}
                                        />
                                    </MDBCol>
                                </MDBRow>
                                <MDBInput wrapperClass='mb-4' required label='Email' id='email' type='email'
                                    onChange={(e) => setemail(e.target.value)} />
                                <MDBInput wrapperClass='mb-4' label='Số điện thoại' id='phoneNumber' type='number' required
                                    onChange={(e) => setphoneNumber(e.target.value)} />
                                <MDBInput wrapperClass='mb-4' label='Số căn cước công dân' id='identificationNumber' type='number' required
                                    onChange={(e) => setidentificationNumber(e.target.value)} />
                                <MDBRow>
                                    <MDBCol col='6'>
                                        Giới tính &emsp;&emsp;
                                        <label>
                                            <input
                                                type="radio"
                                                name="gender"
                                                value={true}
                                                defaultChecked={true}
                                            />
                                            Nam
                                        </label>
                                        &emsp;
                                        <label>
                                            <input
                                                type="radio"
                                                name="gender"
                                                value={false}
                                            />
                                            Nữ
                                        </label>
                                    </MDBCol>
                                    <MDBCol col='6'>
                                        <div style={{ display: 'flex' }}>Ngày sinh &ensp;
                                            <DatePicker
                                                style={{ width: '86%', height: 36 }}
                                                dateFormat="dd/MM/yyyy"
                                                required
                                                onChange={handleDateChangestart}
                                            />
                                        </div>
                                    </MDBCol>
                                </MDBRow>
                                <MDBBtn type="submit" className='w-100 mb-4' size='md'>Đăng ký</MDBBtn>
                            </form>

                        </MDBCardBody>
                    </MDBCard>

                </MDBCol>

            </MDBRow>

        </MDBContainer>
    )
}
export default Register