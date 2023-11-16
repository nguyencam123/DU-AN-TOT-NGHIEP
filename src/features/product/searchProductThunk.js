import { fetchProductsStart, fetchProductsSuccess, fetchProductsFailure } from './productSlide';
import { instance } from '../../app/axiosConfig';
import axios from 'axios';

const BASE_URL = '/homestay/get-all?size=999';

export const fetchSearchProducts = (startDate, enDate, nameOrAddress, numberPerson, roomNumber, priceMin, priceMax, convenientHomestayList) => async (dispatch) => {
    dispatch(fetchProductsStart());
    try {
        const response = await
            axios.post(`http://localhost:8080/api/v1/homestay/search?dateFrom=${startDate}&dateTo=${enDate}&nameOrAddress=${nameOrAddress}&numberPerson=${numberPerson}&roomNumber=${roomNumber}&priceMin=${priceMin}&priceMax=${priceMax}&${convenientHomestayList}`);
        dispatch(fetchProductsSuccess(response.data.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchProductsFailure(error.message));
    }
};