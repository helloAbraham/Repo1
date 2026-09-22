<template>
    <div class="body">
        <form class="form-style" @submit.prevent="handleSubmit">
            <h2 class="input-fields">LOGIN &nbsp; <i class='fas fa-user-alt' style='font-size:36px'></i> </h2>
            <div class="input-fields">
                <input type="email" v-model="email" placeholder="" required>
                <label for="email">Email</label>
            </div>
            <div class="input-fields">
                <input :type="passwordFieldType" v-model="password" placeholder="" required>
                <label for="password">Password</label>
                <span @click="togglePasswordVisibility">
                    <i :class="passwordFieldType === 'password' ? 'fas fa-eye' : 'fas fa-eye-slash'"></i>
                </span>
            </div>
            <input type="submit" value="Login" id="btn">
            <div id="forget_password">
                <i>Forgot Password?</i><span>Click here</span>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: "FloatingLabelTextbox",
    data() {
        return {
            email: '',
            password: '',
            passwordFieldType: 'password'
        };
    },
    methods: {
        togglePasswordVisibility() {
            this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password';
        },
        handleSubmit() {
            // Handle form submission
            console.log('Email:', this.email);
            console.log('Password:', this.password);
        }
    }
}
</script>

<style scoped>
.body {
    margin: 0;
    padding: 0;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #1f3040;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    box-sizing: border-box;
}
.form-style {
    height: 450px;
    width: 450px;
    background-color: #fff;
    padding: 10px 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    box-shadow: -3px 3px 10px rgba(0, 0, 0, 0.4);
}
.h1 {
    text-align: center;
    position: center;
}
.input-fields {
    position: relative;
    width: 400px;
    height: 40px;
    margin-top: 25px;
    left: 20px;
}
.input-fields input {
    width: 100%;
    height: 100%;
    outline: 1.4px solid black;
    border: navajowhite;
    border-radius: 8px;
    padding: 5px 10px;
    padding-left: 10px;
    font-size: 18px;
}
.input-fields input:focus {
    outline: 2px solid #2692df;
}
.input-fields input:focus + label {
    top: -10px;
    color: green;
}
.input-fields label {
    position: absolute;
    top: 15px;
    left: 15px;
    font-size: 18px;
    padding: 0 5px;
    pointer-events: none;
    transition: top .2s;
    background-color: white;
    color: rgb(99,99,99);
}
.input-fields input:not(:placeholder-shown) + label {
    top: -10px;
    color: green;
}
.input-fields span {
    cursor: pointer;
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
}
#btn {
    margin-top: 25px;
    width: 115px;
    padding: 10px 20px;
    border: none;
    color: white;
    background-color: #1f3040;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
}
#forgot_password span {
    text-decoration: underline;
    cursor: pointer;
}
#forget_password {
    padding-top: 75px;
    text-align: center;
    margin-top: 10px;
}
</style>