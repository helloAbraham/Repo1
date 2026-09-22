<!-- Card.vue -->
<template>
    <div class="card" id="topNavMargin">
      <img :src="imageUrl" alt="Profile Picture" class="card-image"/>
      <div class="card-content">
        <h3 class="card-name">{{ name }}</h3>
        <p class="card-position">{{ position }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name:'MemberCard',
    props: {
      name: {
        type: String,
        required: true
      },
      position: {
        type: String,
        required: true
      },
      imageUrl: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  #topNavMargin {
    margin-top: 175px;
    margin-left: 25px;
  }
  .card {
    position: relative;
    border: 1px solid #ddd;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    background-color: #c3e9a1;
    transition: transform 0.2s;
  }
  
  .card:hover {
    transform: translateY(-15px);
  }
  
  .card-image {
    width: 100%;
    height: auto;
  }
  
  .card-content {
    padding: 16px;
  }
  
  .card-name {
    margin: 0;
    font-size: 1.2em;
    color: #333;
  }
  
  .card-position {
    position:sticky;
    margin: 5px 0 0;
    color: #777;
  }
  </style>
  