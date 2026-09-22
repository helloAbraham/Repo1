<template>
    <div>
        <h2>Create Account</h2>
        <!-- we can use either v-if="allErrors.length > 0"-->
        <div  class="box" v-show="allErrors.length > 0">
            <li v-for="(error, index) in allErrors" :key="index">
                {{ error }}
            </li>
        </div>
        <form @submit.prevent="submitButtonPressed" novalidate>
            <div >Full Name
            <BaseInput 
                type="text" 
                v-model="user.name" 
            />
            </div>
            <div>Email
            <BaseInput 
                type="email" 
                v-model="user.email"
            />
            </div>
            <div>Password
            <BaseInput
            type="password" 
            v-model="user.password"
            />
            </div>
        <div>
            <BaseButton type="submit">SIGN UP</BaseButton>
        </div>
        </form>
    </div>
</template>

<script>
import BaseButton from '@/components/BaseButton.vue';
import BaseInput from '@/components/BaseInput.vue';

    export default {
    name: "SignUpExm",
    data() {
        return {
            user: {
                name: "",
                email: "",
                password: ""
            },
            allErrors: []
        };
    },
    methods: {
        submitButtonPressed() {
            this.allErrors = [];
            if(!this.user.name){
                this.allErrors.push("Name field is empty")
            }
            if(!this.user.email){
                this.allErrors.push("Email field is empty")
            } else {
                if(!this.validEmail(this.user.email)){
                    this.allErrors.push("Invalid email");
                }
            }
            if(!this.user.password){
                this.allErrors.push("Password field is empty")
            } else {
                if(this.user.password.length < 8){
                    this.allErrors.push("Password must be at least 8 characters long")
                }
            }
            console.log(this.allErrors);
        },

        validEmail(email){
            const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return re.test(email);
        }
    },
    components: { 
        BaseInput, 
        BaseButton 
    }
}
</script>

<style scoped>
.box {
    background-color: lightgrey;
    width: 435px;
    border: 1px solid green;
    padding: 10px;
    margin: 0px;
    color: red;
}
.title-position{
    position:inherit;
}
</style>