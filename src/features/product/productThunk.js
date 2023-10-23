import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from './productSlide';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/homestay';

export const fetchProducts = () => async (dispatch) => {
    dispatch(fetchProductsStart());
    try {
        const response = await axios.get(BASE_URL);
        dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchProductsFailure(error.message));
    }
};
