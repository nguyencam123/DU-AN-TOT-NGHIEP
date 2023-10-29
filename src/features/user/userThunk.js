// src/features/user/userThunk.js
import { adminloginSuccess, loginSuccess, logout, partnerloginSuccess } from './userSlice';
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
    const response = await axios.post('http://localhost:8080/api/v1/login', {
      uname: username,
      pass: password,
    });
    const accounts = response.data;
    if (accounts.success) {
      dispatch(loginSuccess({ user: accounts }));
      localStorage.setItem('isLoggedIn', 'true');
      openNotificationlogin()
      dispatch(loginSuccess({ user: accounts, userData: accounts }))
    } else {
      openNotification()
    }
    //   dispatch(adminloginSuccess({ admin: accounts }));
    //   localStorage.setItem('isAdmin', 'true');
    //   openNotificationadmin();

    //   dispatch(supperadminloginSuccess({ supperadmin: accounts }));
    //   localStorage.setItem('issupperAdmin', 'true');

    // openNotification()

  } catch (error) {
    console.error('Đăng nhập thất bại:', error);

  }
};
export const loginAdmin = (username, password) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v3/login', {
      uname: username,
      pass: password,
    });
    const accounts = response.data;
    if (accounts.success) {
      dispatch(adminloginSuccess({ admin: accounts }));
      localStorage.setItem('isAdmin', 'true');
      dispatch(adminloginSuccess({ admin: accounts, adminData: accounts }))
    } else {
      openNotification()
    }
  } catch (error) {
    console.error('Đăng nhập thất bại:', error);
    openNotification()
  }
};
export const loginpartner = (username, password) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v2/login', {
      uname: username,
      pass: password,
    });
    const accounts = response.data;
    if (accounts.success) {
      dispatch(partnerloginSuccess({ partner: accounts }));
      localStorage.setItem('partner', 'true');
      dispatch(partnerloginSuccess({ partner: accounts, partnerData: accounts.data }))
    } else {
      openNotification()
    }
  } catch (error) {
    console.error('Đăng nhập thất bại:', error);
    openNotification()

  }
};
export const logoutUser = () => (dispatch) => {
  dispatch(logout());
  localStorage.removeItem('isLoggedIn');
  localStorage.removeItem('isAdmin');
  localStorage.removeItem('partner');
};
