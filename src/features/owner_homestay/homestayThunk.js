import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure, addproduct, edithomestay } from '../owner_homestay/onwerHomestaySlice';
import axios from '../../app/axiosConfig';

const BASE_URL = '/homestay';

export const fetchHomestay = () => async (dispatch) => {
  dispatch(fetchProductsStart());
  try {
    const response = await axios.get(BASE_URL + "/get-all?size=999");
    dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};

export const addHomestay = (homestay, imgUrl) => async (dispatch) => {
  const formData = new FormData();
  imgUrl.forEach((imageUrl) => {
    formData.append('image', imageUrl);
  });
  formData.append('homestay', JSON.stringify(homestay));
  dispatch(fetchProductsStart());
  try {
    await axios.post(BASE_URL + "/add-homestays", formData);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};
export const EditHomestay = (homestay, imgUrl, id) => async (dispatch) => {
  const formData = new FormData();
  imgUrl.forEach((imageUrl) => {
    formData.append('image', imageUrl);
  });
  formData.append('homestay', JSON.stringify(homestay));
  dispatch(fetchProductsStart());
  console.log(id)
  try {
    await axios.put(BASE_URL + `/update-homestays?id=${id}`, formData);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};
