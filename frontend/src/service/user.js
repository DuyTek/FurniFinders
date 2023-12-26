import { put, get } from "../api/request";
import { API_GET_PROFILE, API_UPDATE_PROFILE } from "../constants/endpoints";

export const getProfile = async(id) => get(`${API_GET_PROFILE}/${id}`);
export const updateProfile = async (id ,data) => put(`${API_UPDATE_PROFILE}/${id}`, data);

export default {
    updateProfile,
    getProfile
}