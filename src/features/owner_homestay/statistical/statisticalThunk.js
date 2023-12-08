import { fetchstatisticalStart, fetchStatisticalByYearSuccess, fetchstatisticalFailure, fetchStatisticalByMonthSuccess } from './statisticalSlice';
import { instance } from '../../../app/axiosConfig';



export const fetchStatisticalByYear = (id, year) => async (dispatch) => {

    // Extract the year from the date
    const yearValue = new Date(year).getFullYear();

    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/year?idOwnerHomestay=${id}&year=${yearValue}`);
        dispatch(fetchStatisticalByYearSuccess(response.data.data));
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};


export const fetchStatisticalByMonth = (id) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/month-and-year?idOwnerHomestay=${id}`);
        dispatch(fetchStatisticalByMonthSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};