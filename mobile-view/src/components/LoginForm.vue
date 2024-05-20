<template>
  
        <div class="body">
            <form class="form-style" @submit.prevent="handleSubmit" action="" >
                <h2 class="input-fields">LOGIN</h2>
                <div class="input-fields">
                    <input type="text" v-model="username" id="" placeholder="" required="true">
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
            </div>
            <div id="forget_password">
                <i>Forgot Password?</i><span>Click here</span>
            </div>
            </form>
        </div>
 
</template>

<script>
import axios from 'axios';
import LoginService from '../services/LoginService';
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
            }
    },

        data() {
            return {
                username:'',
                password: '',
                showPassword: false,
            }
        },
        created() {
            LoginService.postEvent()
            .then((response) => {
            this.events = response.data;
        })
            .catch((error) => {
             console.log(error);
        });
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

        methods: {
            async handleSubmit(){
                try {
                    const response = await axios.post('http://localhost:8090/login', {
                        username: this.username,
                        password: this.password
                    });
                    console.log('Form Submitted Successfully.', response.data);
                    //Handle successful login (e.g. redirect to dashboard, store toke, etc)
                    alert('Login Successful! You made it..');
                    this.$router.push({ name: 'Dashboard' });
                } catch (error) {
                    console.error('Login failed:', error);
                    // Handle login failure (e.g., show error message)
                    this.$router.push({ name: 'LoginForm' });
                }

                // Clear the form by resetting the data properties
                this.resetForm();
            },
            resetForm(){
                this.username='';
                this.password='';
                this.showPassword=false; //Optionally reset the showPassword flag

            },
             //This is the method we can redirect user to another page or component
            redirectToLogin(){
                this.$router.push({ name:'Home'})
                // <router-link class="link" :to="{name: 'Home'}">Home</router-link>
            },
            togglePasswordVisibility() {
                this.showPassword = !this.showPassword;
                }
            }
    }
</script>

<style scoped>
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
    margin-top: 20px;
   
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
    top: 15px;
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
    margin-top: 25px;
    width: 115px;
    text-align: center;
    text-decoration:aqua;
    text-transform: uppercase;
    transition: 0.5s;
    padding: 10px 20px;
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
    
    text-decoration: underline;
    cursor: pointer;

}
#forget_password {
    padding-top: 75px;
    text-align: center;
    margin-top: 10px;
}
.toggle-password {
cursor: pointer;
}
</style>