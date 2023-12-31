// src/features/user/userThunk.js
import { adminloginSuccess, loginSuccess, logout, supperadminloginSuccess } from './userSlice';
import axios from 'axios';
import { notification } from 'antd';

const openNotification = () => {
    notification.open({
        message: 'Thông báo',
        description:
            'Đăng nhập không thành công vì tài khoản hoặc mật khẩu không đúng',
        onClick: () => {
            console.log('Notification Clicked!');
        },
    });
};
const openNotificationlogin = () => {
    notification.open({
        message: 'Thông báo',
        description:
            'chào mừng bạn đến với trang web',
        onClick: () => {
            console.log('Notification Clicked!');
        },
    });
};
const openNotificationadmin = () => {
    notification.open({
        message: 'Thông báo',
        description:
            'chào mừng bạn đến với trang quản trị trang web',
        onClick: () => {
            console.log('Notification Clicked!');
        },
    });
};
export const loginUser = (username, password) => async (dispatch) => {
    try {
        const response = await axios.post('http://localhost:8080/api/viewaccount', {
            username: username,
            password: password,
        });

        const accounts = response.data;

        const matchedAccount = accounts.find((account) => account.username === username && account.password === password);
        if (matchedAccount) {
            if (matchedAccount.admin == 0) {
                dispatch(loginSuccess({ user: accounts }));
                localStorage.setItem('isLoggedIn', 'true');
                openNotificationlogin()
                dispatch(loginSuccess({ user: accounts, userData: matchedAccount }))
            } else if (matchedAccount.admin == 1) {
                dispatch(adminloginSuccess({ admin: accounts }));
                localStorage.setItem('isAdmin', 'true');
            } else {
                dispatch(supperadminloginSuccess({ supperadmin: accounts }));
                localStorage.setItem('issupperAdmin', 'true');
            }
        } else {
            // Xử lý khi đăng nhập không thành công
            openNotification()
        }
    } catch (error) {
        console.error('Đăng nhập thất bại:', error);
    }
};

export const logoutUser = () => (dispatch) => {
    dispatch(logout());
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('isAdmin');
    localStorage.removeItem('issupperAdmin');
};
