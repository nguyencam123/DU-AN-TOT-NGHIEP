import './App.css';
import ProductList from './component/product/ProductList';
import LoginComponent from './component/login/Login';
import LoginDetail from './component/login/LoginDetail';
import { BrowserRouter as Router, Routes, Route, Outlet, Navigate } from 'react-router-dom';
import ProtectedRoute from './app/ProtectedRoute';
import AdminLayout from './layout/layoutadmin/AdminLayout';
import UserLayout from './layout/layoutuser/UserLayout';
import Register from './component/login/register'
import Hotel from './component/user/hotelcomponent/hotel';
import ErrorLogin from './features/admin/errorLogin/error';
import Endow from './component/user/endow/endow';
import HomePartner from './component/PartnerComponent/homepartner';
import LoginPartner from './component/PartnerComponent/login/partnerlogin';
import { useSelector } from 'react-redux';
import LoginAdmin from './component/admin/login/loginadmin';
import PartnerLayout from './layout/layoutpartner/partnerlayout';
import { DetailHomestay } from './component/user/hotelcomponent/detailHomestay';
import { BookingHomestay } from './component/user/hotelcomponent/bookingHomestay';
import { BookingReviewHomestay } from './component/user/hotelcomponent/bookingReview';
import PartnerRegister from './component/PartnerComponent/login/partnerregister'
import { BookingSuccess } from './component/user/hotelcomponent/bookigSucces';

function App() {
  //map component user
  const isAdmin = useSelector((state) => state.user.isAdmin);

  const items = [
    { path: '', element: <ProductList /> },
    { path: 'user/propreties', element: <LoginDetail /> },
    { path: 'login', element: <LoginComponent /> },
    { path: 'register', element: <Register /> },
    { path: 'home-stay', element: <Hotel /> },
    { path: 'error-role', element: <ErrorLogin /> },
    { path: 'phieu-giam-gia', element: <Endow /> },
    { path: 'hop-tac', element: <HomePartner /> },
    { path: 'hop-tac/login', element: <LoginPartner /> },
    { path: 'login-admin', element: <LoginAdmin /> },
    { path: 'homestay/detail/:id', element: <DetailHomestay /> },
    { path: 'homestay/booking/:id', element: <BookingHomestay /> },
    { path: 'review/booking/:id', element: <BookingReviewHomestay /> },
    { path: 'hop-tac/register', element: <PartnerRegister /> },
    { path: 'booking/success', element: <BookingSuccess /> }
  ];
  //
  return (
    <>
      <Router>
        <Routes>
          {/* guest component */}
          <Route path="/*" element={<UserLayout />}>
            {items.map(item => (
              <Route path={item.path} element={item.element} />
            ))}
            {/* {isAdmin == true ?
              null : <Route path="admin/*" element={<Navigate to="/error-role" replace />} />
            } */}
          </Route>
          {/* map quyen voi url admin */}
          <Route path="/*" element={<PartnerLayout />}>
            <Route path="partner/*" element={<ProtectedRoute partnerOnly />} />
          </Route>
          <Route path="/*" element={<AdminLayout />}>
            <Route path="admin/*" element={<ProtectedRoute adminOnly />} />
            {/* <Route path="*" element={<Navigate to="/error-role" replace />} /> */}
            <Route path="error-role" element={<ErrorLogin />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}


export default App;
