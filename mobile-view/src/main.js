import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'nprogress/nprogress.css'
import BaseInput from './components/BaseInput.vue';
import BaseButton from './components/BaseButton.vue'


//Register BaseInput Globally
createApp(App)
.component('BaseInput', BaseInput)
.component('BaseButton', BaseButton)
.use(router).mount('#app', 'BaseInput')


