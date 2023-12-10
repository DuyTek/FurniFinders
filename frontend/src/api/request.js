import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000, // Set a timeout of 5 seconds
  headers: {
    'Content-Type': 'application/json',
  },
});


// GET request
export const get = async (url, params) => api.get(url, { params });

// POST request
export const post = async (url, params) =>  api.post(url, params);

// PUT request
export const put = async (url, data) => api.put(url, data);

// DELETE request
export const del = async (url) => api.delete(url);

export default api;