<template>
    <div class="topPadding">
        <h1>You can logout now. </h1>
        <button @click="logout">Logout</button>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'LogoutComponent',

    data() {
        return {

        }
    },

    methods: {
      logout() {
        axios.post('http://localhost:8090/api/auth/logout')
          .then(() => {
            // Redirect to the home page or perform any other action
            window.location.href = '/';
          })
          .catch(error => {
            console.error('Logout failed:', error);
          });
      }
    }
  };
  </script>
<style scoped>
.topPadding {
    padding-top: 200px;
}
</style>