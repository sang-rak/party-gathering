import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Access-Control-Allow-Origin": "*",
    "content-Type": "application/json",
  },
  params: {
    api_key: process.env.REACT_APP_JACKDANG_DB_API_KEY,
    language: "ko-KR",
  },
});

export default instance;
