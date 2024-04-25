<template>

<div class="background-container">
      <!-- Your page content goes here -->
      <div class="headerOne" v-if="event">
      <h3>{{ event.title }}</h3>
      <p>{{ event.time }} @ {{ event.location }}</p>
      <p>{{ event.description }}</p>
    </div>
    </div>
    
  </template>
  
  <script>
  import EventServiceThree from "@/services/EventServiceThree.js";
  
  export default {
    name: "UpcomingEventDetails",
    props: ["id"],
    data() {
      return {
        event: null,
      };
    },
  
    created() {
      //fetch event (by id) and set local data
      EventServiceThree.getEvent(this.id)
        .then((response) => {
          this.event = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  };
  </script>
  <style scoped>
  .background-container {
    background-image: url('../assets/currentImage/Asr_event.jpg'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 100vh; /* Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
  }
  .headerOne {
    padding-top: 450px;
    padding-left: 175px;
    font-size: 25px;
    color: rgb(9, 222, 112);
    justify-content: flex-end;
  }
  </style>