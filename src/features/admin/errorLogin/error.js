import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Result } from 'antd';
const ErrorLogin = () => {
    const navigate = useNavigate(); // Use the useNavigate hook

    const navigatetologin = () => {
        navigate('/'); // Use navigate to go to the /login route
    }

    return (
        <Result
            status="403"
            title="403"
            subTitle="Xin lỗi,bạn không có quyền truy cập cập vào trang này"
            extra={<Button type="primary" onClick={navigatetologin}>Quay lại trang chủ</Button>}
        />
    )
}
export default ErrorLogin;