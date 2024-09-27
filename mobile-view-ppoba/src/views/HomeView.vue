<template>
  <div class="headerSize">
      <MarqueeStyle/> <br/>
      <h1>Patriots of Bangladesh, USA</h1>
       
  </div>
  
  <section class="innerBoxSize">
    <PhotoSlideShow />
    
    <br>
  </section>



  
  <div class="container">
   
   <div class="column"><h2><b>Recent events:</b></h2>
     <UpcomingList />
     <br/>
     <br/>
     <br/>
     <br/>
     <br/>
     <br/>
     <div>
       <h1><strong>Details Links:</strong></h1>
       <br/>
       <EventListPrevious/>
       <br/>
       <br/>
       <ButtonLink url="">
         Protest one 
       </ButtonLink>
       <br/>
       <br/>
       <br/>

     

     </div>
     

    </div>
      <div class="column-right">
          <EventList/>

      </div>
</div>
<div>
  <CardCarousel />
</div>
  
  <section class="hero">
      <div class="hero-text container">
        <HelloWorld slogan="Islamic Center in Elmhurst" />
     
        <h4></h4>
        
        <hr />
        <h2 class="h2">
         Patriots of Bangladesh, USA
        </h2>
        <fieldset>
        <legend class="legend-size">Patriots of Bangladesh, USA</legend>
        <p class="h4">
          is a non-profit, non-political organization. We always stand for humanity. That is our goal.<br>
          Our helping hand always for Bangladesh. This organization, all members are Bangladeshi American and living in USA.<br>
          Though we apart from 13,000 miles we are always want to be closed to Bangladeshi people. A beautiful development democratic country in the  earth.
          <br> Well known over the world "A nation died for Mother language", U.N declared 'international mother language day on February 21st'
          They just need little help to move forward developing the country's GDP and all Sectors. <a href="https://en.wikipedia.org/wiki/Bangladesh">read more </a>

        </p>
      </fieldset>
        <hr />
      </div>
      
    </section>

  <div class="sizeDown">

  </div>
</template>

<script>
import CardCarousel from '@/components/CardCarousel.vue';
import EventList from '../views/EventList.vue';
import EventListPrevious from '../views/EventListPrevious.vue';
import MarqueeStyle from '@/components/MarqueeStyle.vue';
import PhotoSlideShow from '@/components/PhotoSlideShow.vue';
  export default {
      name: 'PrayerTimes',
      components: {
          EventList,
          EventListPrevious,
          MarqueeStyle,
          PhotoSlideShow,
          CardCarousel,
      },
      data() {
          return {

          }
      },

  }
</script>

<style scoped>
.hero {
  
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
      background-image: url('../assets/currentImage/widthBDimage.jpg'); 
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
    min-height: 10vh; /* Ensure the container takes up the full height of the viewport */
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
   
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* Three columns with equal width */
    gap: 1px; /* Spacing between columns */
    display: flex;
    flex-wrap: wrap;
    margin-bottom: -50px;
}
.headerSize {
  padding-top: 175px;
}
.sizeDown {
  padding-top: 625px;
}

h1 { 
        color: #34cf05; 
        text-align: center;
        background-color: red;
        text-shadow: -2px 2px 4px 
            rgba(0, 0, 0, 0.5),  
            2px -2px 0 
            rgba(255, 255, 255, 0.9); 
    } 
.innerBoxSize {
  width: 425px;
  max-width: 425px;
  min-width: 350px;
  margin-top: .5%;
  margin-left: auto;
  margin-right: auto;

}
.column {
    /* Styles for each column */
    margin-left: auto;
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #3206d1;
    margin-bottom: 60px;
    height: 1200px;
    
}

.column-right {
    /* Styles for each column */
    margin-left: auto;
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #3206d1;
    margin-bottom: 60px;
    height: 1200px;
    
}
</style>
