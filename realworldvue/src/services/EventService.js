import axios from "axios";

const apiClient = axios.create({
  baseURL: "https://my-json-server.typicode.com/helloAbraham/Repo1",
  withCredentials: false,
  Headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

//Creating methods here..
export default {
  getEvents() {
    return apiClient.get("/events");
  },

  getEvent(id) {
    return apiClient.get("/events/" + id);
  },
};
