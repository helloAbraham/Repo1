import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'nprogress/nprogress.css'
import BaseInput from './components/BaseInput.vue';


//Register BaseInput Globally

createApp(App).component('BaseInput', BaseInput).use(router).mount('#app', 'BaseInput')


