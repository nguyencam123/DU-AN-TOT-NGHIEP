import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from '../owner_homestay/convenientSlice';
import axios from '../../app/axiosConfig';

const BASE_URL = '/convenient';

export const fetchConvenient = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get(BASE_URL);
    dispatch(fetchProductsSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};
