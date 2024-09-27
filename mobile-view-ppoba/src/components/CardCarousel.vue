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
              <img :src="card.image" :alt="card.title" class="card-image" />
              <div class="card-content">
                <!-- <h3>{{ card.title }}</h3> -->
                <p>{{ card.content }}</p>
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
    name: 'CardCarousel',
    data() {
      return {
        cards: Array.from({ length: 13 }, (_, i) => ({
          id: i + 1,
          title: `Card ${i + 1}`,
          content: `Protest against dictator`,
          image: `https://ppoba.org/protest_img/protest${i + 1}.png`, // Placeholder image
          link: `https://ppoba.org/protest_img/protest${i + 1}.png`,
        })),
        currentIndex: 0,
        cardsToShow: 4,
      };
    },
    computed: {
      visibleCards() {
        const start = this.currentIndex;
        const end = Math.min(start + this.cardsToShow, this.cards.length);
        return this.cards.slice(start, end);
      },
    },
    methods: {
      scrollLeft() {
        if (this.currentIndex > 0) {
          this.currentIndex--;
        }
      },
      scrollRight() {
        if (this.currentIndex + this.cardsToShow < this.cards.length) {
          this.currentIndex++;
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .carousel-container {
    display: flex;
    align-items: center;
    position: relative;
    overflow: hidden;
    width: 100%;
  }
  
  .carousel-wrapper {
    flex: 1;
    overflow: hidden;
    display: flex;
    justify-content: center;
  }
  
  .carousel-inner {
    display: flex;
    transition: transform 0.3s ease;
  }
  
  .card {
    flex: 0 0 auto;
    width: 200px;
    height: 225px;
    margin: 0 10px;
    position: relative;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    background: #fff;
    text-align: center;
    overflow: hidden;
  }
  
  .card-link {
    text-decoration: none;
    color: inherit;
    display: block;
    height: 100%;
  }
  
  .card-image {
    width: 100%;
    height: auto;
    display: block;
  }
  
  .card-content {
    padding: 10px;
  }
  
  .carousel-button {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background: #000;
    color: #fff;
    border: none;
    padding: 10px;
    cursor: pointer;
    z-index: 1;
  }
  
  .carousel-button.left {
    left: 0;
  }
  
  .carousel-button.right {
    right: 0;
  }
  </style>
  