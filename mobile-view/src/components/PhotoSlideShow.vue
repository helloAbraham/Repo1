<template>

    <div class="slideshow">
        <TransitionGroup name="fade" tag="div">
            <img :key="currentIndex" :src="currentImages" class="centered-image" alt="Slideshow">
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
    
    
}
.centered-image {
  max-width: 100%; /* Ensure images don't exceed container width */
  max-height: 100%; /* Ensure images don't exceed container height */
}

.slide {
    width: 100%;
    height:100%;
    object-fit: cover; /* Ensure the image covers the entire container */
    justify-content: center;
    align-items: center;
}
.fade-enter, .fade-leave-to {
    opacity: 0;
}
</style>