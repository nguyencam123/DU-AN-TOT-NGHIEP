import { fetchBookingsStart, fetchBookingsSuccess, fetchBookingsFailure } from './bookingSlice';
import { instance } from '../../../app/axiosConfig';
import axios from 'axios';
import { addConvenientFailed } from '../../admin/adminSlice';

const BASE_URL = '/api/v2/booking';

export const fetchBooking = (id) => async (dispatch) => {
    dispatch(fetchBookingsStart());
    try {
        const response = await instance.get(`/api/v2/booking/byid?id=${id}`);
        dispatch(fetchBookingsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchBookingsFailure(error.message));
    }
};

export const getBookingByNameHomestay = (id, name, nameBooking, statusBooking,serchYear,serchMonth) => async (dispatch) => {
    try {
        const response = await instance.get(`http://localhost:8080/api/v2/booking?idOwner=${id}&size=99&homestayName=${name}&nameBooking=${nameBooking}&statusBooking=${statusBooking}&year=${serchYear}&month=${serchMonth}`);
        dispatch(fetchBookingsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data

    } catch (error) {
        dispatch(addConvenientFailed(error.message));
    }
};
// export const getBookingByPhoneNumber = (sdt) => async (dispatch) => {
//     try {
//         const response = await instance.get('http://localhost:8080/api/v3/booking?size=99&sdtUser=' + sdt);
//         dispatch(fetchBookingsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data

//     } catch (error) {
//         dispatch(addConvenientFailed(error.message));
//     }
// };

// export const getBookingByName = (name) => async (dispatch) => {
//     try {
//         const response = await instance.get('http://localhost:8080/api/v3/booking?size=99&nameBooking=' + name);
//         dispatch(fetchBookingsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data

//     } catch (error) {
//         dispatch(addConvenientFailed(error.message));
//     }
// };