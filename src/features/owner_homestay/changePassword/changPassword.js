import { instance } from "../../../app/axiosConfig";

export const ChangePasswordSlice = async (data, imgUrl, id) => {
    const formData = new FormData();
    formData.append('homestay', JSON.stringify(data));
    imgUrl.forEach((imageUrl) => {
        formData.append('avataUrl', imageUrl);
    });
    try {
        const response = await instance.put(`/api/v2/owner/update-information-owner?id=${id}`, formData);
        return response.data;
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
        console.log(error.message);
        throw error;
    }
};