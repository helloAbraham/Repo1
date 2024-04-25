import { createRouter, createWebHashHistory } from "vue-router";
import EventList from "../views/EventList.vue";
import NotFound from "../views/NotFound.vue";
import PageRender from "../views/PageRender.vue";



const routes = [
  {
    path: "/",
    name: "EventList",
    component: EventList,
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
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "About" */ "../views/AboutView.vue"),
  },

  {
    path: "/createevent",
    name: "CreateEvent",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "eventdetails" */ "../views/CreateEvent.vue"),
  },

  {
    path: "/DynamicTextField",
    name: "DynamicTextField",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "eventdetails" */ "../views/DynamicTextField.vue"),
  },
  {
    path: "/MyCount",
    name: "MyCount",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "mycount" */ "../views/MyCount.vue"),
  },
  {
    path: "/FormValidate",
    name: "SubmitFormModel",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "FormValidate" */ "../views/SubmitFormModel.vue"),
  },

  {
    path: "/PageRender",
    name:"PageRender",
    component: PageRender
  },

  {
    path: "/signup",
    name: "SignUp",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "SignUp" */ "../views/SignUp.vue"),
  },

  {
    path: "/floatinglabeltexbox",
    name: "FloatingLabelTextbox",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "FloatingLabelTextbox" */ "../components/FloatingLabelTextbox.vue"),
  },
  {
    path: "/cdate",
    name: "CustomeSelectedDate",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "CustomeSelectedDate" */ "../views/CustomeSelectedDate.vue"),
  },
  {
    path: "/lifecyclehook",
    name: "LifecycleExample",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "CustomeSelectedDate" */ "../components/LifecycleExample.vue"),
  },
  {
  path: "/mountedValidation",
  name: "ValidationComponent",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "CustomeSelectedDate" */ "../components/ValidationComponent.vue"),
},

{
  path: "/fitraPage",
  name: "FitraPage",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/FitraPage.vue"),
},

];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
