<template>
    <section id="top-padding">
        <div class="body">
        <div class="sign-up" >
        <h2 id="h2">Sign Up</h2>
            <form @submit.prevent="handleSubmit" action="">
                <div class="form-group">
                <label for="name">User Name:</label>
                <input type="text" v-model="username" id="name" required>
                </div>

                <div class="form-group">
                <label for="password">Password:</label>
                <input 
                    :type="showPassword ? 'text' : 'password'" 
                    v-model="password"
                    name="" 
                    id="password" 
                    placeholder="" 
                    required >
                    <span @click="togglePasswordVisibility" class="toggle-password">
                         <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
                     </span> 
                </div>
                <div class="form-group">
                <label for="role">Role:</label>
                <input type="role" v-model="role" id="role" required>
                </div>
                <div class="button-position">
                <button class="btn btn-2" type="submit">Sign Up</button>
        
                <button class="btn btn-2" @click="redirectToLogin">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    </section>
  </template>
  
  <script>
import ButtonLink from './ButtonLink.vue';
import axios from 'axios';
  export default {
    name:'SignUp',
    data() {
      return {
        username: '',
        password: '',
        role: '',
        showPassword: false,
      };
    },
    components: {
        ButtonLink,
    },
    methods: {
        async handleSubmit(){
            try {
                const response = await axios.post('http://localhost:8090/register/user', {
                    username: this.username,
                    password: this.password,                        
                    role: this.role
                    });
                    console.log('Form Submitted Successfully.', response.data);
                    //Handle successful login (e.g. redirect to dashboard, store toke, etc)
                    this.$router.push({ name: 'LoginForm' });
                } catch (error) {
                    console.error('Sign up failed:', error);
                    // Handle login failure (e.g., show error message)
                    this.$router.push({ name: 'LoginForm' });
                }

                // Clear the form by resetting the data properties
                this.resetForm();
            },
            resetForm(){
                this.username='';
                this.password='';
                this.role='';
            },

        togglePasswordVisibility() {
            this.showPassword = !this.showPassword;
        },
      //This is the method we can redirect user to another page or component
      redirectToLogin(){
        this.$router.push({ name:'Home'})
       // <router-link class="link" :to="{name: 'Home'}">Home</router-link>
      }
    }
  };
  </script>
  
  <style scoped>
  #top-padding {
    padding-top: 135px;
    background-color: #1f3040;
    }

.body{
   
    padding-top: 0px;
    width: 100vw;
    height: 100vh;
    justify-content: center;
    align-items: center;
    background-color: #1f3040;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}
  .sign-up {
 
    background-color: #f1f2da;
    max-width: 320px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
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
  #h2{
    text-align: center;
    position: center;
    color: rgb(108, 7, 240);
    text-shadow:#19de5b;
}
.button-position {
    text-align: center;
}


.btn {
  flex: 1 1 auto;
  margin: 10px;
  padding: 10px 20px;
  text-align: center;
  text-decoration:aqua;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: rgb(255, 255, 255);
  text-shadow: #070707;
  box-shadow: 0 0 20px #eee;
  border-radius: 10px;
  cursor:pointer;
 }

 .btn-2 {
  background-image: linear-gradient(to right, #f298d9 0%, #a6c1ee 51%, #dc6cbc 100%);
}
 
  button:hover {
    background-position: right center; /* change the direction of the change here */
    -webkit-transform: scale(1.02);
    transform: scale(1.02);
    box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    background-image: linear-gradient(to right, #f298d9 0%, #a6c1ee 51%, #dc6cbc 100%);
  }
  .toggle-password {
  padding-top: 7px;
  left: 790px;

  cursor: pointer;
}

.pading-button {
    padding-top: 25px;
}
  </style>
  