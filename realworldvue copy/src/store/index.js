import { createStore } from "vuex";

export default createStore({
  state: {
    user:{ id: 'abc123', name: 'Abraham Parker'},
    categories: [
      "sustainbility",
      "nature",
      "animal welfare",
      "housing",
      "eduaction",
      "food",
      "community",
    ],
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});
