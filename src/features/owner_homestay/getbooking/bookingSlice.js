import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  bookings: [],
  booking: {},
  promotions: [],
  comments: [],
  commentsUser:[],
  loading: false,
  error: null,
};

const bookingSlice = createSlice({
  name: 'booking',
  initialState,
  reducers: {
    fetchBookingsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchBookingsSuccess: (state, action) => {
      state.bookings = action.payload;
      state.loading = false;
      state.error = null;
    },
    fetchCommentSuccess: (state, action) => {
      state.comments = action.payload;
      state.loading = false;
      state.error = null;
    },
    fetchCommentUserSuccess: (state, action) => {
      state.commentsUser = action.payload;
      state.loading = false;
      state.error = null;
    },
    addBookingsSuccess: (state, action) => {
      state.bookings = action.payload;
      state.loading = false;
      state.error = null;
    },
    fetchBookingsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    fetchPromotionsSuccess: (state, action) => {
      state.promotions = action.payload;
      state.loading = false;
      state.error = null;
    }
  },
});

export const { fetchBookingsStart, fetchBookingsSuccess, fetchBookingsFailure, fetchPromotionsSuccess, addBookingsSuccess, fetchCommentSuccess,fetchCommentUserSuccess } = bookingSlice.actions;
export default bookingSlice.reducer;
