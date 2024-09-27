<template>
  
    <section class="topPadding">
      
      <div class="hero-text container">
        <h4> </h4>
       <!-- <img :src="imageSrc" alt="Imam_Image" class="photoSize"> -->
        <hr />
        <h2 class="h2">
          Our goal
        </h2>
        
        <fieldset>
        <legend class="fontStyle">Patriot of Bangladesh</legend>
        <p class="fontStyle">
        All the Bangladeshi people abroad in America are together for Bangladesh. We are going to participate some of the development as voluntry.</p>
      </fieldset>
        <hr />
      </div>
         
    </section>
   
    <!-- <div>
        <ProfileOne :profile="userProfile" />
    </div> -->
   
    <div class="sizeDown">
    </div> 
</template>

<script>
import ProfileOne from '../components/ProfileOne.vue';
    export default {
        name:'Dashboard',

        components: {
            ProfileOne,
        },
        data() {
            return {
              imageURL: '@/assets/currentImage/BD_Map.png',
                imageSrc: require('../assets/Saiful_Imam.jpg'), // Import your image
                userProfile: {
                    name: 'Saifulla Khan',
                    avatar: 'https://via.placeholder.com/100',
                    bio: 'A software developer',
                    details: {
                    Email: 'john@example.com',
                    Location: 'New York'
                    // Additional profile details
                    }
                }
            }
        }
    }
</script>

<style scoped>
.topPadding {
    padding-top: 200px;
}
.fontStyle{
        font-size: 20px;
    }

.background-container {


    background-image: url('../assets/zelle_qr.png'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 29%; /* Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
    display: flex;
    flex-wrap: wrap;
    position:sticky;
    
  }
  .photoSize {
    width: 2in;
    height: auto;
  }

  .sizeDown {
  padding-top: 525px;
}
.my-component {

  opacity: 0.2;
  
  background-image: url('@/assets/currentImage/BD_Map.png');
  background-repeat: no-repeat;
  background-size: 680px 440px;
  background-position: center; /* Adjust as needed */

}
</style>