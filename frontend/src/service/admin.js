import { put } from "../api/request";
import { API_VERIFY_USER } from "../constants/endpoints";

export const verifyUser = (id) => put(`${API_VERIFY_USER}/${id}`);