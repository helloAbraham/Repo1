<template>
    <a :href="url" class="button">
      <slot></slot>
    </a>
  </template>
  
  <script>
  export default {
    name: 'ButtonLink',
    props: {
      url: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .button {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
  }
  
  .button:hover {
    background-color: #0be565;
  }
  </style>
  