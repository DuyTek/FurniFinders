import { post } from '../api/request';
import { API_SIGN_UP } from '../constants/endpoints';

export const signUp = async (data) => {
  try {
    const response = await post(API_SIGN_UP, data);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message);
  }
}

export default {
    signUp,
};