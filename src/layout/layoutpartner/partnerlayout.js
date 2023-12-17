import { Outlet, useNavigate } from 'react-router-dom';
import Headerpartner from './headerpartner';
import Siderpartner from './siderpartner';
import { Layout } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { updateAxiosToken } from '../../app/axiosConfig';
import { checkToken } from '../../app/middleware';
const { Header, Content } = Layout;


const PartnerLayout = ({ children }) => {
    const dispatch = useDispatch()

    useEffect(() => {
        // Lấy token từ localStorage, Redux state, hoặc nơi khác
        const token = JSON.parse(localStorage.getItem('ownerDetail'))?.data?.token;
        // Gọi middleware để kiểm tra token
        dispatch(checkToken(token));
    }, [dispatch]);
    useEffect(() => {
        const storedToken = JSON.parse(localStorage.getItem('ownerDetail'))?.data?.token;

        if (storedToken) {
            updateAxiosToken(storedToken);
        }
    }, []);
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
