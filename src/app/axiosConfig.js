import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/v2', // Đặt URL cơ sở của API của bạn
});

const userDetail = JSON.parse(localStorage.getItem('userDetail'));
const token = userDetail?.data.token;

// Thiết lập tiêu đề mặc định cho tất cả các yêu cầu
instance.defaults.headers.common['Content-Type'] = 'application/json';
instance.defaults.headers.common['Authorization'] = `Bearer ${token}`;


export default instance;
