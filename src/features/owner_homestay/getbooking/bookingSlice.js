import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    bookings: [],
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
        fetchBookingsFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload;
        }
    },
});

export const { fetchBookingsStart, fetchBookingsSuccess, fetchBookingsFailure } = bookingSlice.actions;
export default bookingSlice.reducer;
