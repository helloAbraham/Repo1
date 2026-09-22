<template>
    <div class="banner-container">
      <div class="banner-text">{{ bannerText }}</div>
    </div>
  </template>
  
  <script>
  export default {
    name:"BannerText",
    data() {
      return {
        bannerText: 'Your Banner Text Here'
      };
    },
    mounted() {
      // Start the animation when the component is mounted
      this.startAnimation();
    },
    methods: {
      startAnimation() {
        // Add a CSS class to start the animation
        this.$refs.bannerText.classList.add('slide-from-right');
      }
    }
  };
  </script>
  
  <style scoped>
  .banner-container {
    width: 100%;
    overflow: hidden;
  }
  
  .banner-text {
    white-space: nowrap; /* Prevent text from wrapping */
  }
  
  .slide-from-right {
    animation: slideRightToLeft 10s linear infinite; /* Adjust duration and timing function as needed */
  }
  
  @keyframes slideRightToLeft {
    0% {
      transform: translateX(100%);
    }
    100% {
      transform: translateX(-100%);
    }
  }
  </style>
  