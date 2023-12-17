import { fetchstatisticalStart, fetchStatisticalByYearSuccess, fetchstatisticalFailure, fetchStatisticalByMonthSuccess, fetchStatisticalByYearsSuccess, fetchStatisticalByDaySuccess } from './statisticalSlice';
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
export const fetchStatisticalByYears = (id, year) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/month-and-year?idOwnerHomestay=${id}&year=${year}`);
        dispatch(fetchStatisticalByYearsSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};
export const fetchStatisticalByMonth = (id, month, year) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/month-and-year?idOwnerHomestay=${id}&year=${year}&month=${month}`);
        dispatch(fetchStatisticalByMonthSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};
export const fetchStatisticalByDay = (id, day, month, year) => async (dispatch) => {
    dispatch(fetchstatisticalStart());
    try {
        const response = await instance.get(`/api/v2/statictical/month-and-year?idOwnerHomestay=${id}&year=${year}&month=${month}&date=${day}`);
        dispatch(fetchStatisticalByDaySuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
    } catch (error) {
        dispatch(fetchstatisticalFailure(error.message));
    }
};