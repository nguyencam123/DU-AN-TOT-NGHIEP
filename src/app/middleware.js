// middleware.js

import { Navigate } from "react-router-dom";
import { adminloginSuccess, partnerloginSuccess } from "../features/user/userSlice";
import { instance } from "./axiosConfig";
import { logoutUser } from "../features/user/userThunk";

export const checkToken = (token) => async (dispatch) => {
    try {
        // Gọi API kiểm tra token ở đây
        const response = await instance.get(`http://localhost:8080/api/v2/homestay/get-ownerhomestay-by-token?token=${token}`);
        const data = response?.data;
        if (data.success) {
            dispatch(partnerloginSuccess({ partner: data, partnerData: data }))
        } else {
            dispatch(logoutUser());
        }
    } catch (error) {
        console.error('Error checking token:', error);
    }
};
export const checkTokenAdmin = (token) => async (dispatch) => {
    try {
        // Gọi API kiểm tra token ở đây
        const response = await instance.get(`http://localhost:8080/api/v3/homestay/get-admin-by-token?token=${token}`);
        const data = response?.data;
        if (data.success) {
            dispatch(adminloginSuccess({ partner: data, partnerData: data }))
        } else {
            dispatch(logoutUser());
        }
    } catch (error) {
        console.error('Error checking token:', error);
    }
};
