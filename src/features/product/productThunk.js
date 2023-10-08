// src/features/product/productThunk.js
import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from './productSlide';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/hotel';
export const fetchProducts = () => async (dispatch) => {
    dispatch(fetchProductsStart());
    try {
        const response = await axios.get(BASE_URL);
        dispatch(fetchProductsSuccess(response.data));
    } catch (error) {
        dispatch(fetchProductsFailure(error.message));
    }
};
