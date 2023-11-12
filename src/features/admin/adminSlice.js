// src/features/product/productSlice.js
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  category: [],
  categoryType: [],
  booking: [],
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
    fetchConvenientTypeSuccess: (state, action) => {
      state.categoryType = action.payload;
      state.loading = false;
      state.error = null;
    },
    fetchBookingSuccess: (state, action) => {
      state.booking = action.payload;
      state.loading = false;
      state.error = null;
    },
  },
});

export const {
  fetchConvenientSuccess, fetchConvenientTypeSuccess, fetchBookingSuccess
} = adminSlice.actions;
export default adminSlice.reducer;
