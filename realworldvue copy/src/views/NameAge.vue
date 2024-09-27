<template>
   <div>
      <form @submit.prevent="submitForm" autocomplete="off">
        <BaseInput 
        v-model="form.name" 
        label="Name" 
        type="text" 
        />  

        <BaseInput 
        v-modelr="form.email" 
        label="Email" 
        type="email"
        />
    </form>
    </div>
</template>

<script>
import { email, required } from '@vuelidate/validators'
import { useVuelidate } from '@vuelidate/core';
    export default {
        name:"NameAge",
        setup() {
            return {
                v$: useVuelidate()
            }
        },
         data() {
      return {
        form: {
          name: "",
          email: ""
          },
      };
    },
    validations () {
        return {
            form: {
                name: { 
                    required 
                },
                email: { 
                    required, 
                    email 
                }
            }
        }

    }

}
</script>

<style scoped>

</style>