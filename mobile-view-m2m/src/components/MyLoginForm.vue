<template>
    <section class="topPadding">
    <div class="login-form">
      <h2>Login</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" v-model="username" id="username" required />
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" v-model="password" id="password" required />
        </div>
        <button type="submit">Login</button>
      </form>
      <p v-if="error" class="error">{{ error }}</p>
    </div>
</section>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'MyLoginForm',
    data() {
      return {
        username: '',
        password: '',
        error: ''
      };
    },
    methods: {
      async login() {
        try {
          const response = await axios.post('http://localhost:8090/api/auth/login', {
            username: this.username,
            password: this.password
          });
          if (response.status === 200) {
          console.log('Login successful:', response.data);
          // Handle successful login, e.g., store token, redirect, etc.
        } else {
          this.error = 'Unexpected response status: ' + response.status;
        }
          // Handle successful login, e.g., store token, redirect, etc.
        } catch (error) {

            if(error.response){
                //handle resonse from server
                if(error.response === 302){
                    this.error = 'Redirection detected. Please check your backend configuration';
                } else if(error.response.status === 401) {
                    this.error = 'Invalid username or password';

                } else {
                    this.error = 'An error occured: ' + error.response.status;
                }
            } else {
                //handle other error
                this.error = 'An error occured: ' + error.response.message;
            }

          //this.error = 'Invalid username or password';
          console.error('Login failed:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .topPadding {
    padding-top: 200px;
    padding-bottom: 100px;
  }
  .login-form {
    padding-top: 200px;
    max-width: 300px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #f9f9f9;
  }
  

  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .error {
    color: red;
    margin-top: 10px;
  }
  </style>
  