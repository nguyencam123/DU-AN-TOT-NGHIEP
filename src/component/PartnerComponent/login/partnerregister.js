import React, { useState } from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBCard,
    MDBCardBody,
    MDBCol,
    MDBRow,
    MDBInput,
    MDBCheckbox,
    MDBIcon,
    MDBRadio
}
    from 'mdb-react-ui-kit';
import { DatePicker } from 'antd';

import { useFormik } from 'formik';
import * as Yup from 'yup';

const validationSchema = Yup.object().shape({
    name: Yup.string().required('Họ và tên không được để trống'),
    birthday: Yup.date().required('Ngày sinh không được để trống'),
    gender: Yup.boolean().required('Giới tính không được để trống'),
    address: Yup.string().required('Địa chỉ không được để trống'),
    phoneNumber: Yup.string().required('Số điện thoại không được để trống'),
    email: Yup.string().email('Email không hợp lệ').required('Email không được để trống'),
    username: Yup.string().required('Tài khoản không được để trống'),
    password: Yup.string().required('Mật khẩu không được để trống')
});

const PartnerRegister = () => {
    const [selectedDate, setSelectedDate] = useState(12345678);

    const formik = useFormik({
        initialValues: {
            name: '',
            birthday: '',
            gender: true,
            address: '',
            phoneNumber: '',
            email: '',
            username: '',
            password: ''
        },
        validationSchema: validationSchema,
        onSubmit: (values) => {
            // Gửi dữ liệu đăng ký đến API ở đây
            fetch('http://localhost:8080/api/v2/login/registers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(values)
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        }
    });
    const handleDateChange = (date) => {
        setSelectedDate(parseFloat(date));
        formik.setFieldValue('birthday', parseFloat(date)); // Cập nhật giá trị của formik khi ngày thay đổi
    }
    return (
        <MDBContainer fluid style={{ width: '60%' }}>
            <MDBCard className='mx-5 mb-5 p-5 shadow-5' style={{ marginTop: '100px', background: 'hsla(0, 0%, 100%, 0.8)', backdropFilter: 'blur(30px)' }}>
                <MDBCardBody className='p-5 text-center'>
                    <h2 className="fw-bold mb-5">Đăng ký ngay</h2>
                    <form onSubmit={formik.handleSubmit}>
                        <MDBRow>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Họ và Tên'
                                    id='name'
                                    type='text'
                                    {...formik.getFieldProps('name')}
                                />
                                {formik.touched.name && formik.errors.name ? <div className="text-danger">{formik.errors.name}</div> : null}
                            </MDBCol>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Địa chỉ'
                                    id='address'
                                    type='text'
                                    {...formik.getFieldProps('address')}
                                />
                                {formik.touched.address && formik.errors.address ? <div className="text-danger">{formik.errors.address}</div> : null}
                            </MDBCol>
                        </MDBRow>
                        <MDBRow>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Tài khoản'
                                    id='username'
                                    type='text'
                                    {...formik.getFieldProps('username')}
                                />
                                {formik.touched.username && formik.errors.username ? <div className="text-danger">{formik.errors.username}</div> : null}
                            </MDBCol>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Mật khẩu'
                                    id='password'
                                    type='password'
                                    {...formik.getFieldProps('password')}
                                />
                                {formik.touched.password && formik.errors.password ? <div className="text-danger">{formik.errors.password}</div> : null}
                            </MDBCol>
                        </MDBRow>
                        <MDBInput wrapperClass='mb-4' label='Email' id='email' type='email' {...formik.getFieldProps('email')} />
                        {formik.touched.email && formik.errors.email ? <div className="text-danger">{formik.errors.email}</div> : null}
                        <MDBInput wrapperClass='mb-4' label='Số điện thoại' id='phoneNumber' type='phoneNumber' {...formik.getFieldProps('phoneNumber')} />
                        {formik.touched.phoneNumber && formik.errors.phoneNumber ? <div className="text-danger">{formik.errors.phoneNumber}</div> : null}
                        <MDBRow>
                            <MDBCol col='6'>
                                Giới tính &emsp;&emsp;
                                <label>
                                    <input
                                        type="radio"
                                        name="gender"
                                        value={true}
                                        onChange={formik.handleChange}
                                        checked={formik.values.gender === true}
                                    />
                                    Nam
                                </label>
                                &emsp;
                                <label>
                                    <input
                                        type="radio"
                                        name="gender"
                                        value={false}
                                        onChange={formik.handleChange}
                                        checked={formik.values.gender === false}
                                    />
                                    Nữ
                                </label>
                                {formik.touched.gender && formik.errors.gender ? <div className="text-danger">{formik.errors.gender}</div> : null}
                            </MDBCol>
                            <MDBCol col='6'>
                                <div style={{ display: 'flex' }}>Ngày sinh &ensp;
                                    <DatePicker
                                        style={{ width: '86%', height: 36 }}
                                        selected={selectedDate}
                                        onChange={handleDateChange}
                                        dateFormat="dd/MM/yyyy"
                                    />
                                </div>
                                {formik.touched.birthday && formik.errors.birthday ? <div className="text-danger">{formik.errors.birthday}</div> : null}
                            </MDBCol>
                        </MDBRow>

                        <MDBBtn type="submit" className='w-100 mb-4' size='md'>Đăng ký</MDBBtn>
                    </form>
                </MDBCardBody>
            </MDBCard>
        </MDBContainer>
    );
}

export default PartnerRegister;