import { get, post } from '../api/request';
import { API_POST_PRODUCT, API_GET_USER_LIST } from '../constants/endpoints';

export const getUserList = async () => get(API_GET_USER_LIST);

export const postProduct = async (product) => post(API_POST_PRODUCT, product);

export default {
    postProduct,
    getUserList
}
