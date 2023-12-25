import { put } from "../api/request";
import { API_UPDATE_PROFILE } from "../constants/endpoints";

export const updateProfile = async (id ,data) => put(`${API_UPDATE_PROFILE}/${id}`, data);

export default {
    updateProfile,
}