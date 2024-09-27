
<template>
  
        <div class="body">
            <transition name="fade">
            <form v-if="showForm" class="form-style" @submit.prevent="handleSubmit" action="" >
                <h2 class="input-fields">LOGIN</h2>
                <div class="input-fields">
                    <input type="email" v-model="email" id="email" placeholder="" required="true">
                    <label for="">User Name</label>
                </div>
                <div class="input-fields">
                    <input 
                    :type="showPassword ? 'text' : 'password'" 
                    v-model="password"
                    name="" 
                    id="password" 
                    placeholder="" 
                    required>
                    <label for="">Password</label>
                    <span @click="togglePasswordVisibility" class="toggle-password">
                         <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
                     </span> 
            <input type="submit" value="Login" name="" id="btn">
           <ButtonLink @click="redirectToLogin">Cancel</ButtonLink>
           <br/>
                 <router-link to="/register">
                  Don't have an account? Register.
                </router-link>
            </div>
                
       
            <div id="forget_password">
                <i>Forgot Password?</i><span>Click here</span>
            </div>
           
            </form>
            </transition>
            <transition name="fade">
      <div v-if="showSuccessMessage" class="success-message" key="success">
        Form submitted successfully!
        </div>
         </transition>
      
        </div>
       
</template>

<script>

import axios from 'axios';
import navigation from '../components/Navigation.vue';
import VueAxios from 'vue-axios';
    export default {
        name: "LoginForm",
        props: {
            isOpenForm: {
                type: Boolean,
                required: true
            },
            title: {
                type: String,
                default: 'Modal Title'
            },
    },

        data() {
            return {
                showForm: true,
                showSuccessMessage: false,
                email:'',
                password: '',
                showPassword: false,
                isLoggedIn: false,
                status: false,
                // credentials: {
                //     username:'',
                //     password:''
                // },
            }
        },
    //     created() {
    //         LoginService.postEvent()
    //         .then((response) => {
    //         this.events = response.data;
    //     })
    //         .catch((error) => {
    //          console.log(error);
    //     });
    // },
    components: {
        navigation,
    },
        // data() {
        //     return {
        //         isOpen: false,
        //     }
        // },

        // methods: {
        //     toggleModal() {
        //         this.isOpen = !this.isOpen
        //     }
        // }
        computed: {
          
        },
        methods: {

            //simple method login()
            login() {
                axios.post('http://localhost:8090/api/auth/login', {
                    username: this.username,
                    password: this.password
                })
                
                .then(({ data }) => {
                    //handle the response data
                    console.log(data);
                })
                .catch( error => {
                    //handle the error
                    console.log(error);
                });
                this.$router.push({ name: 'Dashboard' });
            },
            
            async handleSubmit(){
                // this.showForm = false;
                // this.showSuccessMessage = true;
                // setTimeout(() => {
                // this.resetForm();
                // }, 3000); // Optional: Reset the form after 3 seconds
                try {  
                    const response = axios.post('http://localhost:8090/api/v1/student/login', {
                        email: this.email,
                        password: this.password
                    })
                    .then(response => {
                        console.log(response.data);
                        console.log(response.data.status);
                        localStorage.setItem('token', response.data.token);
                        alert(response.data.message);
                        if(response.data.status === true)
                        this.$router.push({ name: 'Dashboard' }); 
                    })
                    console.log('Form Submitted Successfully.', response.data);
                    // Example: store token in localStorage or Vuex store
                      //  localStorage.setItem('token', response.data.token);
                        // Redirect to authenticated route
                    //Handle successful login (e.g. redirect to dashboard, store token, etc)
                  //  alert('Login Successful! You made it..');
                //   if( this.status !== response.data.status){
                  // this.$router.push({ name: 'Dashboard' }); 
                //     } else {

                //     }
                    
                    this.showSuccessMessage = true;
                    //If successful login so that logout icon and page will show on navbar
                    this.$store.state.isLoggedIn = true;


                  
                } catch (error) {
                    console.log('Login failed:', error);
                    // Handle login failure (e.g., show error message)
                    this.$router.push({ name: 'LoginForm' });
                    alert('Login Error');
                    this.showSuccessMessage ='An error occurred while logging in';
                }
                  
                // Clear the form by resetting the data properties
                this.resetForm();

            },
            resetForm(){
                this.email='';
                this.password='';
                this.showPassword=false; //Optionally reset the showPassword flag
                this.showSuccessMessage = false;
                this.showForm = true;
            },
             //This is the method we can redirect user to another page or component
            redirectToLogin(){
                
                this.$router.push({ name:'Home'})
                
               // this.$emit('updateActive', !this.isActive);
            },
                togglePasswordVisibility() {
                    this.showPassword = !this.showPassword;
                },
            
                /**
                 async login() {
                try {
                    const response = await api.login(this.credentials);
                    this.message = response.data;
                } catch (error) {
                    this.message = 'Login failed';
                    }
                }    */
            }
    }
</script>

<style scoped>
@import '~nprogress/nprogress.css';
.body{
    margin: 0;
    padding: 0px;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #1f3040;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    box-sizing: border-box;
}
   .form-style{
    height: 320px;
    width: 440px;
    background-color: #fff;
    padding: 10px 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    box-shadow: -3px 3px 10px rgba(0, 0, 0, 0.4);

}
.h1{
    text-align: center;
    position: center;
}
.input-fields{
    position: relative;
    width: 400px;
    height: 40px;
    margin-top: 15px;
   
    text-align: center;
}
.input-fields input{
    width: 100%;
    height: 100%;
    outline: 1.4px solid black;
    border: navajowhite;
    border-radius: 8px;
    padding: 5px 10px;
    padding-left: 10px;
    font-size: 18px;
}
.input-fields input:focus{
    outline: 2px solid#2692df;
}
.input-fields input:focus + label {
    top: -10px;
    color: green;
}

.input-fields label{
    position: absolute;
    top: 10px;
    left: 15px;
    font-size: 18px;
    padding: 0 5px;
    pointer-events: none;
    transition: top 0.5s;
    background-color: white;
    color: rgb(99,99,99);

}
.input-fields input:not(:placeholder-shown) + label {
    top: -10px;
    color: green;
}
.input-fields span{
    position: absolute;
    top: 15px;
    left: 350px;
}

#btn{
    margin-top: 15px;
    margin-bottom: 10px;
    width: 115px;
    text-align: center;
    text-decoration:aqua;
    text-transform: uppercase;
    transition: 0.5s;
    padding: 10px 25px;
    box-shadow: 0 0 20px #eee;
    border: none;
    color: white;
    background-color: #d19be9;
    border-radius: 10px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
}

#btn-2 {
  background-image: linear-gradient(to right, #f298d9 0%, #a6c1ee 51%, #dc6cbc 100%);
}
 
  #btn:hover {
    background-position: right center; /* change the direction of the change here */
    -webkit-transform: scale(1.02);
    transform: scale(1.02);
    box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    background-image: linear-gradient(to right, #f298d9 0%, #a6c1ee 51%, #dc6cbc 100%);
  }
#forgot_password span{
    margin-top: 45px;
    margin-bottom: 5px;
    text-decoration: underline;
    cursor: pointer;

}
#forget_password {
    padding-top: 85px;
    text-align: center;
    margin-top: 10px;
}
.toggle-password {
cursor: pointer;
}

.fade-enter {
    opacity: 0;
}
.fade-enter-active, .fade-leave-to {
    transition: opacity 0.7s ease-out;
}
.fade-leave-to {
    opacity: 0;
}

.success-message {
  font-size: 1.2em;
  color: green;
}
</style>