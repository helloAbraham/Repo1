import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import nProgress from "nprogress";
import 'nprogress/nprogress.css'
import Vuelidate from "vuelidate";

createApp(App)
.use(store)
.use(router)
.use(nProgress)
.use(Vuelidate)
.mount("#app");
