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
  homestay.province = "2695a00e-a933-4c28-819c-9b66f3184e8d";
  homestay.region = "a72af500-5ee5-4268-8d91-d7383d4a9011"
  console.log(homestay);
  dispatch(fetchProductsStart());
  try {
    await axios.post(BASE_URL + "/add-homestay", { homestay, imgUrl } );
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

