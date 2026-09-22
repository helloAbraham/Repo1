import axios from "axios";
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

//More update way ...
//Let's create an Axios instance
const apiClient = axios.create({
  baseURL: 'http://localhost:8090/api/members'

});

apiClient.interceptors.request.use(config => {
  NProgress.start()
  return config;
})

//Adding Request interceptor to start NProgress
apiClient.interceptors.request.use( config => {
  NProgress.start();
  return config;
}, error => {
  NProgress.done();
  return new Promise.reject(error);
});

//Adding Response to interceptior to stop NProgress
apiClient.interceptors.response.use(response => {
  NProgress.done();
  return response;
}, error => {
  NProgress.done();
  return Promise.reject(error);
});


export default apiClient;

//previously created like that. 
// const BASE_URL = 'http://localhost:8090/api/members'

// const register = async (firstName, lastName, address, city, state, zipCode, email, phone ) => {
//   try {
//     const response = await axios.post(`${BASE_URL}/save`, { firstName, lastName, address, city, state, zipCode, email, phone });
//     return response.data;
//   } catch(error) {
//     throw new Error(error.response.data.message || 'Sorry! Member Registration failed');
//   }
// };

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

// export default {
//     register,
// };