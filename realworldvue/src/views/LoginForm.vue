<template>
   <form @submit.prevent="onSubmit">
        <BaseInput
            label="Email"
            type="email"
            v-model="email"
            :error="emailError"
        />
        <BaseInput
            label="Password"
            type="password"
        />
        <BaseButton
            type="submit"
            class="-fill-gradient"
        >
        Submit
        </BaseButton>
   </form>
</template>

<script>
import BaseButton from '@/components/BaseButton.vue';
import BaseInput from '@/components/BaseInput.vue';
import { useField } from 'vee-validate';

    export default {
        name: "FormInput",

    components: { BaseInput, BaseButton },
    data() {
        return {
            onSubmit,
            email: "",
            emailError: "",
        }
    },
    methods: {
        onSubmit() {
            alert('Form has been submitted')
            const { value: email, errorMessage: emailError } = useField('email', function(value) {
                if(!value) 
                    return 'This field is required';
                const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                if(!emailRegex.test(String(value).toLowerCase())) {
                    return "Please enter a valid email address"
                }
                return true
            })
        }

    }
}
</script>

<style scoped>

</style>