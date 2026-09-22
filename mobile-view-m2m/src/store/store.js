
import { createStore } from 'vuex';


export const store = createStore({
    state: {
        isLoggedIn: false
    },

    mutations: {
        
        login(state) {
            state.isLoggedIn = true;
        },
        logout(state) {
            state.isLoggedIn = false;
        }
    },
    actions: {
        
    },

    getters: {
        intendedRoute: state => state.intendedRoute,
    },
})

const app = createApp({ /* your root component */ })

// Install the store instance as a plugin
app.use(store)
