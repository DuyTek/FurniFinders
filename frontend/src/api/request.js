import axios from 'axios';

// Create an instance of Axios with custom configuration
const api = axios.create({
  baseURL: 'https://api.example.com', // Replace with your API base URL
  timeout: 5000, // Set a timeout of 5 seconds
  headers: {
    'Content-Type': 'application/json',
  },
});

// Define the four methods for making REST API requests

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
export const post = async (url, data) => {
  try {
    const response = await api.post(url, data);
    return response.data;
  } catch (error) {
    throw new Error(error.response.data.message);
  }
};

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