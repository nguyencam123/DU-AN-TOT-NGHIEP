import axios from '../../app/axiosConfig';
import { fetchProductsFailure } from '../product/productSlide';
import { denineProducts } from '../product/productThunk';
import { fetchConvenientSuccess } from './adminSlice';

const BASE_URL = '/homestay/get-all?size=999';

export const aproveHomestay = (data) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v3/homestay/agree', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const disAgreeHomestay = (data) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v3/homestay/disAgree', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getConvenient = () => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v3/convenient-homestay');
    dispatch(fetchConvenientSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

