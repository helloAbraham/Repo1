import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import nProgress from "nprogress";
import 'nprogress/nprogress.css'
import BaseInput from "./components/BaseInput.vue";
import BaseButton from "./components/BaseButton.vue";
import BaseSelect from "./components/BaseSelect.vue";
import BaseRadio from "./components/BaseRadio.vue";
import BaseCheckbox from "./components/BaseCheckbox.vue";
import ParentComponet from "./components/ParentComponet.vue";
import BaseButtonStyle from "./components/BaseButtonStyle.vue";

createApp(App)
.component('BaseButton', BaseButton)
.component('BaseButonStyle', BaseButtonStyle)
.component('BaseInput', BaseInput)
.component('BaseSelect', BaseSelect)
.component('BaseRadio', BaseRadio)
.component('BaseCheckBox', BaseCheckbox)
.component('ParentComponent', ParentComponet)
.use(store)
.use(router)
.use(nProgress)
.mount("#app");

