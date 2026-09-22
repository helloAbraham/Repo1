<template>
    <div :style="backgroundStyle" class="background-container">
      <!-- Your content here -->
      <p>Your content goes here</p>
    </div>
  </template>
  
  <script>
  export default {
    name:'ImageFromServer',
    data() {
      return {
        imageUrl: '', // To hold the URL of the background image
      };
    },
    computed: {
      backgroundStyle() {
        return {
          backgroundImage: `url(${this.imageUrl})`,
          backgroundSize: 'cover', // Ensure the image covers the container
          backgroundPosition: 'center', // Center the image
          backgroundRepeat: 'no-repeat' // Prevent repeating the image
        };
      }
    },
    async created() {
      try {
        const response = await fetch('https://my-json-server.typicode.com/MasjidAlTaufiq/ppobaimg'); // Replace with your JSON URL
        const data = await response.json();
        this.imageUrl = data.imageUrl; // Adjust this based on your JSON structure
      } catch (error) {
        console.error('Error fetching the JSON data:', error);
      }
    }
  };
  </script>
  
  <style scoped>
  .background-container {
    height: 100vh; /* Full viewport height */
    width: 100%; /* Full width */
  }
  </style>
  