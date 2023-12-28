import { put } from "../api/request";
import { API_VERIFY_USER, API_UPDATE_PRODUCT_STATUS } from "../constants/endpoints";

export const verifyUser = (id) => put(`${API_VERIFY_USER}/${id}`);
export const updateProductStatus = (product_id, postStatus) => put(API_UPDATE_PRODUCT_STATUS, { postStatus, product_id});