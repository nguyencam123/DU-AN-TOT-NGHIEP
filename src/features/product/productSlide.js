// src/features/product/productSlice.js
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  products: [],
  loading: false,
  error: null,
};

const productSlice = createSlice({
  name: 'product',
  initialState,
  reducers: {
    fetchProductsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchProductsSuccess: (state, action) => {
      state.products = action.payload;
      state.loading = false;
      state.error = null;
    },
    fetchProductsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    deleteProduct: (state, action) => {
      const productIdToDelete = action.payload;
      state.products = state.products.filter(product => product.id !== productIdToDelete);
    },
    addproduct: (state, action) => {
      state.products.push(action.payload)
    }
  },
});

export const { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure, deleteProduct, addproduct } = productSlice.actions;
export default productSlice.reducer;
