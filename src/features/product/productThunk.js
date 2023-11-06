import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure, fetchProductsDetailSuccess } from './productSlide';
import axios from '../../app/axiosConfig';

const BASE_URL = '/homestay/get-all?size=999';

export const fetchProducts = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get(BASE_URL);
    dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getOneProduct = (id) => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/homestay/getOne?homestayId=' + id);
    dispatch(fetchProductsDetailSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    // console.log(response.data.data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};