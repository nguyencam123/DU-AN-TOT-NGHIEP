import { useEffect, useState } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';

const ProtectedRoute = ({ partnerOnly, adminOnly, ...rest }) => {
    const [isLoading, setIsLoading] = useState(true);

    const isLoggedIn = useSelector((state) => state.user.isLoggedIn);
    const isAdmin = useSelector((state) => state.user.isAdmin);
    const isPartner = useSelector((state) => state.user.ispartner);

    useEffect(() => {
        setIsLoading(false);
    }, []);

    if (isLoading) {
        return null; // Render nothing while loading
    }

    // if (!isLoggedIn) {
    //     return <Navigate to="/login" />;
    // }

    if (adminOnly && !isAdmin) {
        return <Navigate to="/error-role" replace />;
    }
    if (partnerOnly && !isPartner) {
        return <Navigate to="/hop-tac/login" replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;
