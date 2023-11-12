import { instance } from '../../app/axiosConfig';
import { fetchProductsFailure } from '../product/productSlide';
import { denineProducts } from '../product/productThunk';
import { fetchBookingSuccess, fetchConvenientSuccess, fetchConvenientTypeSuccess } from './adminSlice';

const BASE_URL = '/homestay/get-all?size=999';

export const aproveHomestay = (data) => async (dispatch) => {
  try {
    const response = await instance.put('http://localhost:8080/api/v3/homestay/approve', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const disAgreeHomestay = (data) => async (dispatch) => {
  try {
    const response = await instance.put('http://localhost:8080/api/v3/homestay/refuse', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getConvenient = () => async (dispatch) => {
  try {
    const response = await instance.get('http://localhost:8080/api/v3/convenient-homestay?size=99');
    dispatch(fetchConvenientSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getConvenientType = () => async (dispatch) => {
  try {
    const response = await instance.get('http://localhost:8080/api/v3/convenient-homestay/type?size=99');
    dispatch(fetchConvenientTypeSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addConvenient = (data) => async (dispatch) => {
  try {
    console.log(1);
    const response = await instance.post('http://localhost:8080/api/v3/convenient-homestay/add-convenient', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addType = (data) => async (dispatch) => {
  try {
    const response = await instance.post('http://localhost:8080/api/v3/convenient-homestay/add-convenient-type', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getBooking = () => async (dispatch) => {
  try {
    const response = await instance.get('http://localhost:8080/api/v3/booking?size=99');
    dispatch(fetchBookingSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data

  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const updateConvenient = (data) => async (dispatch) => {
  try {
    console.log(1);
    const response = await instance.put('http://localhost:8080/api/v3/convenient-homestay/update-convenient', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};


