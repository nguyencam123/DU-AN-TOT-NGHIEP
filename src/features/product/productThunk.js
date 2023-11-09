import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure, fetchProductsDetailSuccess, fetchCommentProductSuccess, fetchAvgPointSuccess } from './productSlide';
import axios from '../../app/axiosConfig';

const BASE_URL = '/homestay/get-all?size=999';

export const fetchProducts = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get('http://localhost:8080/api/v1/homestay?size=99');
    dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getProducts = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get('http://localhost:8080/api/v1/homestay');
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

export const getCommentProduct = (id) => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/comment?homestayId=' + id);
    dispatch(fetchCommentProductSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
    // console.log(response.data.data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getAvgPoint = (id) => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/comment/avg-point?homestayId=' + id);
    dispatch(fetchAvgPointSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    // console.log(response.data.data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const getNumberPersonPoint = (id) => async (dispatch) => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/comment/number-of-reviewers?homestayId' + id);
    dispatch(fetchAvgPointSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    // console.log(response.data.data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};