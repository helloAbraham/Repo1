import axios from "axios";
import NProgress from 'nprogress';

const BASE_URL = 'http://localhost:8090/api/auth'

const register = async (username, password, role ) => {
  try {
    const response = await axios.post(`${BASE_URL}/register/user`, { username, password, role });
    return response.data;
  } catch(error) {
    throw new Error(error.response.data.message || 'Sorry! Registration failed');
  }
};

const login = async (username, password) => {
  try {
    const response = await axios.post(`${BASE_URL}/login`, { username, password });
    if(response.data.token) {
      localStorage.setItem('authToken', response.data.token);
    }
    return response.data;
  } catch(error) {
    throw new Error(error.response.data || 'Login Failed!')
  }
};

const logout = () => {
  localStorage.removeItem('authToken');
};

const isAuthenticated = () => {
  return !!localStorage.getItem('authToken')

};

//let's use intercepters
//this is the request for server
register.interceptors.request.use(config => {
    NProgress.start()
    return config;
  })
  //this is the response from the server 
  register.interceptors.response.use(response => {
    NProgress.done()
    return response;
  })
  

export default {
    register,
    login,
    logout,
    isAuthenticated
}