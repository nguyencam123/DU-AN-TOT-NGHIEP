import HeaderUser from './headeruser';
import FooterUser from './footeruser';
import { Layout } from 'antd';
import { Outlet } from 'react-router-dom';
import { useEffect } from 'react';
import { updateAxiosToken } from '../../app/axiosConfig';

const { Header, Content } = Layout;

const UserLayout = ({ children }) => {

    useEffect(() => {
        const storedToken = JSON.parse(localStorage.getItem('userDetail'))?.data?.token;
        updateAxiosToken(storedToken);
    }, []);
    return (
        <Layout>
            <HeaderUser />
            {/* Nội dung của trang quản trị */}
            <div className="admin-content">
                <Outlet />
            </div>
            {/* Footer của trang quản trị */}
            <FooterUser /> {/* Sử dụng AdminFooter */}
        </Layout>
    );
}

export default UserLayout;