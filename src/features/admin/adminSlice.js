// src/features/product/productSlice.js
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  category: [],
  loading: false,
  error: null,
};

const adminSlice = createSlice({
  name: 'admin',
  initialState,
  reducers: {
    fetchConvenientSuccess: (state, action) => {
      state.category = action.payload;
      state.loading = false;
      state.error = null;
    },
  },
});

export const {
  fetchConvenientSuccess
} = adminSlice.actions;
export default adminSlice.reducer;
