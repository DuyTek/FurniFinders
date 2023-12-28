import { get, post } from '../api/request';
import { API_GET_USER_LIST, API_POST_PRODUCT } from '../constants/endpoints';

export const getUserList = async () => get(API_GET_USER_LIST);

export const postProduct = async (product) => post(API_POST_PRODUCT, product);

export default {
    postProduct,
    getUserList
}
