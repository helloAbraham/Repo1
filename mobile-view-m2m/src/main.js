import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import Vuex, { createStore } from 'vuex'
import 'nprogress/nprogress.css'
import BaseInput from './components/BaseInput.vue';
import BaseButton from './components/BaseButton.vue'
import BaseSelect from './components/BaseSelect.vue';
import BaseRadio from './components/BaseRadio.vue';
import BaseCheckBox from './components/BaseCheckBox.vue';
import BaseButtonStyle from './components/BaseButtonStyle.vue';
import ButtonLink from './components/ButtonLink.vue';
import Vuelidate from 'vuelidate';
import store from './store'
import '@fortawesome/fontawesome-free/css/all.min.css';



//Register BaseInput Globally
createApp(App)
.component('BaseInput', BaseInput)
.component('BaseButton', BaseButton)
.component('BaseButtonStyle', BaseButtonStyle)
.component('ButtonLink', ButtonLink)
.component('BaseSelect', BaseSelect)
.component('BaseRadio', BaseRadio)
.component('BaseCheckBox', BaseCheckBox)
.use(store)
.use(router)
.use(Vuelidate)
.mount('#app')


