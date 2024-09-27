<template>
    <div class="profile">
      <img :src="profile.avatar" :alt="profile.name">
      <h2>{{ profile.name }}</h2>
      <p>{{ profile.bio }}</p>
      <ul>
        <li v-for="(value, key) in profile.details" :key="key">
          {{ key }}: {{ value }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ProfileOne',
    props: {
      profile: {
        type: Object,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .profile {
    text-align: center;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .profile img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-bottom: 10px;
  }
  </style>
  