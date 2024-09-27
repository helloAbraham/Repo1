<template>
    <router-link
      class="event-link"
      :to="{ name: 'EventDetails', params: { id: eventabc.id } }"
    >
      <div class="event-card">
        <!-- Display event data -->
        <h2 class="title" style="margin-bottom: 10px;" >{{ eventabc.title }}</h2>
        <span class="fas fa-calendar" style="margin-bottom: 10px;">&nbsp;Date: {{ eventabc.date }}</span><br>
        <span class="fas fa-clock" style="margin-bottom: 10px;" >&nbsp;{{ eventabc.time }}</span><br>
        <span class="fas fa-location-dot" style="margin-bottom: 10px;">&nbsp; Location: {{ eventabc.location }}</span>
        <br>
        <span class="span-style" >Host: {{ eventabc.host }}</span>
        <br>
        <span class="span-status" style="margin-bottom: 10px;">Status : {{ eventabc.status }}</span>
      </div>
    </router-link>
  </template>
  
  <script>
  export default {
    name: "EventCard",
    props: {
      eventabc: {
        type: Object,
        required: true,
      },
    },
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  .event-card {
    padding: 20px;
    width: 350px;
    cursor: pointer;
    border: 2px solid #39495c;
    margin-bottom: 18px;
    border-radius: 10px;
    border-color: rgb(98, 236, 5);
    text-shadow: #39495c;
  }
  .event-card:hover {
    transform: scale(1.01);
    box-shadow: 0 3px 15px 0 rgba(0, 0, 0, 0.2);
  }
  .event-link {
    color: #2c3e50;
    text-decoration: none;
  }
  .title {
    background-color: bisque;
 
  }
  .span-style {
    background-color: rgb(176, 243, 246);
    font-size: 18px;

  }
  .span-status {
   
    background-color: rgb(223, 223, 218);
    font-size: 18px;
    

  }
  </style>
  