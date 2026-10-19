import { createRouter, createWebHashHistory } from "vue-router";

import EventList from "../views/EventList.vue";
import NotFound from "../views/NotFound.vue";
import PageRender from "../views/PageRender.vue";
import HomePage from "../views/HomePage.vue";



const routes = [
  {
    path: "/",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/evenlist",
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
    component: NotFound,
  },

  {
    path: "/upimage",
    name: "ImageUpload",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "imageupload" */ "../components/ImageUpload.vue"
      ),
  },

  {
    path: "/imagelist",
    name: "ImageList",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "imagelist" */ "../components/ImageList.vue"
      ),
  },

  {
    path: "/imagevisit",
    name: "ImageVisit",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "imagevisit" */ "../components/ImageVisit.vue"
      ),
  },

  {
    path: "/iam",
    name: "ImageVisitTwo",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "iam" */ "../components/ImageVisitTwo.vue"
      ),
  },

  {
    path: "/deletefile",
    name: "DeleteFile",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "deletefile" */ "../components/DeleteFile.vue"
      ),
  },
  
  {
    path: "/allimg",
    name: "MyImageGallery",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "deletefile" */ "../components/MyImageGallary.vue"
      ),
  },

  {
    path: "/blogs",
    name: "BlogPage",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "blogs" */ "../components/BlogPage.vue"
      ),
  },

  {
    path: "/contact",
    name: "ContactUs",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "contactus" */ "../components/ContactUs.vue"
      ),
  },

  {
    path: "/allContact",
    name: "AllContactPgn",
    props: true,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "contactus" */ "../components/AllContactPgn.vue"
      ),
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

{
  path: "/updateData",
  name: "ExampleDataUpdate",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../views/ExampleDataUpdate.vue"),
},

{
  path: "/updateDataRender",
  name: "ExampleDataUpdateRender",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../views/ExampleDataUpdateRender.vue"),
},

{
  path: "/Alert",
  name: "CreateAlert",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/CreateAlert.vue"),
},

// {
//   path: "/resume",
//   name: "HomePage",
//   // route level code-splitting
//   // this generates a separate chunk (about.[hash].js) for this route
//   // which is lazy-loaded when the route is visited.
//   component: () =>
//     import(/* webpackChunkName: "FitraPage" */ "../views/HomePage.vue"),
// },
{
  path: "/GridLayout",
  name: "GridLayout",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/GridLayout.vue"),
},

{
  path: "/responsive",
  name: "ResponsivePage",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/ResponsivePage.vue"),
},

{
  path: "/AlertSelected",
  name: "CreateAlertSelectVersion",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/CreateAlertSelectVersion.vue"),
},

{
  path: "/imageshow",
  name: "PictureImg",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "imageshow" */ "../components/PictureImg.vue"),
},

{
  path: "/visitingcard",
  name: "digitalId",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "imageshow" */ "../components/DigitalId.vue"),
},

{
  path: "/child",
  name: "ChildComponent",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "FitraPage" */ "../components/ChildComponent.vue"),
},

{
  path: "/parentslot",
  name: "ParemtNamedSlot",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "childslot" */ "../components/ParentNamedSlot.vue"),
},

{
  path: "/myaudio",
  name: "AudioPlayer",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "childslot" */ "../views/AudioPlayer.vue"),
},

{
  path: "/parentscopedslot",
  name: "LibraryScopedSlot",
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  component: () =>
    import(/* webpackChunkName: "childslot" */ "../components/LibraryScopedSlot"),
},
{
  path: "/dytvta",
  name: "DownloadFromYouTube",
  component: () =>
    import( "../components/DonwloadFromYouTube.vue"), // use the actual filename
},

{
  path: "/stopwatch",
  name: "StopWatch",
  component: () =>
    import( "../components/StopWatch.vue"), // use the actual filename
},

{
  path: "/game",
  name: "GamesPage",
  component: () =>
    import( "../views/GamesPage.vue"), // use the actual filename
},

{
  path: "/speaktext",
  name: "SpeakText",
  component: () =>
    import( "../components/SpeakText.vue"), // use the actual filename
},

{
  path: "/fighterjet",
  name: "FighterJet",
  component: () =>
    import( "../components/FighterJet.vue"), // use the actual filename
},

{
  path: "/alertpage",
  name: "PageForAlert",
  component: () =>
    import( "../components/PageForAlert.vue"), // use the actual filename
},

{
  path: "/editpage",
  name: "EditableParagraphs",
  component: () =>
    import( "../views/EditableParagraphs.vue"), // use the actual filename
},

{
  path: "/newsfed",
  name: "NewsFed",
  component: () =>
    import( "../components/NewsFed.vue"), // use the actual filename
},

{
  path: "/bricks",
  name: "BrincksGame",
  component: () =>
    import( "../views/BricksGame.vue"), // use the actual filename
},

{
  path: "/qrcodegen",
  name: "QrCode",
  component: () =>
    import( "../views/QrCode.vue"), // use the actual filename
},

{
  path: "/ballonpop",
  name: "BalloonPop",
  component: () =>
    import( "../components/BalloonPop.vue"), // use the actual filename
},

{
  path: "/audiocall",
  name: "AudioChat",
  component: () =>
    import( "../components/AudioChat.vue"), // use the actual filename
},

{
  path: "/seform",
  name: "SeasonEpisode",
  component: () =>
    import( "../components/SeasonEpisode.vue"), // use the actual filename
},

{
  path: "/jlist",
  name: "JournalistListPage",
  component: () =>
    import( "../components/JournalistListPage.vue"), // use the actual filename
},

{
  path: "/tvlist",
  name: "BanglaTV",
  component: () =>
    import( "../components/BanglaTV.vue"), // use the actual filename
},

{
  path: "/tvlist",
  name: "BanglaTV",
  component: () =>
    import( "../components/BanglaTV.vue"), // use the actual filename
},

{
  path: "/mltv",
  name: "MoldovaTV",
  component: () =>
    import( "../components/MoldovaTV.vue"), // use the actual filename
},

{
  path: "/live/:id",
  name: "LiveFed",
  component: () => import("@/components/LiveFed.vue")
},

{
  path: "/mp3player",
  name: "Mp3Player",
  component: () => import("@/components/Mp3Player.vue")
}


];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
