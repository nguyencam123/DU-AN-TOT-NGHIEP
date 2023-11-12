import axios from '../../app/axiosConfig';
import { fetchProductsFailure } from '../product/productSlide';
import { denineProducts } from '../product/productThunk';
import { fetchConvenientSuccess, fetchConvenientTypeSuccess } from './adminSlice';

const BASE_URL = '/homestay/get-all?size=999';

export const aproveHomestay = (data) => async (dispatch) => {
  try {
    const response = await axios.put('http://localhost:8080/api/v3/homestay/approve', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const disAgreeHomestay = (data) => async (dispatch) => {
  try {
    const response = await axios.put('http://localhost:8080/api/v3/homestay/refuse', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getConvenient = () => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v3/convenient-homestay?size=99');
    dispatch(fetchConvenientSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getConvenientType = () => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v3/convenient-homestay/type');
    dispatch(fetchConvenientTypeSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addConvenient = (data) => async (dispatch) => {
  try {
    console.log(1);
    const response = await axios.post('http://localhost:8080/api/v3/convenient-homestay/add-convenient', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addType = (data) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v3/convenient-homestay/add-convenient-type', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};


