<template>
  <div class="game-container">
    <canvas ref="gameCanvas" width="460" height="340"></canvas>
    <div class="controls">
      <BaseButtonStyle @click="startGame">Start Game</BaseButtonStyle>
      <h1 class="h1">Score: {{ score }}</h1>
     <!-- 🎮 Game Over Modal -->
      <ConfirmModalFlexButton
        :visible="showConfirm"
        title="🎮 Game Over!"
        message="You missed the ball! Play again?"
        confirmText="Play Again"
        @confirm="handleRestart"
        @cancel="handleCancel"
      />

      <!-- 🏆 Win Modal -->
      <ConfirmModalFlexButton
        :visible="showCongratsModal"
        title="🏆 Congratulations!"
        message="You cleared all bricks! You win!"
        confirmText="Play Again"
        @confirm="handleRestart"
        @cancel="handleCancel"
      />

    </div>
  </div>
</template>

<script>
import bgImageFile from '@/assets/myBackPhoto.jpeg'; // <-- put here background image at src/assets/myPhoto.jpeg (or change path)
import BaseButtonStyle from '@/components/BaseButtonStyle.vue';
import hitSound from '@/assets/Brick_impact debris_Sound Effect.mp3'; // add a short hit sound file in src/assets

import ConfirmModalFlexButton from '@/components/ConfirmModalFlexButton.vue';


export default {
  name: 'BricksGame',
  components: {
    BaseButtonStyle,
    ConfirmModalFlexButton,
  },
  data() {
    return {
      hitAudio: null, // Audio object for brick hit
      canvas: null,
      ctx: null,
      ballRadius: 8,
      x: 0,
      y: 0,
      dx: 2,
      dy: -2,
      paddleHeight: 10,
      paddleWidth: 75,
      paddleX: 0,
      rightPressed: false,
      leftPressed: false,
      brickRowCount: 5,
      brickColumnCount: 5,
      brickWidth: 75,
      brickHeight: 20,
      brickPadding: 10,
      brickOffsetTop: 30,
      brickOffsetLeft: 30,
      bricks: [],
      score: 0,
      animationFrame: null,
      // custom additions
      brickColors: ['#e74c3c', '#f1c40f', '#2ecc71', '#3498db', '#9b59b6'], // row colors (will cycle if more rows)
      bgImage: null,
      bgLoaded: false,
      showConfirm: false,
      showCongratsModal: false,
    }
  },
  mounted() {
    this.canvas = this.$refs.gameCanvas
    this.ctx = this.canvas.getContext('2d')
    document.addEventListener('keydown', this.keyDownHandler)
    document.addEventListener('keyup', this.keyUpHandler)
    this.initBricks()
    // load background image (non-blocking)
    this.bgImage = new Image()
    this.bgImage.onload = () => {
      this.bgLoaded = true
    }
    this.bgImage.onerror = () => {
      this.bgLoaded = false
    }
    this.bgImage.src = bgImageFile

    // init hit sound
    try {
      this.hitAudio = new Audio(hitSound)
      this.hitAudio.preload = 'auto'
      this.hitAudio.volume = 0.6
    } catch (e) {
      this.hitAudio = null
    }

    // pointer handlers so paddle follows mouse / touch
    this.canvas.addEventListener('mousemove', this.onPointerMove)
    this.canvas.addEventListener('touchmove', this.onTouchMove, { passive: false })
    // prevent default touch actions while interacting the canvas
    this.canvas.style.touchAction = 'none'
  },
  beforeUnmount() {
    document.removeEventListener('keydown', this.keyDownHandler)
    document.removeEventListener('keyup', this.keyUpHandler)
    cancelAnimationFrame(this.animationFrame)
    if (this.canvas) {
      this.canvas.removeEventListener('mousemove', this.onPointerMove)
      this.canvas.removeEventListener('touchmove', this.onTouchMove)
    }
  },
  methods: {
    initBricks() {
      this.bricks = []
      for (let c = 0; c < this.brickColumnCount; c++) {
        this.bricks[c] = []
        for (let r = 0; r < this.brickRowCount; r++) {
          this.bricks[c][r] = { x: 0, y: 0, status: 1 }
        }
      }
    },
    drawBricks() {
      for (let c = 0; c < this.brickColumnCount; c++) {
        for (let r = 0; r < this.brickRowCount; r++) {
          const b = this.bricks[c][r]
          if (b.status === 1) {
            const brickX = c * (this.brickWidth + this.brickPadding) + this.brickOffsetLeft
            const brickY = r * (this.brickHeight + this.brickPadding) + this.brickOffsetTop
            b.x = brickX
            b.y = brickY
            this.ctx.beginPath()
            this.ctx.rect(brickX, brickY, this.brickWidth, this.brickHeight)
            // choose color based on row (r). If not enough colors, cycle.
            const color = this.brickColors[r % this.brickColors.length]
            this.ctx.fillStyle = color
            this.ctx.fill()
            this.ctx.closePath()
          }
        }
      }
    },
    drawBall() {
      this.ctx.beginPath()
      this.ctx.arc(this.x, this.y, this.ballRadius, 0, Math.PI * 2)
      this.ctx.fillStyle = '#0095DD'
      this.ctx.fill()
      this.ctx.closePath()
    },
    drawPaddle() {
      this.ctx.beginPath()
      this.ctx.rect(this.paddleX, this.canvas.height - this.paddleHeight, this.paddleWidth, this.paddleHeight)
      this.ctx.fillStyle = '#0095DD'
      this.ctx.fill()
      this.ctx.closePath()
    },
    collisionDetection() {
      for (let c = 0; c < this.brickColumnCount; c++) {
        for (let r = 0; r < this.brickRowCount; r++) {
          const b = this.bricks[c][r]
          if (b.status === 1) {
            if (
              this.x > b.x &&
              this.x < b.x + this.brickWidth &&
              this.y > b.y &&
              this.y < b.y + this.brickHeight
            ) {
              // play hit sound (reset to start so rapid hits sound correctly)
              if (this.hitAudio) {
                try {
                  this.hitAudio.currentTime = 0
                  this.hitAudio.play().catch(()=>{})
                } catch (e) { /* ignore play errors */ }
              }
              this.dy = -this.dy
              b.status = 0
              this.score++
              if (this.score === this.brickRowCount * this.brickColumnCount) {
                // alert('Congratulation!YOU WIN!')
                cancelAnimationFrame(this.animationFrame)
                this.showCongratsModal = true;
              }
            }
          }
        }
      }
    },
    draw() {
      // clear canvas then draw background if loaded
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
      if (this.bgLoaded && this.bgImage) {
        // draw background stretched to canvas size
        this.ctx.drawImage(this.bgImage, 0, 0, this.canvas.width, this.canvas.height)
      } else {
        // fallback background color
        this.ctx.fillStyle = '#eee'
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height)
      }
      this.drawBricks()
      this.drawBall()
      this.drawPaddle()
      this.collisionDetection()

      // Bounce off walls
      if (this.x + this.dx > this.canvas.width - this.ballRadius || this.x + this.dx < this.ballRadius) {
        this.dx = -this.dx
      }
      if (this.y + this.dy < this.ballRadius) {
        this.dy = -this.dy
      } else if (this.y + this.dy > this.canvas.height - this.ballRadius) {
        if (this.x > this.paddleX && this.x < this.paddleX + this.paddleWidth) {
          this.dy = -this.dy
        } else {
          
          cancelAnimationFrame(this.animationFrame)
          this.showConfirm = true;
          return
         // alert('GAME OVER')
          cancelAnimationFrame(this.animationFrame)
          return
        }
      }

      // Paddle movement
      if (this.rightPressed && this.paddleX < this.canvas.width - this.paddleWidth) {
        this.paddleX += 7
      } else if (this.leftPressed && this.paddleX > 0) {
        this.paddleX -= 7
      }

      // Move ball
      this.x += this.dx
      this.y += this.dy
      this.animationFrame = requestAnimationFrame(this.draw)
    },
    startGame() {
      cancelAnimationFrame(this.animationFrame)
      this.initBricks()
      this.x = this.canvas.width / 2
      this.y = this.canvas.height - 30
      this.dx = 2
      this.dy = -2
      this.paddleX = (this.canvas.width - this.paddleWidth) / 2
      this.score = 0
      this.draw()
    },
    keyDownHandler(e) {
      if (e.key === 'Right' || e.key === 'ArrowRight') this.rightPressed = true
      else if (e.key === 'Left' || e.key === 'ArrowLeft') this.leftPressed = true
    },
    keyUpHandler(e) {
      if (e.key === 'Right' || e.key === 'ArrowRight') this.rightPressed = false
      else if (e.key === 'Left' || e.key === 'ArrowLeft') this.leftPressed = false
    },
    // move paddle to pointer (mouse)
    onPointerMove(e) {
      if (!this.canvas) return
      const rect = this.canvas.getBoundingClientRect()
      const x = e.clientX - rect.left
      this.paddleX = Math.max(0, Math.min(this.canvas.width - this.paddleWidth, x - this.paddleWidth / 2))
    },
    // touch handler (prevent page scroll)
    onTouchMove(e) {
      e.preventDefault()
      if (!this.canvas) return
      const rect = this.canvas.getBoundingClientRect()
      const touch = e.touches && e.touches[0]
      if (!touch) return
      const x = touch.clientX - rect.left
      this.paddleX = Math.max(0, Math.min(this.canvas.width - this.paddleWidth, x - this.paddleWidth / 2))
    },

    handleRestart() {
      this.showConfirm = false
      this.showCongratsModal = false
      this.startGame()
    },

  handleCancel() {
      this.showConfirm = false
      this.showCongratsModal = false
    },
  },
}
</script>

<style scoped>
/* changed code: make layout friendly for iPhone 16 Pro (portrait) and similar narrow/long phones */
.game-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  min-height: 100vh;
  width: 100%;
  box-sizing: border-box;
  padding: env(safe-area-inset-top, 12px) 12px env(safe-area-inset-bottom, 12px);
  gap: 10px;
  background-size: cover;
  background-position: center;
}

/* canvas responsive: use viewport width but keep aspect ratio */
canvas {
  width: min(92vw, 460px);          /* shrink on small screens, cap on large */
  aspect-ratio: 460 / 340;         /* force correct aspect */
  height: auto;
  max-height: calc(100vh - 160px); /* leave space for controls/modal */
  border: 1px solid #000;
  background: #eee;
  display: block;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.15);
}

/* controls optimized for touch */
.controls {
  margin-top: 6px;
  text-align: center;
  display: flex;
  gap: 10px;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  width: 100%;
  padding: 0 6px;
}

.controls button,
.controls .base-button {
  padding: 12px 16px;
  font-size: 1rem;
  border-radius: 8px;
  touch-action: manipulation;
}

/* Score text responsive */
.h1 {
  margin: 1px;
  color: aqua;
  font-size: 1.05rem;
}

/* better layout on very narrow screens */
@media (max-width: 420px) {
  canvas {
    width: calc(100vw - 24px);
    max-height: 60vh;
  }
  .controls {
    gap: 8px;
  }
  .h1 {
    font-size: 1rem;
  }
}

/* landscape safety */
@media (orientation: landscape) and (max-height: 420px) {
  canvas {
    width: calc(70vw);
    max-height: calc(100vh - 140px);
  }
}
</style>