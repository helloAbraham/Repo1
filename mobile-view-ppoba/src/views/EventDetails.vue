<template>

<div class="background-container">
      <!-- Your page content goes here -->
      
      <div class="headerOne" v-if="event">
        <!-- <img src='https://abrahamparker.com/jfk_image1.jpg' alt="Responsive Image" class="responsive-image"> -->
        <img :src="imageUrl" alt="Event Image" v-if="imageUrl" class="responsive-image">
        <!-- Show loading message or placeholder if imageUrl is not available -->
        <p v-else>Loading image...</p>
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
        imageUrl: null,
      };
    },
  
    created() {
      //fetch event (by id) and set local data
      EventService.getEvent(this.id)
        .then((response) => {
          this.event = response.data;
         
          this.imageUrl = this.event.imgUrl;//Adjust base on your JSON structure
          console.log(this.imageUrl);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  };
  </script>
  <style scoped>
  .background-container {
    
    display: flex;
    justify-content: center;
  }

  .responsive-image {
    display: flex;
   
    max-width: 100%;
    height: auto;
}
  .headerOne {
    padding-top: 145px;
    padding-left: 50px;
    font-size: 25px;
    font-weight: 900;
    color: rgb(9, 222, 112);
    justify-content: flex-end;
   
  }

  /* Media query for mobile screens */
  @media (max-width: 425px) {
  .responsive-image {
    margin-left: 0px;
    max-width: 375px;
    }
  }

 
  </style>