<template>
    <section class="topPadding">
    <div>
      <h2>Login</h2>
      <form @submit.prevent="login">
        <label>Username:</label>
        <input type="email" v-model="email" required>
        <br>
        <label>Password:</label>
        <input type="password" v-model="password" required>
        <br>
        <button type="submit">Login</button>
      </form>
    </div>
</section>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    name:"StuLogin",
    data() {
      return {
        email: '',
        password: ''
      };
    },
    methods: {
      login() {
        axios.post('http://localhost:8090/api/v1/student/login', {
          email: this.email,
          password: this.password
        }).then(response => {
          console.log(response.data);
          // Handle successful login (e.g., store token in localStorage)
        }).catch(error => {
          console.error('Error logging in:', error);
          // Handle login error
        });
      }
    }
  }
  </script>
  
  
  <style scoped>
    .topPadding {
    padding-top: 200px;
    padding-bottom: 100px;
  }
  </style>