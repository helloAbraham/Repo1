<!-- This page using BaseInput without import because we use main.js register the component BaseInput -->
<template>
    <div class="header-margin">
        <h1>My App Input Example</h1>
        <form @submit.prevent="submit">
       <br />
        <!-- <BaseInput v-model="textInput"></BaseInput> -->
        <br />
        <br />
        <input
      type="email"
      placeholder="What's your email"
      :class="{ error: $v.email.$error }" 
      v-model="email"
      @blur="$v.email.$touch()" 
    >
        <!-- <p v-if="!$v.email.email">Please enter a valid email</p>
        <p v-if="!$v.email.required">Email is required</p> -->
        <!-- <div v-if="$v.email.$error">
      <p v-if="!$v.email.email" class="errorMessage">Please enter a valid email address.</p>
      <p v-if="!$v.email.$error" class="errorMessage">Email is required.</p>
    </div> -->
    <button type="submit" :disabled="$v.$invalid">Submit</button>
    
        <BaseSelect>Select the item</BaseSelect>
        <BaseButtonStyle>StylishButton</BaseButtonStyle>
        <br />
        <br />
        </form>
    </div>
</template>

<script>

    export default {
        name:"MyForm",
        // setup: () => ({ v$: useVuelidate() }),
        data() {
            return {
                email: 'null'
            }
        },
        validations: {
            
                email: {
                    required,
                    email
                }
            
        },
        methods: {
          
            },
            submit() {
        this.$v.$touch()
        if (!this.$v.$invalid) {
          console.log('Form Submission:', this.email)
        }
      }
}
        
        

        
    
</script>

<style scoped>
.header-margin {
    padding-top: 350px;
}
</style>

<!-- <p v-if="!$v.email.required">Email is required</p>
        <p v-else-if="!$v.email.email">Invalid email format</p> -->