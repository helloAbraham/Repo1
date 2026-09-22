import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import NotFound from '@/views/NotFound.vue';
import EventList from '../views/EventList.vue';


import store from '@/store';
import HomePage from '@/views/HomePage.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },

    {
    path: '/HomePage',
    name: 'HomePage',
    component: HomePage
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
    component: () => import(/* webpackChunkName: "about" */ '../views/BoardMembers.vue')
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
    path: '/mylogin',
    name: 'MyLoginForm',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "threecol" */ '../components/MyLoginForm.vue')
  },

  {
    path: '/stulogin',
    name: 'StuLogin',
   
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "threecol" */ '../components/StuLogin.vue')
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
    path: "/events/:id",
    name: "EventDetailsPrevious",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "eventdetails" */ "../views/EventDetailsPrevious.vue"
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
    path: "/allnews",
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
    path: "/latestnews",
    name: "LatestNews",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "dashborad" */ "../views/LatestNews.vue"
      ),
      //meta: { requiresAuth: true } 
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
    path: "/ContactUs",
    name: "ContactUsNew",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "myform" */ "../views/ContactUsNew.vue"
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
  },

  {
    path: "/colorPage",
    name: "SurveyQues",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "SurveyQues" */ "../components/SurveyQues.vue"
      ),
  },
  {
    path: "/bmp",
    name: "BoardMembers",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "IamgeFromServer" */ "../views/BoardMembers.vue"
      ),
  },
    {
    path: "/EditPage",
    name: "EditableParagraphs",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "EditableParagraphs" */ "../views/EditableParagraphs.vue"
      ),
    },

    {
    path: "/qrcodegen",
    name: "QrCode",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "QrCodeGenerator" */ "../views/QrCode.vue"
      ),
    },

    {
        path: "/playlist",
        name: "PlayList",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "PlayList" */ "../views/PlayList.vue"
      ),
    },

    {
        path: "/seform",
        name: "SeasonEpisode",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "PlayList" */ "../components/SeasonEpisode.vue"
      ),
    },

    {
        path: "/seasonlist",
        name: "SeasonList",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "PlayList" */ "../components/SeasonList.vue"
      ),
    },

        {
        path: "/seupdate",
        name: "SeasonEpisodeUpdate",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "PlayList" */ "../components/SeasonEpisodeUpdate.vue"
      ),
    },

    {
        path: "/live",
        name: "LiveStream",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "LiveStream" */ "../components/LiveFed.vue"
      ),
    },

        {
        path: "/tv",
        name: "WatchLive",
        props: true,
        component: () =>
          import(
        /* webpackChunkName: "WatchLive" */ "../components/WatchLive.vue"
      ),
    },

    {
      path: "/jlist",
      name: "JournalistList",
      props: true,
      component: () =>
        import(
          /* webpackChunkName: "JournalistList" */ "../components/JournalistList.vue"
        )
    }

      //   {
      //   path: "/liveFed",
      //   name: "LiveFed",
      //   props: true,
      //   component: () =>
      //     import(
      //   /* webpackChunkName: "LiveStream" */ "../components/LiveFed.vue"
      // ),
    //},

]

const router = createRouter({
  history: createWebHashHistory(),
  routes,

  scrollBehavior(to, from, savedPosition) {
    // Restore the previous scroll position when using
    // browser Back/Forward buttons
    if (savedPosition) {
      return savedPosition;
    }

    // Otherwise, go to the top of the page
    return {
      top: 0,
      left: 0,
    };
  },
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


/**
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('token');
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isAuthenticated) {
      next('/login');  // Redirect to login page if not authenticated
    } else {
      next();  // Proceed to the authenticated route
    }
  } else {
    next();  // Always call next() if route doesn't require authentication
  }
});
 */

export default router;
