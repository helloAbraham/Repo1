<template>
  <div class="game-container">
    <!-- make canvas responsive and landscape-oriented -->
    <canvas ref="gameCanvas" width="1200" height="600"></canvas>
  </div>
</template>

<script>
export default {
  name: "FighterJet",
  data() {
    return {
      player: {
        // wider, flatter rectangle to represent a horizontal fighter jet
        x: 575,              // initial horizontal (will be centered for 1200w canvas)
        y: 520,              // near bottom
        width: 100,          // make jet wider than tall so it looks horizontal
        height: 32,
        speed: 7,            // a bit faster for horizontal movement
      },
      bullets: [],
      enemies: [],
      gameInterval: null,
      enemySpeed: 2,
      bulletSpeed: 9,
      isGameOver: false,
    };
  },
  methods: {
    startGame() {
      if (this.gameInterval) clearInterval(this.gameInterval);
      this.gameInterval = setInterval(this.gameLoop, 1000 / 60);
      this.spawnEnemy();
    },
    stopGame() {
      clearInterval(this.gameInterval);
      this.gameInterval = null;
      this.isGameOver = true;
    },
    gameLoop() {
      const canvas = this.$refs.gameCanvas;
      const ctx = canvas.getContext("2d");

      if (this.isGameOver) return;

      // Clear the canvas for each frame
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      // Draw the player jet (horizontal)
      this.drawPlayer(ctx);

      // Move bullets and draw them
      this.moveBullets(ctx);

      // Move enemies and draw them
      this.moveEnemies(ctx);

      // Check for collisions between bullets and enemies
      this.checkCollisions();

      // Spawn new enemies at intervals
      if (Math.random() < 0.02) {
        this.spawnEnemy();
      }
    },
    drawPlayer(ctx) {
      // draw a horizontal fighter shape (body + wings + nose)
      const p = this.player;
      ctx.save();
      ctx.fillStyle = "#0b53d4";
      // body
      ctx.fillRect(p.x, p.y, p.width, p.height);
      // nose (triangular) pointing to the right
      ctx.beginPath();
      ctx.moveTo(p.x + p.width, p.y + p.height / 2);
      ctx.lineTo(p.x + p.width + 18, p.y + p.height * 0.2);
      ctx.lineTo(p.x + p.width + 18, p.y + p.height * 0.8);
      ctx.closePath();
      ctx.fill();
      // wings (top and bottom small trapezoids)
      ctx.fillStyle = "#0b6ff0";
      ctx.fillRect(p.x + 14, p.y - 8, p.width * 0.35, 8); // top wing
      ctx.fillRect(p.x + 14, p.y + p.height, p.width * 0.35, 8); // bottom wing
      ctx.restore();
    },
    movePlayer(direction) {
      const canvas = this.$refs.gameCanvas;
      const maxX = canvas.width - this.player.width - 18; // leave room for nose
      const maxY = canvas.height - this.player.height;
      if (direction === "left") {
        this.player.x = Math.max(0, this.player.x - this.player.speed);
      } else if (direction === "right") {
        this.player.x = Math.min(maxX, this.player.x + this.player.speed);
      } else if (direction === "up") {
        this.player.y = Math.max(0, this.player.y - this.player.speed);
      } else if (direction === "down") {
        this.player.y = Math.min(maxY, this.player.y + this.player.speed);
      }
    },
    shootBullet() {
      // bullets come from the nose (right side)
      this.bullets.push({
        x: this.player.x + this.player.width + 16,
        y: this.player.y + this.player.height / 2 - 4,
        width: 12,
        height: 8,
      });
    },
    moveBullets(ctx) {
      for (let i = 0; i < this.bullets.length; i++) {
        const bullet = this.bullets[i];
        bullet.x += this.bulletSpeed; // bullets move to the right for horizontal game

        // Draw the bullet
        ctx.fillStyle = "orange";
        ctx.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);

        // Remove bullet if it's off-screen
        if (bullet.x > this.$refs.gameCanvas.width) {
          this.bullets.splice(i, 1);
          i--;
        }
      }
    },
    spawnEnemy() {
      // enemies come from the right and move left (horizontal gameplay)
      const yPos = Math.random() * (600 - 50); // random vertical position inside canvas
      this.enemies.push({
        x: 900 + Math.random() * 300, // spawn off-screen to the right (for 1200 width)
        y: yPos,
        width: 50,
        height: 40,
      });
    },
    moveEnemies(ctx) {
      for (let i = 0; i < this.enemies.length; i++) {
        const enemy = this.enemies[i];
        enemy.x -= this.enemySpeed; // move enemies leftwards

        // Draw the enemy as a horizontal green jet (simple)
        ctx.save();
        ctx.fillStyle = "darkgreen";
        ctx.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        // enemy nose pointing left
        ctx.beginPath();
        ctx.moveTo(enemy.x, enemy.y + enemy.height / 2);
        ctx.lineTo(enemy.x - 14, enemy.y + enemy.height * 0.2);
        ctx.lineTo(enemy.x - 14, enemy.y + enemy.height * 0.8);
        ctx.closePath();
        ctx.fill();
        ctx.restore();

        // Remove enemy if it goes off-screen to the left
        if (enemy.x + enemy.width < 0) {
          this.enemies.splice(i, 1);
          i--;
        }
      }
    },
    checkCollisions() {
      // Check for collisions between bullets and enemies
      for (let i = 0; i < this.bullets.length; i++) {
        for (let j = 0; j < this.enemies.length; j++) {
          const bullet = this.bullets[i];
          const enemy = this.enemies[j];

          if (
            bullet.x < enemy.x + enemy.width &&
            bullet.x + bullet.width > enemy.x &&
            bullet.y < enemy.y + enemy.height &&
            bullet.y + bullet.height > enemy.y
          ) {
            // Collision detected, remove bullet and enemy
            this.bullets.splice(i, 1);
            this.enemies.splice(j, 1);
            i--;
            break;
          }
        }
      }
    },
    handleKeyDown(event) {
      if (event.key === "ArrowLeft") this.movePlayer("left");
      if (event.key === "ArrowRight") this.movePlayer("right");
      if (event.key === "ArrowUp") this.movePlayer("up");
      if (event.key === "ArrowDown") this.movePlayer("down");
      if (event.code === "Space" && !this.isGameOver) this.shootBullet();  // Spacebar to shoot
    },
  },
  mounted() {
    window.addEventListener("keydown", this.handleKeyDown);
    this.startGame();
    // make canvas fit parent width on mount (keeps landscape feel on small screens)
    const canvas = this.$refs.gameCanvas;
    const parent = canvas.parentElement;
    if (parent && parent.clientWidth < canvas.width) {
      const ratio = canvas.height / canvas.width;
      canvas.style.width = parent.clientWidth + "px";
      canvas.style.height = Math.floor(parent.clientWidth * ratio) + "px";
    }
  },
  beforeUnmount() {
    window.removeEventListener("keydown", this.handleKeyDown);
    if (this.gameInterval) clearInterval(this.gameInterval);
  },
};
</script>

<style scoped>
.game-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #071428; /* darker sky */
  padding: 8px;
  box-sizing: border-box;
}

/* keep canvas landscape and responsive */
canvas {
  border: 2px solid black;
  max-width: 100%;
  height: auto;
  aspect-ratio: 1200 / 600;
  background: linear-gradient(#6fb8ff, #0b3b66);
  border-radius: 6px;
  box-shadow: 0 6px 18px rgba(0,0,0,0.4);
}
</style>
