import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from '../owner_homestay/convenientSlice';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v2/convenient';

export const fetchConvenient = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get(BASE_URL);
    dispatch(fetchProductsSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};
