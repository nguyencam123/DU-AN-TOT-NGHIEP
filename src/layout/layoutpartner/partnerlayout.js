import { Outlet } from 'react-router-dom';
import Headerpartner from './headerpartner';
import Siderpartner from './siderpartner';
import { Layout } from 'antd';
const { Header, Content } = Layout;


const PartnerLayout = ({ children }) => {
    return (
        <div style={{ backgroundColor: '#f5f5f5' }}>
            <Headerpartner />

            <div className="admin-content">
                <Outlet />
            </div>
            <Siderpartner />
        </div>
    );
}

export default PartnerLayout;
