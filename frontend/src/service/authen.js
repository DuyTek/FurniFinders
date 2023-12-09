import { post } from '../api/request';
import { API_SIGN_IN, API_SIGN_UP } from '../constants/endpoints';

export const signUp = async (data) => post(API_SIGN_UP, data);
export const signIn = async (data) => post(API_SIGN_IN, data);

export default {
    signUp,
    signIn,
};