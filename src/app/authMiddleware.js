import { redirectToLogin, redirectToAdmin } from './pathActions'; // Định nghĩa các action để chuyển hướng

export const AuthMiddleware = (store) => (next) => (action) => {
    const isLoggedIn = store.getState().user.isLoggedIn;
    const isAdmin = store.getState().user.isAdmin;
    const currentPath = window.location.pathname;

    if (currentPath.startsWith('/admin')) {
        // Người dùng chưa đăng nhập và truy cập vào trang /admin
        // Dispatch action để chuyển hướng về trang /login
        window.location.href = 'http://localhost:3000/login';
    } else if (!isLoggedIn && currentPath.includes('/cart/payment-info')) {
        // Người dùng chưa đăng nhập và truy cập vào trang đặt hàng
        // Dispatch action để chuyển hướng về trang /login
        return next(redirectToLogin());
    } else if (isAdmin && currentPath === '/login') {
        // Người dùng có quyền admin và truy cập vào trang /login
        // Dispatch action để chuyển hướng về trang /admin
        return next(redirectToAdmin());
    }

    return next(action);
};
