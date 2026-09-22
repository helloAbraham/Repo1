<template>
   <div class="menu-container">
    <h1 class="title">🎮 Choose a Game</h1>

    <div class="button-group">
      <a href="#/bricks" class="game-button">🧱 Brick Game</a>
      <a href="#/ballonpop" class="game-button">🎈 Balloon Pop</a>
    </div>
  </div>
</template>
<script>
import BricksGame from './BricksGame.vue';
import BalloonPop from '../components/BalloonPop.vue';

    export default {
        name: "GamesPage",
        components: {
            BricksGame,
            BalloonPop
        },
        data() {
            return {
                // Any data properties for the Games page
            };
        },
        methods: {
            // Any methods for the Games page
        },
        
    }
</script>

<style scoped>
.menu-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to bottom, #d0f0ff, #ffffff);
  font-family: "Poppins", sans-serif;
}

.title {
  font-size: 2em;
  color: #333;
  margin-bottom: 40px;
}

.button-group {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.game-button {
  text-decoration: none;
  color: white;
  background: #007bff;
  padding: 15px 40px;
  border-radius: 10px;
  font-size: 1.2em;
  font-weight: 600;
  transition: background 0.3s, transform 0.2s;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.game-button:hover {
  background: #0056b3;
  transform: scale(1.05);
}
</style>