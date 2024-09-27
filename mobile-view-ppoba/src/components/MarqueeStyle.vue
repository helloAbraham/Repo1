<template>
    <div class="marquee-container">
      <div class="marquee-text" :style="{ animationDuration: animationDuration }"><h2>{{ currentDate }}</h2></div>
    </div>
  </template>
  
  <script>
  export default {
    name:'MarqueeStyle',
    data() {
      return {
        currentDate: '',
        animationDuration: '20s' // Adjust the duration of the animation as needed
      };
    },
    mounted() {
      // Update currentDate with the current date when the component is mounted
      this.updateCurrentDate();
    },
    methods: {
      updateCurrentDate() {
        // Get the current date
        const today = new Date();
        // Format the date as desired (e.g., 'March 7, 2024')
        const formattedDate = today.toLocaleDateString('en-US', { 
          weekday: 'long', 
          year: 'numeric', 
          month: 'long', 
          day: 'numeric' 
        });
        // Update currentDate with the formatted date
        this.currentDate = formattedDate;
      }
    }
  };
  </script>
  
  <style scoped>
  .marquee-container {
    width: 100%;
    overflow: hidden;
    font-style: oblique;
    color:rgb(186, 14, 224);
  }
  
  .marquee-text {
    white-space: nowrap; /* Prevent text from wrapping */
    animation: marquee 20s linear infinite; /* Adjust the duration and timing function as needed */
  }
  
  @keyframes marquee {
    0% {
      transform: translateX(100%);
    }
    100% {
      transform: translateX(-100%);
    }
  }

  </style>
  