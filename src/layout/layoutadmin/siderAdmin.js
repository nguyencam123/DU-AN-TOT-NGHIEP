import React from 'react';
import { Breadcrumb, Layout, Menu } from 'antd';
import {
    DesktopOutlined,
    FileOutlined,
    PieChartOutlined,
    TeamOutlined,
    UserOutlined,
} from '@ant-design/icons';
import { Link, Route, Routes } from 'react-router-dom'; // Import thư viện Link và Route
import AddProductForm from '../../component/product/productadd';
const { Header, Content, Sider } = Layout;


function getItem(label, key, icon, route) {
    return {
        key,
        icon,
        label,
        route,
    };
}

const items = [
    getItem('Quản trị sản phẩm', '1', <PieChartOutlined />, 'admin/createform'),
    getItem('Option 2', '2', <DesktopOutlined />, 'admin/createform'),
];

const SiderAdmin = () => {
    return (
        <Content
            style={{
                padding: '0 10px',
            }}
        >
            <Layout
                style={{
                    padding: '24px 0',
                    background: 'colorBgContainer',
                }}
            >
                <Sider
                    style={{
                        background: 'colorBgContainer'

                    }}
                    width={200}
                >
                    <Menu
                        mode="inline"
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
                        style={{
                            height: '100%',
                        }}
                    >
                        {items.map((item) => (
                            <Menu.Item key={item.key} icon={item.icon}>
                                <Link to={item.route} style={{ textDecoration: 'none' }}>{item.label}</Link>
                            </Menu.Item>
                        ))}
                    </Menu>
                </Sider>
                <Content
                    style={{
                        padding: '0 24px',
                        minHeight: '80vh',
                        backgroundColor: '#ffffff',
                        marginLeft: '10px',
                    }}
                >
                    <Routes>
                        {items.map((item) => (
                            <Route path={item.route} element={<AddProductForm />} />
                        ))}
                    </Routes>
                </Content>
            </Layout>
        </Content>
    );
}

export default SiderAdmin;

