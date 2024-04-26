import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'nprogress/nprogress.css'
import BaseInput from './components/BaseInput.vue';
import BaseButton from './components/BaseButton.vue'
import BaseSelect from './components/BaseSelect.vue';
import BaseRadio from './components/BaseRadio.vue';
import BaseCheckBox from './components/BaseCheckBox.vue';
import BaseButtonStyle from './components/BaseButtonStyle.vue';
import ButtonLink from './components/ButtonLink.vue';

//Register BaseInput Globally
createApp(App)
.component('BaseInput', BaseInput)
.component('BaseButton', BaseButton)
.component('BaseButtonStyle', BaseButtonStyle)
.component('ButtonLink', ButtonLink)
.component('BaseSelect', BaseSelect)
.component('BaseRadio', BaseRadio)
.component('BaseCheckBox', BaseCheckBox)
.use(router).mount('#app', 'BaseInput')


