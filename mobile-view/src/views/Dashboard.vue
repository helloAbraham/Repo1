<template>
    <!-- Here planning to have card design each card will have summer then clicking will take the next page for details-->
   
    <section class="topPadding">
      <div class="hero-text container">
        <HelloWorld slogan="Islamic Center in Elmhurst" />
     
        <h4> </h4>
       <img :src="imageSrc" alt="Imam_Image" class="photoSize">
        <hr />
        <h2 class="h2">
          Imam 
        </h2>
        
        <fieldset>
        <legend class="fontStyle">Moulana Md Shaiful Islam</legend>
        <p class="fontStyle">
        Md Shaiful Islam is highly educated in religious studies, divinity, and theology. He has been Imam Khatib for a long time. 
        He started his career as an Imam at an early age. He has more than 15 years of experience as an Imam, Counselor, and teacher/lecturer.
        He is not only experienced as Imam, also has 10 years of work as an administrator. His American career as Imam began 2006 
        at Al Osman and Hamid Jame Masjid Sunnyside New York at the same time he was a teacher in Al-Amin Masjid and Islamic Center Astoria.
        Soon after in the year 2008, he joined as Imam, Khatib, and administrator in charge of Islamic Education at Masjid Al-Taufiq till 2017. 
        He rejoined as Imam Masjid Al-Taufiq 2023.
        He graduated with M.A. in Arabic Literature, from the Madrasa Education Board, Bangladesh.
        He graduated with a Master's in Islamic Studies from Nation University of Bangladesh. Bachelor’s in arts from Feni Govt College.
        Languages can speak Bengali, English, Arabic, and Urdu.</p>
      </fieldset>
        <hr />
      </div>
      
    </section>
    <!-- <div>
        <ProfileOne :profile="userProfile" />
    </div> -->
    <div class="background-container"> </div>
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
</style>