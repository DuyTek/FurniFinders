import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000, // Set a timeout of 5 seconds
  headers: {
    'Content-Type': 'application/json',
  },
});


// GET request
export const get = async (url, params) => {
  try {
    const response = await api.get(url, { params });
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message);
  }
};

// POST request
export const post = async (url, data) =>  api.post(url, data)
  .then((response) => response)
  .catch((error) => error.response.data);

// PUT request
export const put = async (url, data) => {
  try {
    const response = await api.put(url, data);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message);
  }
};

// DELETE request
export const del = async (url) => {
  try {
    const response = await api.delete(url);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message);
  }
};

export default api;