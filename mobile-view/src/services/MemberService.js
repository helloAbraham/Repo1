import axios from "axios";
// import NProgress from 'nprogress';

const BASE_URL = 'http://localhost:8090/api/members'

const register = async (firstName, lastName, address, city, state, zipCode, email, phone ) => {
  try {
    const response = await axios.post(`${BASE_URL}/save`, { firstName, lastName, address, city, state, zipCode, email, phone });
    return response.data;
  } catch(error) {
    throw new Error(error.response.data.message || 'Sorry! Member Registration failed');
  }
};

//let's use intercepters
//this is the request for server
// register.interceptors.request.use(config => {
//     NProgress.start()
//     return config;
//   })
//   //this is the response from the server 
//   register.interceptors.response.use(response => {
//     NProgress.done()
//     return response;
//   })

export default {
    register,
};