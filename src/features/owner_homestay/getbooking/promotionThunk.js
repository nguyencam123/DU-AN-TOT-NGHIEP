import { fetchBookingsStart, fetchPromotionsSuccess, fetchBookingsFailure } from './bookingSlice';
import { instance } from '../../../app/axiosConfig';

export const fetchPromotion = (id) => async (dispatch) => {
    dispatch(fetchBookingsStart());
    try {
        const response = await instance.get(`/api/v2/promotion?idOwner=${id}`);
        dispatch(fetchPromotionsSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchBookingsFailure(error.message));
    }
};
export const addPromotion = (promotion) => async (dispatch) => {
    dispatch(fetchBookingsStart());
    try {
        await instance.post("/api/v2/promotion/add-promotion", promotion);
    } catch (error) {
        dispatch(fetchBookingsFailure(error.message));
    }
};