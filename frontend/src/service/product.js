import { get, post } from '../api/request';
import { API_POST_PRODUCT, API_GET_USER_LIST, API_GET_ALL_PRODUCTS, API_GET_APPROVED_PRODUCTS } from '../constants/endpoints';

export const getUserList = async () => get(API_GET_USER_LIST);

export const postProduct = async (product) => post(API_POST_PRODUCT, product);
export const getAllApprovedProducts = async () => get(API_GET_APPROVED_PRODUCTS);
export const getAllProducts = async () => get(API_GET_ALL_PRODUCTS);

export default {
    postProduct,
    getUserList,
    getAllApprovedProducts
}
