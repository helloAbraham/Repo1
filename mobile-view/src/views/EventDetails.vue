<template>

<div class="background-container">
      <!-- Your page content goes here -->
      <div class="headerOne" v-if="event">
      <h2>{{ event.title }}</h2>
      <p>{{ event.time }} at {{ event.location }}</p>
      <p>{{ event.description }}</p>
    </div>
    </div>
    
  </template>
  
  <script>
  import EventService from "@/services/EventService.js";
  
  export default {
    name: "EventDetails",
    props: ["id"],
    data() {
      return {
        event: null,
      };
    },
  
    created() {
      //fetch event (by id) and set local data
      EventService.getEvent(this.id)
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
    background-image: url('../assets/currentImage/image1.jpg'); 
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 100vh; /* Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
  }
  .headerOne {
    padding-top: 375px;
    padding-left: 175px;
    font-size: 25px;
    font-weight: 900;
    color: rgb(9, 222, 112);
    justify-content: flex-end;
   
  }

  </style>