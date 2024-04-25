<template>
    <div>
      <!-- Image wrapped with anchor tag -->
      <a :href="linkUrl">
        <img :src="imageUrl" :alt="imageAlt">
      </a>
    </div>
  </template>
  
  <script>
  export default {
    name: 'LinkableImage',
    props: {
      imageUrl: {
        type: String,
        required: true
      },
      imageAlt: {
        type: String,
        required: true
      },
      linkUrl: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <!-- Scoped styles for the component -->
  <style scoped>
  /* Optional styling for the linkable image */
  a {
    /* Add your custom styles for the link */
  }
  </style>
  