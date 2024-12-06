<template>
    <div>
      <h2>Child Component</h2>
      <p>{{ message }}</p> <!-- Display the message received as a prop -->
    </div>
    <BaseButtonStyle>This is Button with Style</BaseButtonStyle>
   
  </template>
  
  <script>
  import BaseButtonStyle from './BaseButtonStyle.vue';


  export default {
    name: 'ChildComponent',
    props: {
      message: {
        type: String,
        //required: true // Prop is required and must be a string
        default: "Current State"
      }
    },

    components: {
    
        BaseButtonStyle
    },
    data () {
        return {

        }
    }
  }
  </script>
  