<template>
  <div class="sizeTop">
     <p>Hello World!!!</p>
     <p>lorem500</p>
     <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>  <p>Hello World!!!</p>
     <p>lorem500</p>
  </div>
 <div class="sizeTop">
  <h1>Hello Aiden</h1>
 </div>
</template>
   
  
  <script>
  export default {
    data() {
      return {
        images: [
        { src: 'src/assets/currentImage/image1.jpg', alt: 'Image 1' },
        { src: 'src/assets/currentImage/image2.jpg', alt: 'Image 2' },
        { src: 'src/assets/currentImage/image3.jpg', alt: 'Image 3' },
          // Add more image paths as needed
        ],
        currentIndex: 0
      };
    },
    computed: {
    currentImageSrc() {
      return this.images[this.currentIndex].src;
    },
    currentImageAlt() {
      return this.images[this.currentIndex].alt;
    }
  },
  created() {
        // Start the slideshow when the component is created
        this.startSlideshow();
  },
    // mounted() {
    //     this.startSlideshow();
    // },
    methods: {
      startSlideshow() {
      // Set interval to change images every 3 seconds (adjust as needed)
      this.slideshowInterval = setInterval(() => {
        this.currentIndex = (this.currentIndex + 1) % this.images.length;
      }, 3000);
    }
    },
    beforeDestroy() {
    // Clear the interval to stop the slideshow when the component is destroyed
    clearInterval(this.slideshowInterval);
  }

  };
  </script>
  
  <style scoped>
  .fade-enter-active, .fade-leave-active {
    transition: opacity 0.5s;
  }
  
  .fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
    opacity: 0;
  }

  .container {
    
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* Three columns with equal width */
    gap: 2px; /* Spacing between columns */
}
.column {
    /* Styles for each column */
    
    margin-top: 200px;
    padding: 20px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    margin-bottom: 200px;
}
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content {
  flex: 1;
}

.footer {
  flex-shrink: 0;
}

/* Add any custom styling for your slideshow */
.slideshow {
  width: 100%;
  height: auto;
}

.slide {
  width: 100%;
  height: auto;
}
.sizeTop {
  padding-top: 200px;
}
  </style>
  