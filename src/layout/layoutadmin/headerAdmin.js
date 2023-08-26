import React from 'react';
import { Layout, Menu } from 'antd';
import Title from 'antd/es/typography/Title';
import {
    AppleOutlined
} from '@ant-design/icons';
const { Header } = Layout;
const items1 = ['1', '2', '3'].map((key) => ({
    key,
    label: `nav ${key}`,
}));
const HeaderAdmin = () => {
    return (
        <Header style={{ display: 'flex', alignItems: 'center' }}>
            <div className="demo-logo" />
            <AppleOutlined style={{ fontSize: '28px', color: 'white' }} />&emsp;<Title level={3} style={{ marginTop: '13px', color: 'white' }}>IPShop chuyên bán hàng uy tín</Title>
        </Header>
    )
}
export default HeaderAdmin