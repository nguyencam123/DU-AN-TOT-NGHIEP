import { instance } from '../../../app/axiosConfig';
import { fetchUserFailure, fetchUserSuccess } from './userSlice';


export const fetchAllUser = () => async (dispatch) => {
  try {
    const response = await instance.get(`/api/v3/user`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const getCommentByUser = () => async (dispatch) => {
  try {
    const response = await instance.get(`/api/v3/user`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const approveUser = (id) => async (dispatch) => {
  try {
    await instance.put(`/api/v3/user/approve`, { userId: id });
    const response = await instance.get(`/api/v3/user`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const refuseUser = (id) => async (dispatch) => {
  try {
    await instance.put(`/api/v3/user/refuse`, { userId: id });
    const response = await instance.get(`/api/v3/user`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}
