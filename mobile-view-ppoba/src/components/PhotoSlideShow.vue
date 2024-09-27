<template>

    <div class="slideshow">
        <TransitionGroup name="fade" mode="out-in" @before-enter="beforeEnter" @enter="enter" @leave="leave" tag="div">
            <router-view :key="$route.fullPath"/>
            <h1>
            <img v-for="img in images" :src="img" class="hidden" :key="img" />
            <img :key="currentIndex" :src="currentImages" class="centered-image" alt="Slideshow">
            </h1>
            <br/>
            <button @click="prevPhoto" style="border-color: green; border-radius: .5em;">Previous</button>
            <button @click="center" style="border: none;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
            <button @click="nextPhoto" style="border-color: green; border-radius: .5em;">Next</button>
        </TransitionGroup>
        
           

    </div>
</template>

<script>
    export default {
        name: "PhotoSlideShow",
        data() {
            return {
                images: [
                require('@/assets/currentImage/image1.jpg'),
                require('@/assets/currentImage/image2.jpg'),
                require('@/assets/currentImage/image3.jpg'),
                require('@/assets/currentImage/image4.jpg'),
                require('@/assets/currentImage/image5.jpg'),
                ],
                currentIndex: 0,
            }
        },
    
        computed: {
            currentImages() {
                return this.images[this.currentIndex];
            }
        },

        mounted() {
            setInterval(this.nextSlide, 3000); //Change slide every 3 seconds
            // console.log('Data:', this.images);
        },

        methods: {
            nextSlide() {
                this.currentIndex = (this.currentIndex + 1) % this.images.length;
            },
            nextPhoto() {
                this.currentIndex = (this.currentIndex + 1) % this.images.length;
            },
            prevPhoto() {
                this.currentIndex = (this.currentIndex - 1 + this.images.length) % this.images.length;
            }

        }
        
    }
</script>

<style scoped>
.slideshow {
   
    display: flex;
    overflow: hidden;
    justify-content: center;
    align-items: center;
    text-align: center;
    position: relative;
    
}

.slide-container {
  width: 100%;
  height: 500px; /* Adjust as needed */
  overflow: hidden;
  
}
.centered-image {
  max-width: 100%; /* Ensure images don't exceed container width */
  max-height: 100%; /* Ensure images don't exceed container height */
  object-fit: cover;
}

.slide {
    width: 100%;
    height:100%;
    object-fit: cover; /* Ensure the image covers the entire container */
    justify-content: center;
    align-items: center;
    
}
.fade-enter-active, .fade-leave-active {
    transition: opacity 0.5s ease-in-out;
}

.fade-enter, .fade-leave-to {
    opacity: 0;
}
/* Example CSS transition */
img {
  transition: opacity 0.5s ease;
}
.hidden {
    display: none;
}

</style>