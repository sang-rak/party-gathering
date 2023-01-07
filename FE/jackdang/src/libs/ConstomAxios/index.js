import axios from 'axios';

const customAxios = axios.create({
  // baseURL: '',
  baseURL: 'http://127.0.0.1:8080',
  headers: {
    'Access-Control-Allow-Origin': '*',
  },
});

export default customAxios;
