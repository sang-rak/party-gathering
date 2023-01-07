import customAxios from "libs/CustomAxios";


export const handlePostLogin = async function fetchData() {
  const url = `/`
  const data = await customAxios.post(`${url}`);
  return data;
}