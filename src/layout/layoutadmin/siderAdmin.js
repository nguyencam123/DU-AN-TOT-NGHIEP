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
import CategoryList from '../../component/category/categorylist';
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
    getItem('Quản trị sản phẩm', '1', <PieChartOutlined />, 'admin/createform', <AddProductForm />),
    getItem('Quản trị loại sản phẩm', '2', <DesktopOutlined />, 'admin/category', <CategoryList />),
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
                    padding: '12px 0',
                    background: 'colorBgContainer',
                }}
            >
                <Sider
                    style={{
                        background: 'colorBgContainer'

                    }}
                    width={250}
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
                        minHeight: '88vh',
                        backgroundColor: '#ffffff',
                        marginLeft: '10px',
                    }}
                >
                    <Routes>
                        <React.Fragment>
                            <Route path='admin/createform' element={<AddProductForm />} />
                            <Route path='admin/category' element={<CategoryList />} />
                        </React.Fragment>
                    </Routes>
                </Content>
            </Layout>
        </Content>
    );
}

export default SiderAdmin;

