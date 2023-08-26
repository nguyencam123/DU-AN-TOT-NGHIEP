// src/features/product/productThunk.js
import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from './productSlide';
import axios from 'axios';

export const fetchProducts = () => async (dispatch) => {
    dispatch(fetchProductsStart());
    try {
        const response = await axios.get('http://localhost:8080/api/product/getall');
        dispatch(fetchProductsSuccess(response.data));
    } catch (error) {
        dispatch(fetchProductsFailure(error.message));
    }
};
