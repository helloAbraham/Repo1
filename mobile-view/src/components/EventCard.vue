<template>
    <router-link
      class="event-link"
      :to="{ name: 'EventDetails', params: { id: eventabc.id } }"
    >
      <div class="event-card">
        <!-- Display event data -->
        <h3>{{ eventabc.title }}</h3>
        <span>at {{ eventabc.time }}</span>
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
    width: 300px;
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
  </style>
  