<template>
    <div class="background-container">
      <!-- Your page content goes here -->
      <h1 class="headerOne">Ramadan Mubarak-2024</h1>
      <h2 class="headerTwo">Taraweeh Praying in Ramadan</h2>
      
    </div>
  </template>
  
  <script>
  export default {
    name:"BackgroundImageAdd",
    // Component logic if needed
  };
  </script>
  
  <style scoped>
  .background-container {
    background-image: url('../assets/islamic_image_1.jpg'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 100vh; /* Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
    display: flex;
    flex-wrap: wrap;
  }
  .headerOne {
    padding-top: 200px;
    padding-left: 300px;
    font-size: 45px;
    color: bisque;
  }
  .headerTwo {
    padding-left: 300px;
    font-size: 45px;
    color: bisque;
  }
  </style>
  