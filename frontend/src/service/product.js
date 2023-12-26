import { get } from '../api/request';
import { API_GET_USER_LIST } from '../constants/endpoints';

export const getUserList = async () => get(API_GET_USER_LIST);

export default {
    getUserList
}