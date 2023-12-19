import { post } from '../api/request';
import { API_POST_PRODUCT } from '../constants/endpoints';

export const postProduct = async (product) => post(API_POST_PRODUCT, product);

export default {
    postProduct,
}
