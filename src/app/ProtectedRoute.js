// ProtectedRoute.js
import React from 'react';
import { useSelector } from 'react-redux';
import { Navigate, Outlet } from 'react-router-dom'; // Thêm Outlet vào imports
import { Route } from 'react-router-dom';

const ProtectedRoute = ({ adminOnly, ...rest }) => {
    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const isAdmin = useSelector((state) => state.user.isAdmin);

    // if (!isLoggedIn) {
    //     // Sử dụng Navigate trong phạm vi của Outlet
    //     return <Navigate to="/login" />;
    // }

    if (adminOnly && !isAdmin) {
        // Sử dụng Navigate trong phạm vi của Outlet
        return <Navigate to="/error-role" replace />;
    }

    // Sử dụng Outlet để hiển thị nội dung con
    return <Outlet />;
};

export default ProtectedRoute;
