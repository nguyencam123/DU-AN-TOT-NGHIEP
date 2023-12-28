import { fetchstatisticalStart, fetchStatisticalByYearSuccess, fetchstatisticalFailure, fetchStatisticalByMonthSuccess, fetchStatisticalByYearsSuccess, fetchStatisticalByDaySuccess, fetchStatisticalByTop5Success } from './statisticalSlice';
import { instance } from '../../../app/axiosConfig';



export const fetchStatisticalByYear = (year) => async (dispatch) => {

  // Extract the year from the date
  const yearValue = new Date(year).getFullYear();

  dispatch(fetchstatisticalStart());
  try {
    const response = await instance.get(`api/v3/statistical/year?year=${yearValue}`);
    dispatch(fetchStatisticalByYearSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchstatisticalFailure(error.message));
  }
};
export const fetchStatisticalByYears = (year) => async (dispatch) => {
  dispatch(fetchstatisticalStart());
  try {
    const response = await instance.get(`/api/v3/statictical/month-year?year=${year}`);
    dispatch(fetchStatisticalByYearsSuccess(response.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchstatisticalFailure(error.message));
  }
};
export const fetchStatisticalByMonth = (month, year) => async (dispatch) => {
  dispatch(fetchstatisticalStart());
  try {
    const response = await instance.get(`/api/v3/statictical/month-and-year?year=${year}&month=${month}`);
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
export const fetchStatisticalByTop5 = (year) => async (dispatch) => {
  dispatch(fetchstatisticalStart());
  try {
    const response = await instance.get(`/api/v3/statictical/top5?year=${year}&size=5`);
    dispatch(fetchStatisticalByTop5Success(response.data.data)); // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchstatisticalFailure(error.message));
  }
};