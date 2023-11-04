import { fetchProvinceStart, fetchProvinceSuccess, fetchProvinceFailure } from './provinceSlice';
import axios from '../../../app/axiosConfig';

const BASE_URL = '/province';

export const fetchProvince = () => async (dispatch) => {
    dispatch(fetchProvinceStart());
    try {
        const response = await axios.get(BASE_URL);
        dispatch(fetchProvinceSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchProvinceFailure(error.message));
    }
};
