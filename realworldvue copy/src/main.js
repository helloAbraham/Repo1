import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import nProgress from "nprogress";
import 'nprogress/nprogress.css'
import Vuelidate from "vuelidate";
import BaseInput from "./components/BaseInput.vue";
import BaseButton from "./components/BaseButton.vue";
import BaseSelect from "./components/BaseSelect.vue";
import BaseRadio from "./components/BaseRadio.vue";
import BaseCheckbox from "./components/BaseCheckbox.vue";

createApp(App)
.component('BaseButton', BaseButton)
.component('BaseInput', BaseInput)
.component('BaseSelect', BaseSelect)
.component('BaseRadio', BaseRadio)
.component('BaseCheckBox', BaseCheckbox)
.use(store)
.use(router)
.use(nProgress)
.use(Vuelidate)
.mount("#app");

