import { instance } from '../../../app/axiosConfig';
import { fetchCommentUser, fetchUserFailure, fetchUserSuccess } from './userSlice';


export const fetchAllUser = () => async (dispatch) => {
  try {
    const response = await instance.get(`/api/v3/user`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const fetchAllUserByName = (name) => async (dispatch) => {
  try {
    const response = await instance.get(`/api/v3/user?userName=${name}`);
    dispatch(fetchUserSuccess(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const getCommentByUser = (id, username) => async (dispatch) => {
  try {
    const response = await instance.get(`api/v3/comment?homestayId=${id}&userName=${username}`);
    dispatch(fetchCommentUser(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}

export const getCommentByUserId = (id, idUser) => async (dispatch) => {
  console.log(1);
  try {
    const response = await instance.get(`api/v3/comment?userId=${idUser}&homestayName=${id}`);
    dispatch(fetchCommentUser(response.data.data));
  } catch (error) {
    dispatch(fetchUserFailure(error.message));
  }
}
export const getCommentByHomestay = (id) => async (dispatch) => {
  try {
    const response = await instance.get(`api/v3/comment?homestayId=${id}`);
    dispatch(fetchCommentUser(response.data.data));
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
