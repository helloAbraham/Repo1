import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import NotFound from '@/views/NotFound.vue';
import EventList from '../views/EventList.vue';


import store from '@/store';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path:"/404",
    name:"NotFound",
    component: NotFound,
  },
  {
    path: "/:pathMatch(.*)",
    name:'bad-not-found',
    component: NotFound
  },
  {
    path: '/threecol',
    name: 'ThreeColumnView',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "threecol" */ '../views/ThreeColumnView.vue')
  },
  {
    path: '/bgimage',
    name: 'BackgroundImageAdd',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "threecol" */ '../components/BackgroundImageAdd.vue')
  },
  {
    path: '/about',
    name: 'About',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

  {
    path: '/signup',
    name: 'SignUp',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "signup" */ '../components/SignUp.vue')
  },

  {
    path: '/register',
    name: 'MemberRegister',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "register" */ '../components/MemberRegister.vue')
  },

  {
    path: '/login',
    name: 'LoginForm',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "threecol" */ '../components/LoginForm.vue')
  },

  {
    path: '/logout',
    name: 'LogoutComponent',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "LogoutComponent" */ '../components/LogoutComponent.vue')
  },

  {
    path: '/eventlist',
    name: 'EventList',
    component: EventList
  },
  {
    path:"/404",
    name:"/404",
    component: NotFound,
  },
  {
    path: "/:pathMatch(.*)",
    name:'bad-not-found',
    component: NotFound
  },
  {
    path: "/event/:id",
    name: "EventDetails",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "eventdetails" */ "../views/EventDetails.vue"
      ),
  },

  {
    path: "/jumaevent/:id",
    name: "JumaEventDetails",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "eventdetails" */ "../views/JumaEventDetails.vue"
      ),
  },

  {
    path: "/upevent/:id",
    name: "UpcomingEventDetails",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "eventdetails" */ "../views/UpcomingEventDetails.vue"
      ),
  },
  {
    path: "/profile",
    name: "Dashboard",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "dashborad" */ "../views/Dashboard.vue"
      ),
      //meta: { requiresAuth: true } 
  },
  {
    path: "/praytime",
    name: "PrayerTimes",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "eventdetails" */ "../views/PrayerTimes.vue"
      ),
  },

  {
    path: "/photoSlide",
    name: "PhotoSlidesShow",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "photoslide" */ "../components/PhotoSlideShow.vue"
      ),
  },

  {
    path: "/myform",
    name: "MyForm",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "myform" */ "../views/MyForm.vue"
      ),
  },

  {
    path: "/dollarinput",
    name: "CurrencyInput",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "myform" */ "../components/CurrencyInput.vue"
      ),
  },

  {
    path: "/condButton",
    name: "ConditionalButtonShow",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "ConditionalButtonShow" */ "../components/ConditionalButtonShow.vue"
      ),
  }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});


router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)){
    //If the route requires authentication
      if(!isAuthenticated()) {
        //if not authenticated
        store.dispatch('setIntededRoute', to.fullPath); //save intended route
        next('/login'); //Redirect to  login page

      } else {
        next(); //Continue to login page
      }
  } else {
    next(); // Continue to the route
  }
})

// Example route guard in Vue Router
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !isLoggedIn()) {
    next('/login');
  } else {
    next();
  }
});

export default router;
