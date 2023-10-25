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
import HomeStayProduct from '../../component/PartnerComponent/page/homestayProduct';
import HomeStayAdd from '../../component/PartnerComponent/page/homestayAdd';
import HomeStayConvenient from '../../component/PartnerComponent/page/homestayConvenient';
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
  { label: 'Homestay', key: '1', icon: <PieChartOutlined />, route: 'partner/homestay', component: <HomeStayProduct /> },
  { label: 'Đặt phòng', key: '2', icon: <DesktopOutlined />, route: 'partner/statistical', component: <HomeStayProduct /> },
  { label: 'Đăng homestay', key: '3', icon: <DesktopOutlined />, route: 'partner/add', component: <HomeStayAdd /> },
  { label: 'Thống kê', key: '4', icon: <DesktopOutlined />, route: 'partner/categor', component: <HomeStayProduct /> },
  { label: 'Tiện nghi', key: '5', icon: <DesktopOutlined />, route: 'partner/convenient', component: <HomeStayConvenient /> },

];


const Siderpartner = () => {
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
            {items.map(item => (
              <Route key={item.key} path={item.route} element={item.component} />
            ))}
          </Routes>
        </Content>
      </Layout>
    </Content>
  );
}

export default Siderpartner;

