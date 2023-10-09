import './App.css';
import ProductList from './component/product/ProductList';
import LoginComponent from './component/login/Login';
import LoginDetail from './component/login/LoginDetail';
import { BrowserRouter as Router, Routes, Route, Outlet } from 'react-router-dom';
import ProtectedRoute from './app/ProtectedRoute';
import AdminLayout from './layout/layoutadmin/AdminLayout';
import UserLayout from './layout/layoutuser/UserLayout';
import Register from './component/login/register'

function App() {
  return (
    <>
      <Router>
        <Routes>
          {/* guest component */}
          <Route path='/*' element={<UserLayout />}>
            <Route path=''>
              <Route index element={<ProductList />} />
            </Route>
            <Route path='user/propreties'>
              <Route index element={<LoginDetail />} />
            </Route>
            <Route path='login'>
              <Route index element={<LoginComponent />} />
            </Route>
            <Route path='register'>
              <Route index element={<Register />} />
            </Route>
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
