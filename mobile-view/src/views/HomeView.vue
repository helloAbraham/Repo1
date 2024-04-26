<template>
  <div class="background-image-top">
  </div>

  <div class="container">
   
        <div class="column"><h2><b>Upcoming events:</b></h2>
          <UpcomingList />
          <br/>
          <br/>
          <br/>
          <br/>
          <br/>
          <br/>
          <div>
            <h1><strong>Useful Links:</strong></h1>
            <br/>
            <br/>
            <br/>
            <ButtonLink url="https://qiblafinder.withgoogle.com/intl/en/finder/ar">
              Qibla finder
            </ButtonLink>
            <br/>
            <br/>
            <br/>

            <ButtonLink url="https://archive.org/details/bangla-quran-nurani-pronunciation-and-translation/">
              Bangla-Quran
            </ButtonLink>
            <br/>
            <br/>
            <br/>
            <ButtonLink url="https://quran.com/">
              English-Quran
            </ButtonLink>
            <br/>
            <br/>
            <br/>
            <ButtonLink url="https://quran.com/">
              Whats-App_Group
            </ButtonLink>
            <br/>
            <br/>
            <br/>
            <ButtonLink url="https://abrahamparker.com">
              Developer
            </ButtonLink>

          </div>

    </div>
    
        <div class="column">
          <MarqueeStyle/> <br/>
          <EventList/>
          <MarqueeStyleJuma/>
          <JumaList/>
        </div>
        <div class="column"><h2><strong>Donate:</strong></h2> 
          Donate for the Mosque <br>
          Zelle Payment Accepted for
          <br/>
         
          <br/>
          <div class="background-container">
           
          </div> <h3 class="h3">masjidalt4102@gmail.com</h3>
        </div> 
       
    </div>
    <div>
    <PhotoSlideShow />
    </div>
  <div class="home">
    <div class="hero-text container">
    
    </div>
    <div>
      <PhotoGallery :photos="galleryPhotos" />
    </div>
    <section class="hero">
      <div class="hero-text container">
        <HelloWorld slogan="Islamic Center in Elmhurst" />
     
        <h4> </h4>
        
        <hr />
        <h2 class="h2">
          MASJID AL-TAUFIQ
        </h2>
        <fieldset>
        <legend class="legend-size">Welcome to Masjid Al-Taufiq</legend>
        <p class="h4">
        The goal of the Masjid Al-Taufiq is to provide convenient places of worship, islamic education and dawah. 
        Masjid Al-Taufiq Islamic education where children, local and from afar, can learn and memorize the Quran 
        and become scholars of deen. Masjid Al-Taufiq also performs community services such as marriage ceremonies, 
        religious prayers for the deceased, and funeral services help. 
        The association is also committed to do dawah work and help the dawah groups that travel for this noble effort. 
        The association also conducts various halaqas/programs in the masjid and other educational programs and 
        community get togethers during Ramadan and throughout the year.</p>
      </fieldset>
        <hr />
      </div>
      
    </section>
    
   

    <div>
    <InputTime />
  </div>
   
  </div>
 
</template>

<script>
// @ is an alias to /src
import CurrentDate from '@/components/CurrentDate.vue';
import HelloWorld from '../components/HelloWorld.vue';
import MarqueeStyle from '../components/MarqueeStyle.vue';
import EventList from './EventList.vue';
import JumaList from '../views/JumaList.vue';
import MarqueeStyleJuma from '../components/MarqueeStyleJuma.vue';
import UpcomingList from '../views/UpcomingList.vue';
import PhotoGallery from '../components/PhotoGallery.vue';
import PhotoSlideShow from '@/components/PhotoSlideShow.vue';


export default {
  name: 'Home',
  data() {
    return {
      currentDate: '',
      marqueeText: '',
    }
  },
  mounted() {
      // Update currentDate with the current date when the component is mounted
      this.updateCurrentDate();
    },
  components: {
    HelloWorld,
    CurrentDate,
    MarqueeStyle,
    EventList,
    MarqueeStyleJuma,
    JumaList,
    UpcomingList,
    PhotoGallery,
    PhotoSlideShow,

  },

  computed: {
    currentImage(){
      return this.images[this.currentIndex];
    }
  },

  methods: {
    updateCurrentDate() {
        // Get the current date
        const today = new Date();
        // Format the date as desired (e.g., 'March 7, 2024')
        const formattedDate = today.toLocaleDateString('en-US', { 
          weekday: 'long', 
          year: 'numeric', 
          month: 'long', 
          day: 'numeric' 
        });
        // Update currentDate with the formatted date
        this.currentDate = formattedDate;
      }
  }
}
</script>

<style lang="scss" scoped>
.hero {
  // background-image: url("../assets/logo.png");
  background-attachment: fixed;
  position: relative;
  height: 100vh;

  img {
    object-fit: cover;
    height: 100%;
    width: 100%;
  }

  .hero-text {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    color: #0b0c0c;

    h4 {
      text-transform: uppercase;
      font-size: 22px;
      padding-bottom: 4px;
    }

    .h2 {
      font-family: 'Times New Roman', Times, serif;
      font-size: 35px;
      color: white;
      text-shadow: 3px 5px 4px rgb(25, 222, 85), 0 0 35px blue, 0 0 10px blue;
      background-image: url('../assets/nameImage.jpeg'); 
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 20vh; /* Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */


    }

    hr:nth-child(2) {
      max-width: 365px;
      margin-bottom: 16px;
    }
    hr:nth-child(4) {
      height: 6px;
      background-color: #fff;
      border: none;
      max-width: 85px;
      margin-top: 16px;
    }
    
  }
}
.container {
    margin-top: -10px;
    padding-top: 20px;
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* Three columns with equal width */
    gap: 1px; /* Spacing between columns */
    display: flex;
    flex-wrap: wrap;
}
.column {
    /* Styles for each column */
  
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #3206d1;
    margin-bottom: 200px;
    height: 960px;
    
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
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
.h3 {
  padding-top:10px;
}
.background-image-top {
  padding-top: 350px;
  padding-bottom: 0px;
  background-image: url('../assets/lailaha.png'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
     /*min-height: 100%; Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
    display: flex;
    flex-wrap: wrap;
    position:sticky;
    background-repeat: no-repeat;
    background-size: 300px 75px;
    margin-bottom: 5px;
}
.h4 {
  font-size: 20px;
}
.legend-size {
  font-size: 25px;
}
.slides {
 padding-top: -210px;
}

</style>