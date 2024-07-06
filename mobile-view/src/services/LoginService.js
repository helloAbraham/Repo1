import axios from "axios";
import NProgress from 'nprogress';

//This is called Single Axios Instance for our app
const apiClient = axios.create({
  baseURL: "http://localhost:8090/api/auth",
  withCredentials: false,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
    "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
     Accepts: "application/json",
    "Content-Type": "application/json",
  },
});

//let's use intercepters
//this is the request for server
apiClient.interceptors.request.use(config => {
  NProgress.start()
  return config;
})
//this is the response from the server 
apiClient.interceptors.response.use(response => {
  NProgress.done()
  return response;
})


//These are each of our API calls
//Creating methods here..
export default {
  getEvents() {
    return apiClient.get("/events");
  },


  //per page called will be like that
  /**
      getEvents(perPage, page){
        return apiClient.get('/events?_limit=' + perPage + '&_page=' + page)
      }
   */

  getEvent(id) {
    return apiClient.get("/events/" + id);
  },

  postEvent(event){
    return apiClient.post('/login', event);
  },

  register(user) {
    return apiClient.post('/register', user);
  },
  login(credentials) {
    return apiClient.post('/login', credentials);
  }
};
