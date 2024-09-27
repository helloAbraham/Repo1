<template>
    <div>
      <input v-model="message" placeholder="Type your message" />
      <button @click="sendMessage">Send</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        message: ''
      };
    },
    methods: {
      sendMessage() {
        if (this.message.trim()) {
          this.$emit('add-message', this.message);
          this.message = ''; // Clear the input field
        }
      }
    }
  };
  </script>
  