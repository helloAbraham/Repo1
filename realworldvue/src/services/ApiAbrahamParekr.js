import axios from "axios";
import NProgress from 'nprogress';

//This is called Single Axios Instance for our app
const apiMyClient = axios.create({
  baseURL: "https://api.abrahamparker.com/api/images/",
  withCredentials: false,
  Headers: {
    Accept: "application/json",
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
    postEvent() {
        return apiMyClient.post("/upload");
    }, 
    getEventImage() {
        return apiMyClient.get("/listAll");
    },
    getEvents() {
        return apiMyClient.get("/findAll");
    },
   
    deleteEvent() {
        return apiMyClient.delete("/delete");
    }

  //per page called will be like that
  /**
      getEvents(perPage, page){
        return apiClient.get('/events?_limit=' + perPage + '&_page=' + page)
      }
   */

      /** 
  getEvent(id) {
    return apiClient.get("/events/" + id);
  },

  postEvent(event){
    return apiClient.post('/events', event);
  }
  */
};
