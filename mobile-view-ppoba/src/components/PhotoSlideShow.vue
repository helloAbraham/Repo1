<template>

    

    <div class="carousel-container">

  <button @click="scrollLeft" class="carousel-button left">‹</button>

  <div class="carousel-wrapper">
    <div class="carousel-inner">
      <div
        v-for="card in visibleCards"
        :key="card.id"
        class="card"
      >
        <a :href="card.link" class="card-link" target="_blank">
          <img
            :src="card.image"
            :alt="card.title"
            class="card-image"
          />
          <div class="card-content">
            <!-- <p>{{ card.content }}</p> -->
          </div>
        </a>
      </div>
    </div>
  </div>

  <button @click="scrollRight" class="carousel-button right">›</button>

</div>

</template>

<script>
    export default {
        name: "PhotoSlideShow",
        data() {
            return {
                cards: Array.from({ length: 6 }, (_, i) => ({
                   id: i + 1,
                  title: `Card ${i + 1}`,
                  content: `Slide Show ${i + 1}`,
                  image: `https://www.abrahamparker.com/PhotoSlideShow/gallary${i + 1}.png`,
                  link: `https://www.abrahamparker.com/PhotoSlideShow/gallary${i + 1}.png`,
                })),
                 currentIndex: 0,
                 cardsToShow: 3,
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
            },
            visibleCards() {
      return this.cards.slice(
        this.currentIndex,
        this.currentIndex + this.cardsToShow
      );
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
            },

            scrollRight() {
      if (this.currentIndex + this.cardsToShow < this.cards.length) {
        this.currentIndex++;
      }

        },
            scrollLeft() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      }
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

.carousel-container {
  position: relative;
  width: 100%;
}


.carousel-wrapper {
  overflow: hidden;
  width: 100%;
}

.carousel-inner {
  display: flex;
  gap: 20px;
}

.card {
    width: 375px;
    height: 300px;
  flex: 0 0 auto;          /* prevents shrinking */
  background: #111827;
  border-radius: 14px;
  overflow: hidden;
  position: relative;
  transition: transform 0.3s ease;
}

.card:hover {
  transform: scale(1.05);
}

.card-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card-content {
  padding: 10px;
  text-align: center;
  color: #fff;
}

.carousel-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background: #7c3aed;
  color: #fff;
  border: none;
  padding: 6px 20px;
  font-size: 32px;
  border-radius: 50%;
  cursor: pointer;
  z-index: 10;
}

.carousel-button.left {
  left: -58px;   /* push outside the arrow button*/
}

.carousel-button.right {
  right: -50px;  /* push outside the arrow button*/
}


.carousel-button:hover {
  background: #e404b7;
}

</style>