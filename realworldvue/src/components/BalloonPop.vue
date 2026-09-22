<template>
  <div class="header">
    <h1>Balloon Pop Game</h1>
  </div>
    <div class="game-container">
      <div id="game-area" ref="gameArea">
        <div
        v-for="balloon in balloons"
        :key="balloon.id"
        class="balloon-wrapper"
        :style="balloon.wrapperStyle"
        @click="popBalloon(balloon.id)"
      >
        <div class="balloon" :style="balloon.style"></div>
        <div class="string"></div>
      </div>

      <!-- Using ConfirmModal-->
    <ConfirmModal
      :visible="showConfirm"
      title="🎮 Game Over!"
      message="You missed 10 balloons. Play again?"
      confirmText="Play Again"
      @confirm="playAgain"
      @cancel="endGame"
    />

    <!-- Congratulations Modal -->
    <ConfirmModal
      :visible="showCongratsModal"
      title="🎉 Congratulations!"
      message="You reached 100 points!"
      confirmText="Play Again"
      @confirm="playAgain"
      @cancel="endGame"
    />
    
 </div>

    <div class="controls">
       
        <div style="display:flex; justify-content:center; gap:10px; margin-top:10px;">
             
      <BaseButtonStyle @click="startGame" v-if="!gameRunning">▶️ Start</BaseButtonStyle>
      <BaseButtonStyle @click="stopGame" v-if="gameRunning">⏹ Stop</BaseButtonStyle>
      <BaseButtonStyle @click="resetGame">🔄 Reset</BaseButtonStyle><h2>Score: {{ score }}</h2>
        </div>
    </div>
    <h3>A game created by: Abraham Farhad Parker</h3> 
    <!-- Hidden audio element -->
    <audio ref="popSound" src="/sounds/pop.mp3" preload="auto"></audio>
  </div>
</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';
import ConfirmModal from './ConfirmModal.vue';

export default {
  name: "BalloonPop",
  components: { 
    BaseButtonStyle,
    ConfirmModal
 },
  data() {
    return {
      score: 0,
      missed: 0,              // 👈 new tracker
      balloons: [],
      nextId: 1,
      missedBalloons: 0,
      gameRunning: false,
      spawnInterval: null,
      gameOver: false,        // 👈 track if game over
      showConfirm: false,
      showCongratsModal: false, // 👈 new state for congrats
    };
  },
  
  methods: {
    startGame() {
      if (this.gameRunning || this.gameOver) return;
      this.gameRunning = true;
      this.spawnInterval = setInterval(this.createBalloon, 800);
    },
    stopGame() {
      this.gameRunning = false;
      clearInterval(this.spawnInterval);
      this.spawnInterval = null;
    },
    resetGame() {
      this.stopGame();
      this.score = 0;
      this.missed = 0;
      this.balloons = [];
      this.gameOver = false;
    },

    createBalloon() {
      const gameArea = this.$refs.gameArea;
      if (!gameArea) return;

      const id = this.nextId++;
      const left = Math.random() * (gameArea.offsetWidth - 60);
      const size = 40 + Math.random() * 30;
      const color = this.randomColor();
      const duration = 5 + Math.random() * 3;
      const floatDistance = gameArea.offsetHeight + 100;
      const drift = Math.random() * 100 - 50;

      const wrapperStyle = {
        left: `${left}px`,
        animation: `floatUp-${id} ${duration}s linear forwards`,
      };

      const style = {
        width: `${size}px`,
        height: `${size * 1.3}px`,
        background: `radial-gradient(circle at 20px 20px, ${color}, #333)`,
      };

      // custom keyframes per balloon
      const keyframes = `
        @keyframes floatUp-${id} {
          0% { bottom: -120px; transform: translateX(0px); opacity: 1; }
          50% { transform: translateX(${drift}px); }
          100% { bottom: ${floatDistance}px; transform: translateX(0px); opacity: 0; }
        }`;
      const styleSheet = document.createElement("style");
      styleSheet.innerHTML = keyframes;
      document.head.appendChild(styleSheet);

      const balloon = { id, style, wrapperStyle };
      this.balloons.push(balloon);

      // 👇 when animation ends = missed balloon
      setTimeout(() => {
        const stillThere = this.balloons.find(b => b.id === id);
        if (stillThere) {
          this.balloons = this.balloons.filter(b => b.id !== id);
          this.missed++;
          if (this.missed >= 10) {
            this.triggerGameOver();
          }
        }
      }, duration * 1000);
    },

    popBalloon(id) {
      this.playPopSound();
      this.score++;
      this.balloons = this.balloons.filter((b) => b.id !== id);
      // ✅ Check for win condition
      if (this.score >= 99) {
        this.triggerCongrats();
      }
    },

    randomColor() {
      const colors = ["#ff6b6b", "#feca57", "#48dbfb", "#1dd1a1", "#5f27cd"];
      return colors[Math.floor(Math.random() * colors.length)];
    },

    playPopSound() {
      const sound = this.$refs.popSound;
      if (sound) {
        sound.currentTime = 0;
        sound.play();
      }
    },

    triggerGameOver() {
      this.stopGame();
      this.gameOver = true;
      this.showConfirm = true;
    },
    triggerCongrats() {
      this.stopGame();
      this.showCongratsModal = true;
    },
    playAgain() {
      this.showConfirm = false;
      this.resetGame();
      this.startGame();
      this.showCongratsModal = false;
    },
    endGame() {
      this.showConfirm = false;
      this.resetGame();
      this.showCongratsModal = false;
    },
  },
  beforeDestroy() {
    clearInterval(this.spawnInterval);
  },
};
</script>


<style scoped>
.game-container {
  text-align: center;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

#game-area {
position: relative;
  width: 375px;                /* iPhone 16 portrait width */
  height: 585px;   
  margin: 20px auto;
  border: 2px solid #333;
  border-radius: 8px;
  background: linear-gradient(to top, #a6e3ff, #ffffff);
  overflow: hidden;
}

/* wrapper for balloon + string */
.balloon-wrapper {
  position: absolute;
  bottom: -120px;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}

/* actual balloon */
.balloon {
  border-radius: 50%;
  transition: transform 0.2s;
  box-shadow: inset -5px -10px rgba(0, 0, 0, 0.2);
}

.balloon:hover {
  transform: scale(1.2);
}

/* string below the balloon */
.string {
  width: 2px;
  height: 135px;
  background-color: #555;
  margin-top: -5px;
   transform-origin: top;
  animation: stringWobble 1.5s ease-in-out infinite alternate;
}

.controls {
  margin-top: 20px;
}

.controls button {
  margin: 0 10px;
  padding: 10px 15px;
  font-size: 1em;
  cursor: pointer;
  border-radius: 6px;
  border: 1px solid #444;
  background-color: #f1f1f1;
}

.controls button:hover {
  background-color: #d9d9d9;
}
#game-area {
  position: relative;
  width: 600px;
  height: 500px;
  margin: 20px auto;
  border: 2px solid #333;
  border-radius: 8px;

  /* ✅ Add background image here */
  background-image: url("../assets/sky.jpg"); 
  background-size: cover;          /* fills the whole area */
  background-position: center;     /* keeps it centered */
  background-repeat: no-repeat;

  overflow: hidden;
}

.header {
  text-align: center;       /* center all text inside the container */
  margin-top: 20px;         /* optional spacing from top */
}

.header h1 {
  font-size: 2em;
  margin-bottom: 5px;
}

.header h2 {
  font-size: 1.3em;
  margin-top: 0;
}

</style>
