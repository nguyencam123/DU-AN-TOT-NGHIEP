import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from '../owner_homestay/onwerHomestaySlice';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v2/homestay';

export const fetchHomestay = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get(BASE_URL + "/get-homestay");
    dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addHomestay = (homestay, imgUrl) => async (dispatch) => {
  const formData = new FormData();
  formData.append('image', imgUrl);
  formData.append('homestay', JSON.stringify(homestay));
  dispatch(fetchProductsStart());
  try {
    await axios.post(BASE_URL + "/add-homestays", formData);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

