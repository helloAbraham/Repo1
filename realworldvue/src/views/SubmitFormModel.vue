<template>
    <div>
      <form @submit.prevent="submitForm" autocomplete="off">
        <h1>Create an Event, {{ userName }}</h1>
        <p>This event was created by {{ userName }}</p>
        <BaseSelect
          :options="categories"
          v-model="myEvent.category"
          label="Select a category"
        />
        <h3>Name & describe the event</h3>
        <BaseInput 
          v-model="myEvent.date"
          type="date"
          :format="'MM/DD/YYYY'"
          >
          </BaseInput>
        <!-- here BaseInput use just single use each time input for example title, then description-->
        <BaseInput v-model="myEvent.title" label="Title" type="text" />
        <BaseInput 
        v-model.number="myEvent.year" 
        label="Year" 
        type="number" 
        oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
        maxlength="4"
        required
        />  
        <p class="sloteColor" v-if="!yearIsValid" >Input 4 digits year</p>

        <h3>What is your Pay Period?</h3>
        <BaseInput 
        v-model.number="myEvent.payperiod" 
        label="PayPeriod" 
        type="number"
        oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
        maxlength="6"
        required 
        />
        <p class="sloteColor" v-if="!payperiodIsValid" >Input 6 digits pay period</p>
        <h3>Are pets allowed?</h3>
        <div>
          <BaseRadio
            v-model="myEvent.pets"
            :selectedValue="1"
            label="Yes"
            name="pets"
          />
        </div>
  
        <div>
          <BaseRadio
            v-model="myEvent.pets"
            :selectedValue="0"
            label="No"
            name="pets"
          />
        </div>
        <h3>Extras if any ...</h3>
        <div>
          <BaseCheckbox v-model="myEvent.extras.catering" label="Catering" />
          <BaseCheckbox v-model="myEvent.extras.music" label="Live Music" />
        </div>
        <button type="submit" :disabled="!formIsValid">Submit</button>
      </form>
    </div>
  </template>
  
  <script>
  import BaseInput from "@/components/BaseInput.vue";
  import BaseSelect from "@/components/BaseSelect.vue";
  import BaseCheckbox from "@/components/BaseCheckbox.vue";
  import BaseRadio from "@/components/BaseRadio.vue";
  import { mapState } from 'vuex';
  export default {
    name: "SubmitFormModel",
    data() {
      return {
        categories: [
          "sustainbility",
          "nature",
          "animal welfare",
          "housing",
          "eduaction",
          "food",
          "community",
        ],
        myEvent: {
          date:"",
          category: "",
          title: "",
          year: "",
          payperiod: "",
          pets: 1,
          extras: {
            catering: false,
            music: false,
          },
        },
      };
    },
 
     //Same state can access in multiple places in component
     computed: {
      // userName() {
      //   return this.$store.state.user.name;
      // }
      //can be done different way below
      // mapState({
      //   userName: state=> state.user.name,
      //   userId: state=> state.user.id,
      //   //categories: state=> state.categories
      //   //Above line top level statement, we can use as string
      //   categories: 'categories'
      // }),
      yearIsValid(){
        return typeof this.myEvent.year === 'number' && this.myEvent.year.toString().length ===4;
      },
      payperiodIsValid(){
        return typeof this.myEvent.payperiod === 'number' && this.myEvent.payperiod.toString().length ===6; 
      },
      formIsValid(){
        return this.yearIsValid  && this.payperiodIsValid;
      }
    },

    methods: {
      submitForm() {
        if(this.formIsValid){
          console.log('Form Submitted', this.form)
          alert("The form has bees submitted successfully.")
          this.myEvent.title="";
          this.myEvent.year="";
          this.myEvent.payperiod="";
          this.categories=[];
        } else {
          console.log('X Invalid form')
        }
        
      }
    },
      /**
        Also we can do change here: and render template section like that { user.name }
        mapState({
        user: 'user',
                      //We do not need for id but we can render id like that { user.id} 
                      //it works because we assign here user object, and we can render from that object ... 
        categories: 'categories'
        //template section we can render:
        // <ul>
            <li v-for="category in categories" :key="category"> 
              {{ category }}</li>
            </ul>
      }),
       */
  
       /**
          also mapState more simplicity we can use an array without name properties assign above
          mapState(['user', 'category'])
          //Note rendering template same as before
        */
      
    components: {
      BaseInput,
      BaseSelect,
      BaseCheckbox,
      BaseRadio,
    },
  };
  </script>
  <style scoped>
  .sloteColor{
    color: red;
  }
  </style>
  <!-- Above examples are manual without using vuelidate-->