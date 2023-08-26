import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchProductstart, fetchProductFail, fetchProductSuccess } from "./createproductslice";
import axios from "axios";

export const addProductAsync = createAsyncThunk(
    'product/addProduct',
    async (productData, { rejetWithValue }) => {
        try {
            const response = await axios.post('http://localhost:8080/api/product/addproduct', productData)
            return response.data;
        } catch (error) {
            return rejetWithValue(error.message)
        }
    }
)

export const fetchProductsAsync = createAsyncThunk(
    'product/fetchProducts',
    async (_, { rejetWithValue }) => {
        try {
            const response = await axios.get('http://localhost:8080/api/product/getall');
            return response.data
        } catch (error) {
            return rejetWithValue(error.message);
        }
    }
)