<template>
    <section id="top-padding">
      
        <div class="body">
        <div class="sign-up" >
        <h2 id="h2">Member Registration</h2>
            <form ref="clearformdata" @submit.prevent="handleSubmit" action="">
                <div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" v-model="formData.firstName" id="firstName" required>
              
                <label for="lastName">Last Name:</label>
                <input 
                    v-model="formData.lastName"
                    name="" 
                    id="lastName" 
                    placeholder="" 
                    required >
               
                <label for="address">Address:</label>
                <input type="address" v-model="formData.address" id="address" required>
             
                <label for="city">City:</label>
                <input type="city" v-model="formData.city" id="city" required>
            
                <label for="state">State:</label>
                <input type="state" v-model="formData.state" id="state" required>
             
                <label for="zipCode">Zip Code:</label>
                <input type="zipCode" v-model="formData.zipCode" id="zipCode" required>
            
                <label for="email">Email:</label>
                <input type="email" v-model="formData.email" id="email" required>
             
                <label for="phone">Phone:</label>
                <input type="phone" v-model="formData.phone" id="phone" required>
                </div>
                
                <div class="button-position">
                <button class="btn btn-2" type="submit">Sign Up</button>
        
                <button class="btn btn-2" @click="redirectToLogin">Cancel</button>
                </div>
                <router-link to="/login">
                  Already Registered? Login.
                </router-link>
            </form>
        </div>
    </div>
  
    </section>
  </template>
  
<script>
import ButtonLink from './ButtonLink.vue';
import myAxiosInstance from '../services/MemberService';
export default {
    name:'MemberRegister',
    data() {
      return {
        formData: {
                  firstName: '',
                  lastName: '',
                  address: '',
                  city:'',
                  state:'',
                  zipCode:'',
                  email:'',
                  phone:'',
        },
      };
    },
    components: {
        ButtonLink,
    },
    methods: {
        async handleSubmit(){
            try {
                const response = await myAxiosInstance.post('/save', this.formData);
                this.response = response.data;    
                //alert('Registration successful! Please login.');
                   console.log('Form Submitted Successfully.', response.data);
                    //Handle successful login (e.g. redirect to dashboard, store toke, etc)
                    this.$router.push({ name: 'LoginForm' });
                } catch (error) {
                    console.error('Member registration failed:', error);
                    // Handle login failure (e.g., show error message)
                    this.$router.push({ name: 'MemberRegister' });
                }

                // Clear the form by resetting the data properties
                //this.resetForm();
                //No need above method
                //We can user ref
                this.$refs.clearformdata.reset();
            },
            resetForm(){
              this.firstName='',
              this.lastName= '',
              this.address= '',
              this.city='',
              this.state='',
              this.zipCode='',
              this.email='',
              this.phone=''
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
  @import '~nprogress/nprogress.css';
  #top-padding {
    padding-top: 100px;
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
.body-enter {
    opacity: 0;
}
.body-enter-active, .body-leave-to {
    transition: opacity 0.5s ease-out;
}
.body-leave-to {
    opacity: 0;
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
    margin-bottom: 1px;
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
  