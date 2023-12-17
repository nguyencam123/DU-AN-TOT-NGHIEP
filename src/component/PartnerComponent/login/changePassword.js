import React from 'react';
import { Tabs, Typography, notification } from 'antd';
import { MDBBtn, MDBCol, MDBInput, MDBRow } from 'mdb-react-ui-kit';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import moment from 'moment';
import { ChangePasswordByPass, ChangePasswordSlice } from '../../../features/owner_homestay/changePassword/changPassword';
const onChange = (key) => {
    console.log(key);
};

const { Title } = Typography
const ChangePassword = () => {
    const userDetail = JSON.parse(localStorage.getItem('ownerDetail'));
    const namelocal = userDetail?.data.name;
    const idowner = userDetail?.data.id;
    const [name, setname] = useState('')
    const [birthday, setbirthday] = useState(856345)
    const [gender, setgender] = useState(true)
    const [address, setaddress] = useState('')
    const [phoneNumber, setphoneNumber] = useState(0)
    const [email, setemail] = useState('')
    const [password, setpassword] = useState('')
    const [newpassword, setnewpassword] = useState('')
    const [confirmpassword, setconfirmpassword] = useState('')
    const [identificationNumber, setidentificationNumber] = useState('')
    const handleDateChangestart = (dates) => {
        setbirthday(moment(dates).valueOf());
    };
    const [file, setFile] = useState([]);

    const handleFileChange = (e) => {
        let selectedFile = e.target.files;
        let fileList = [...selectedFile]; // Chuyển đổi FileList thành mảng
        setFile(fileList);
    };
    const navigate = useNavigate()
    const openNotification = () => {
        notification.open({
            message: 'Thông báo',
            description:
                'Cập nhật thông tin thành công',
            onClick: () => {
                console.log('Notification Clicked!');
            },
        });
    };
    const openNotificationChangePass = () => {
        notification.open({
            message: 'Thông báo',
            description:
                'Mật khẩu mới và mật khẩu xác nhận phải giống nhau',
            onClick: () => {
                console.log('Notification Clicked!');
            },
        });
    };
    const formChangePass = {
        currentPassword: password,
        newPassword: newpassword,
        confirmationPassword: confirmpassword
    }
    const formData = {
        username: userDetail?.data.username,
        birthday: 1234567890,
        name: userDetail?.data.name,
        gender: userDetail?.data.gender,
        address: userDetail?.data.address,
        phoneNumber: userDetail?.data.phoneNumber,
        email: userDetail?.data.email
    }
    const handleSubmitchange = async (e) => {
        console.log(formChangePass)
        e.preventDefault();
        if (newpassword !== confirmpassword) {
            openNotificationChangePass();
            return;
        }
        try {
            // Assuming ChangePasswordByPass function takes an object with a 'password' property
            await ChangePasswordByPass(formChangePass);
            openNotification();
        } catch (error) {
            console.error("Password change failed:", error);
        }
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await ChangePasswordSlice(formData, file, idowner);
            openNotification();
        } catch (error) {
            // Handle the error, show a notification, etc.
            console.error("Password change failed:", error);
        }
    };

    const items = [
        {
            key: '1',
            label: 'Thông tin tài khoản',
            children: <div>
                <Title level={3}>Thông tin cá nhân</Title>
                <Title level={5}>Cập nhật thông tin của bạn</Title>
                <form onSubmit={handleSubmit}>
                    <MDBRow>
                        <MDBCol col='6'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='Họ và Tên'
                                id='name'
                                type='text'
                                onChange={(e) => setname(e.target.value)}
                                defaultValue={namelocal}
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
                                defaultValue={userDetail?.data.address}
                                onChange={(e) => setaddress(e.target.value)}
                            />
                        </MDBCol>
                    </MDBRow>
                    <div style={{ width: '100%' }}><span style={{ width: 150 }}>Email:</span><span>{userDetail?.data.email}</span></div><br />
                    <div style={{ width: '100%' }}><span style={{ width: 150 }}>Số điện thoại :</span><span>{userDetail?.data.phoneNumber}</span></div><br />
                    <div style={{ width: '100%' }}><span style={{ width: 150 }}>GIới tính :</span><span>{userDetail?.data.gender == true ? 'nam' : 'nữ'}</span></div><br />
                    {/* <input type='file' accept="image/*" onChange={handleFileChange} /> */}
                    <MDBBtn type="submit" className='w-100 mb-4' size='md' style={{ marginTop: 10 }} onClick={handleSubmit}>Lưu</MDBBtn>
                </form>
            </div>,
        },
        {
            key: '2',
            label: 'Đặt lại mật khẩu',
            children: <form>
                <MDBInput wrapperClass='mb-4' label='Mật khẩu cũ' id='password' type='password' required
                    onChange={(e) => setpassword(e.target.value)} />
                <MDBInput wrapperClass='mb-4' label='Mật khẩu mới' id='newpassword' type='password' required
                    onChange={(e) => setnewpassword(e.target.value)} />
                <MDBInput wrapperClass='mb-4' label='Xác nhận mật khẩu' id='confirmpassword' type='password' required
                    onChange={(e) => setconfirmpassword(e.target.value)} />
                <MDBBtn className='w-100 mb-4' size='md' style={{ marginTop: 10 }} onClick={handleSubmitchange}>Đổi mật khẩu</MDBBtn>
            </form>,
        },
        {
            key: '3',
            label: 'Thông tin thông báo',
            children: <div style={{ width: '100%', height: 200, border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 20px 10px 20px', backgroundColor: 'rgba(255,255,255,1.00)' }}>
                <Title level={4}>Thông báo email</Title>
                <div>Tùy chọn email:chúng tôi sẽ gửi các thông báo về thanh toán,đổi mật khẩu quên mật khẩu vào email: <Title level={5}>{userDetail?.data.email}</Title></div>
            </div>,
        },
    ];
    return (
        <div>
            <Title level={3}>Cài đặt</Title>
            <Tabs defaultActiveKey="1" items={items} onChange={onChange} />;
        </div>
    )
}
export default ChangePassword;