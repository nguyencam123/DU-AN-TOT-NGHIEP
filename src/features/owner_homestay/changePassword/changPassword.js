import { message } from "antd";
import { instance } from "../../../app/axiosConfig";

export const ChangePasswordSlice = async (data, imgUrl, id) => {
    const formData = new FormData();
    formData.append('owner', JSON.stringify(data));
    imgUrl.forEach((imageUrl) => {
        formData.append('avataUrl', imageUrl);
    });
    try {
        await instance.put(`/api/v2/owner/update-information-owner?id=${id}`, formData);
    } catch (error) {
        console.log(error.message);
        throw error;
    }
};
export const ChangePasswordByPass = async (Data) => {
    try {
        const response = await instance.post(`/api/v2/change-pass/changePassword`, Data);
        return response.data;
    } catch (error) {
        message.error(error.response.data.message);
        throw error;
    }
};
export const ChangePasswordByPassUser = async (Data) => {
    try {
        const response = await instance.post(`http://localhost:8080/api/v1/change-pass/changePassword`, Data);
        return response.data;
    } catch (error) {
        message.error(error.response.data.message);
        throw error;
    }
};