<template>
    <h1>Previous Events:</h1>
    <div class="events">
      <EventCardPrevious v-for="event in events" :key="event" :eventabc="event" />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import EventCard from "@/components/EventCard.vue";
  import EventCardPrevious from "@/components/EventCardPrevious.vue";
  import EventServicePrevious from "@/services/EventServicePrevious.js";
  
  export default {
    name: "EventListPrevious",
    components: {
      EventCard,
      EventCardPrevious,
    },
  
    data() {
      return {
        events: null,
      };
    },
    created() {
      EventServicePrevious.getEvents()
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
  