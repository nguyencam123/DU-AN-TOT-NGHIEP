import { fetchstatisticalStart, fetchStatisticalByYearSuccess, fetchstatisticalFailure, fetchStatisticalByMonthSuccess } from './statisticalSlice';
import { instance } from '../../../app/axiosConfig';



export const fetchStatisticalByYear = (id, year) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    const formData = new FormData();
    formData.append('year', year);
    try {
        const response = await instance.get(`/api/v2/statictical/year?id=${id}`, formData);
        dispatch(fetchStatisticalByYearSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};
export const fetchStatisticalByMonth = (id) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/month-and-year?id=${id}`);
        dispatch(fetchStatisticalByMonthSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};