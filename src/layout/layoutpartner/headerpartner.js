import React from 'react';
import { Layout, Menu } from 'antd';
import Title from 'antd/es/typography/Title';
import logotravel from "../../assets/svg/Rectangle 405.svg"
import {
    AppleOutlined
} from '@ant-design/icons';
const { Header } = Layout;
const items1 = ['1', '2', '3'].map((key) => ({
    key,
    label: `nav ${key}`,
}));
const Headerpartner = () => {
    return (
        <Header style={{ display: 'flex', alignItems: 'center' }}>
            <div className="demo-logo" />
            <Title level={3} style={{ marginTop: '13px', color: 'white' }}><img src={logotravel} />Trang quản trị Homestay của bạn</Title>
            <div className="layout-login" style={{ marginLeft: 'auto' }}><Title level={5} style={{ color: 'white' }}>Nguyễn Cầm (Đăng xuất)</Title></div>
        </Header>
    )
}
export default Headerpartner