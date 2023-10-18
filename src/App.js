import './App.css';
import ProductList from './component/product/ProductList';
import LoginComponent from './component/login/Login';
import LoginDetail from './component/login/LoginDetail';
import { BrowserRouter as Router, Routes, Route, Outlet } from 'react-router-dom';
import ProtectedRoute from './app/ProtectedRoute';
import AdminLayout from './layout/layoutadmin/AdminLayout';
import UserLayout from './layout/layoutuser/UserLayout';
import Register from './component/login/register'
import Hotel from './component/user/hotelcomponent/hotel';
import ErrorLogin from './features/admin/errorLogin/error';
import Endow from './component/user/endow/endow';
import HomePartner from './component/PartnerComponent/homepartner';
import LoginPartner from './component/PartnerComponent/login/partnerlogin';

function App() {
  //map component user
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
  ];
  //
  return (
    <>
      <Router>
        <Routes>
          {/* guest component */}
          <Route path='/*' element={<UserLayout />}>
            {items.map(item => (
              <Route path={item.path} element={item.element} />
            ))}
          </Route>
          {/* map quyen voi url admin */}
          <Route path='/*' element={<AdminLayout />}>
            <Route path='admin/*' element={<ProtectedRoute adminOnly />} />
          </Route>
        </Routes>
      </Router>
    </>
  );
}


export default App;
