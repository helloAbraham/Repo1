<template>
  <div class="timer-app">
    <MarqueeStyle />
    <h1>⏱ Countdown Timer - Good Luck! </h1>

    <div class="inputs">
      <label>
        Hours:
        <input type="number" v-model.number="inputHours" min="0" />
      </label>
      <label>
        Minutes:
        <input type="number" v-model.number="inputMinutes" min="0" max="59" />
      </label>
      <BaseButtonStyle @click="setCustomTime">Set Time</BaseButtonStyle>
    </div>

    <div class="time-display">
      {{ formattedTime }}
    </div>

    <div class="actions">
      <BaseButtonStyle @click="startTimer" :disabled="running">Start</BaseButtonStyle>
      <BaseButtonStyle @click="pauseTimer" :disabled="!running">Pause</BaseButtonStyle>
      <BaseButtonStyle @click="resetTimer">Reset</BaseButtonStyle>
    </div>

    <audio ref="alarmAudio" :src="alarmSrc" preload="auto"></audio>
        <!-- Modal Alert -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <h2>⏰ Time’s Up!</h2>
        <img
          class="alert-gif"
          :src="funnyImage"
          preload="auto"
          alt="Alarm GIF"
        />
        <button class="close-btn" @click="closeModal">OK</button>
      </div>
    </div>

    <ModalComponentWithImg
      :visible="showModal"
      :imageUrl="funnyImage"
      @close="closeModal"
    >
      <template #header><h2>⏰ Time’s Up!</h2></template>
      <template #footer><BaseButtonStyle @click="closeModal">OK</BaseButtonStyle></template>
    </ModalComponentWithImg>
  </div>
</template>

<script>
import alarmFile from '@/assets/school_bell_sound.wav';
import BaseButtonStyle from './BaseButtonStyle.vue';
import funnyImage from '@/assets/Pen_Down.gif';
import ModalComponentWithImg from './ModalComponentWithImg.vue';
import MarqueeStyle from '@/components/MarqueeStyle.vue';

export default {
  name: "StopWatch",
  data() {
    return {
      inputHours: 0,
      inputMinutes: 0,
      timeLeft: 0, // total seconds
      timer: null,
      running: false,
      alarmSrc: alarmFile,
      funnyImage: funnyImage,
      showModal: false,
      initialTimeLeft: 0, // store the configured time so Reset can restore it
    };
  },
  components: {
    BaseButtonStyle,
    ModalComponentWithImg,
    MarqueeStyle,
  },
  computed: {
    formattedTime() {
      const hours = String(Math.floor(this.timeLeft / 3600)).padStart(2, "0");
      const minutes = String(Math.floor((this.timeLeft % 3600) / 60)).padStart(2, "0");
      const seconds = String(this.timeLeft % 60).padStart(2, "0");
      return `${hours}:${minutes}:${seconds}`;
    },
  },
  methods: {
    setCustomTime() {
      const totalSeconds = this.inputHours * 3600 + this.inputMinutes * 60;
      if (totalSeconds <= 0) {
        alert("Please enter a valid time greater than 0.");
        return;
      }
      // save configured time so Reset restores it
      this.initialTimeLeft = totalSeconds;
      this.timeLeft = totalSeconds;
      // ensure any running timer is cleared before user starts again
      this.clearTimer();
      this.running = false;
    },
    startTimer() {
      if (this.timeLeft <= 0 || this.running) return;
      this.running = true;
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
        } else {
          this.clearTimer();
          this.playAlarm();
          this.showModal = true;
        }
      }, 1000);
    },
    pauseTimer() {
      this.clearTimer();
      this.running = false;
    },
    // Reset restores the timer to the last configured value (initialTimeLeft)
    resetTimer() {
      this.clearTimer();
      this.running = false;
      // If user set a time, restore it. Otherwise reset to 0.
      this.timeLeft = this.initialTimeLeft || 0;
    },
    stopTimer() {
      this.clearTimer();
      this.running = false;
    },
    // single helper to safely clear interval and null it
    clearTimer() {
      if (this.timer != null) {
        clearInterval(this.timer);
        this.timer = null;
      }
    },
    playAlarm() {
      const alarm = this.$refs.alarmAudio;
      if (!alarm) return;
      alarm.play().catch((err) => {
        // handle autoplay block or other play errors
        console.warn('play failed:', err);
      });
    },
    closeModal() {
      this.showModal = false;
      this.resetTimer();
      this.timeLeft = 0;
    },
  },
  beforeUnmount() {
    this.clearTimer();
  },
};
</script>

<style scoped>
.timer-app {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 1px;
  font-family: Arial, sans-serif;
}

.inputs {
      display: flex;
  margin-bottom: 2px;

}

select {
  font-size: 1em;
  padding: 6px 8px;
  min-width: 6ch;
  border-radius: 4px;
  box-sizing: border-box;
}

label {
  margin: 5 15px;
  font-size: 2.5em;
  color: aqua;
}

input {
  width: 100px;
  padding: 5px;
  margin-left: 5px;
  margin-right: 10px;
  font-size: 1em;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.time-display {
  font-size: 7em;
  margin: 5px 0;
  margin-bottom: 1px;
  font-weight: bold;
}

/* changed code: make buttons appear in one row */
.actions {
  margin: 10px 0;
  display: flex;
  gap: 10px;
  align-items: center;
  justify-content: center;
  flex-wrap: nowrap;
}

button {
  margin: 2px;
  padding: 10px 20px;
  font-size: 1em;
  border: none;
  border-radius: 8px;
  background: #42b883;
  color: white;
  cursor: pointer;
}

button:disabled {
  background: #ccc;
  cursor: not-allowed;
}
</style>
