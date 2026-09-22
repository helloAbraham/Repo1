<template>
    <div class="topPadding">
    <div class="container">
        <!---First div with a unique color -->
        <div class="color-box-1" :style="{ backgroundColor: color1 }">
            Group of question 1 here 
            <pre>
            1. Question one <BaseInput />
            1. I like to submit the question?
            3. Question threesadadsadsadadsa sadadadda asdadada dasdsad sadadasd.?
            </pre>
            <BaseButton>MyButton</BaseButton>
        </div>

       
    </div>
    <div class="container">
     <!---Second div with a unique color -->
     <div class="color-box-1" :style="{ backgroundColor: color2 }">
        <pre>
            Group of question 2 here
            1. Question one <input type="radio">
            2. Question two
            Question threesadadsadsadadsa sadadadda asdadada dasdsad sadadasd
            Question threesadadsadsadadsa sadadadda asdadada dasdsad sadadasd
            Question threesadadsadsadadsa sadadadda asdadada dasdsad sadadasd
            Question threesadadsadsadadsa sadadadda asdadada dasdsad sadadasd
            <BaseSelect>Select: </BaseSelect>
        </pre>
        </div>
    </div>

    <div class="container">
     <!---Third div with a unique color -->
    <div class="color-box-1" :style="{ backgroundColor: color3 }">
            Group of question 3 here
            {{ color1 }}
            <pre>
            1. Question one 
            2. Question two
            3. Question three
            <input type="checkbox">
            1. Question one 
            2. Question two
            3. Question three
            1. Question one 
            2. Question two
            3. Question three  
            1. Question one 
            2. Question two
            3. Question three
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit illum voluptatum reprehenderit dolorem perferendis provident eveniet iure quibusdam. Accusantium iusto praesentium eius laboriosam pariatur facilis tenetur alias atque veniam architecto.
            </pre>
        </div>
    </div>
    
</div>
    
</template>

<script>
import BaseButton from './BaseButton.vue';
import BaseInput from './BaseInput.vue';
import BaseSelect from './BaseSelect.vue';

    export default {
    name: "SurveyQues",

    data() {
        return {
            color1: '#FAD02E', //Example color 1
            color2: '#4CAF50', //Example color 2
            color3: '#2196F3', //Example color 3
            
        }
    },

    components: {
        BaseButton,
        BaseSelect,
    }
        
    }
</script>

<style scoped>
.topPadding {
    padding-top: 200px;
    padding-bottom: 100px;
  }
.container {
    display: flex;
    justify-content: space-around;
    margin-top: 20px;
}

.color-box-1{
  padding: 20px;
  border-radius: 8px;
  color: white;
  font-size: 18px;
  text-align: left;
  width: 850px; /* Adjust width as needed */
  height: 200px; /* Adjust height as needed */
}
.color-box-2{
  padding: 20px;
  border-radius: 8px;
  color: white;
  font-size: 18px;
  text-align:left;
  width: 650px; /* Adjust width as needed */
  height: 200px; /* Adjust height as needed */
}
.color-box-3{
  padding: 20px;
  border-radius: 8px;
  color: white;
  font-size: 18px;
  text-align: left;
  width: 650px; /* Adjust width as needed */
  height: 200px; /* Adjust height as needed */
}
</style>