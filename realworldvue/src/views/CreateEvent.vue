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
      <!-- here BaseInput use just single use each time input for example title, then description-->
      <BaseInput v-model="myEvent.title" label="Title" type="text" />
      <BaseInput v-model="myEvent.description" label="Description" type="text" />  
      
      <h3>Where is your event?</h3>
      <BaseInput v-model="myEvent.location" label="Location" type="text" />
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
      <button type="submit">Submit</button>
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
  name: "CreateEvent",
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
        category: "",
        title: "",
        description: null,
        location: "",
        pets: 1,
        extras: {
          catering: false,
          music: false,
        },
      },
    };
  },
  methods: {
    submitForm() {
      if(this.formIsValid){
        console.log('Form Submitted', this.form)
      } else {
        console.log('X Invalid form')
      }
      
    }
  },
   //Same state can access in multiple places in component
   computed: 
    // userName() {
    //   return this.$store.state.user.name;
    // }
    //can be done different way below
    mapState({
      userName: state=> state.user.name,
      userId: state=> state.user.id,
      //categories: state=> state.categories
      //Above line top level statement, we can use as string
      categories: 'categories'
    }),
   
  
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
.background-image-top {
  background-image: url('../assets/programcomputer.gif'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
     /*min-height: 100%; Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
    display: flex;
    flex-wrap: wrap;
    position:sticky;
    background-repeat: no-repeat;
 
  }
</style>