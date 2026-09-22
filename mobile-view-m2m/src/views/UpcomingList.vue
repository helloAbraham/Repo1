<template>
    <h1>Event in Mosqoue:</h1>
    <div class="events">
      <UpcomingEventCard v-for="event in events" :key="event" :eventabc="event" />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import JumaEventCard from "@/components/JumaEventCard.vue";
  import UpcomingEventCard from "@/components/UpcomingEventCard.vue"
  import EventServiceThree from "@/services/EventServiceThree.js";
  
  export default {
    name: "UpcomingList",
    components: {
      JumaEventCard,
      UpcomingEventCard
    },
  
    data() {
      return {
        events: null,
      };
    },
    created() {
      EventServiceThree.getEvents()
        .then((response) => {
          this.events = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  };
  </script>
  
  <style scoped>
  .events {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  </style>
  
  <!--
    //This is first example. 
    but the problem is Each component creates a new Axios instance
    Our API code is all over our application
      created() {
      axios
        .get("https://my-json-server.typicode.com/helloAbraham/Repo1/events")
        .then((response) => {
          //console.log("events:", response.data);
          //Now we can see we are getting data in the console, coming from the server.
          //This time We can set the data to our local component
          this.events = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  -->
  