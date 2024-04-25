<template>
    <div class="gallery">
      <div v-for="(photo, index) in photos" :key="index" class="photo">
        <img :src="photo.url" :alt="photo.alt">
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PhotoGallery',
    props: {
      photos: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .gallery {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    grid-gap: 10px;
  }
  
  .photo {
    overflow: hidden;
    border-radius: 5px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  }
  
  .photo img {
    width: 100%;
    height: auto;
    display: block;
  }
  </style>
  