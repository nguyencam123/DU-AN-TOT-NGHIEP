import { fetchBookingsStart, fetchBookingsSuccess, fetchBookingsFailure } from './bookingSlice';
import { instance } from '../../../app/axiosConfig';
import axios from 'axios';

const BASE_URL = '/api/v2/booking';

export const fetchBooking = (id) => async (dispatch) => {
    dispatch(fetchBookingsStart());
    try {
        const response = await instance.get(`/api/v2/booking?id=${id}`);
        dispatch(fetchBookingsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchBookingsFailure(error.message));
    }
};
