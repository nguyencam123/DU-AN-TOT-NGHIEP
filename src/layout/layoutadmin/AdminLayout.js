import { Outlet } from 'react-router-dom';
import HeaderAdmin from './headerAdmin';
import SiderAdmin from './siderAdmin';
import { Layout } from 'antd';
const { Header, Content } = Layout;


const AdminLayout = ({ children }) => {
    return (
        <div style={{ backgroundColor: '#f5f5f5' }}>
            {/* Header của trang quản trị */}
            <HeaderAdmin />

            {/* Nội dung của trang quản trị */}
            <div className="admin-content">
                <Outlet />
            </div>
            {/* Footer của trang quản trị */}
            <SiderAdmin />
        </div>
    );
}

export default AdminLayout;
