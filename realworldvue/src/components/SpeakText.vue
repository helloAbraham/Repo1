<template>
  <div class="speak-text">
    <h1>🗣️ Text to Speech ...</h1>

    <textarea
      v-model="text"
      placeholder="Type or paste a large paragraph here..."
      rows="8"
    ></textarea>

    <div class="controls">
      <BaseButtonStyle @click="speakText" :disabled="isSpeaking">🔊 Speak</BaseButtonStyle>
      <BaseButtonStyle @click="stopSpeech" :disabled="!isSpeaking">⏹️ Stop</BaseButtonStyle>
    </div>

    <!-- preview with live highlight of spoken substring -->
    <div class="preview" v-html="highlightedHtml" aria-live="polite"></div>
  </div>
</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';


export default {
  name: "SpeakText",
    components: {
        BaseButtonStyle,
    },
  data() {
    return {
      text: "",
      isSpeaking: false,
      utterance: null,
      selectedVoice: null,
      // indexes of currently spoken substring (char indices)
      activeStart: -1,
      activeEnd: -1,
    };
  },
  mounted() {
    this.loadVoices();
    // some browsers populate voices asynchronously
    window.speechSynthesis.onvoiceschanged = this.loadVoices;
  },
  beforeUnmount() {
    window.speechSynthesis.onvoiceschanged = null;
    this.stopSpeech();
  },
  computed: {
    highlightedHtml() {
      const escape = (s) =>
        s
          .replace(/&/g, "&amp;")
          .replace(/</g, "&lt;")
          .replace(/>/g, "&gt;")
          .replace(/"/g, "&quot;")
          .replace(/'/g, "&#39;");
      const t = this.text || "";
      if (this.activeStart >= 0 && this.activeEnd > this.activeStart) {
        const before = escape(t.slice(0, this.activeStart));
        const mid = escape(t.slice(this.activeStart, this.activeEnd));
        const after = escape(t.slice(this.activeEnd));
        return `${before}<mark class="spoken">${mid}</mark>${after}`;
      }
      return escape(t);
    },
  },
  methods: {
    loadVoices() {
      const voices = window.speechSynthesis.getVoices() || [];
      const alex = voices.find((v) => v.name && v.name.toLowerCase().includes("alex"));
      const en = voices.find((v) => v.lang === "en-US");
      this.selectedVoice = alex || en || voices[0] || null;
    },
    speakText() {
      if (!this.text.trim()) return alert("Please enter some text to speak.");

      window.speechSynthesis.cancel();
      this.activeStart = -1;
      this.activeEnd = -1;

      const utter = new SpeechSynthesisUtterance(this.text);

      if (!this.selectedVoice) this.loadVoices();
      if (this.selectedVoice) utter.voice = this.selectedVoice;

      utter.rate = 1;
      utter.pitch = 1;
      utter.volume = 1;

      utter.onstart = () => (this.isSpeaking = true);
      utter.onend = () => {
        this.isSpeaking = false;
        this.activeStart = -1;
        this.activeEnd = -1;
      };
      utter.onerror = () => {
        this.isSpeaking = false;
        this.activeStart = -1;
        this.activeEnd = -1;
      };

      // highlight using boundary events when available
      utter.onboundary = (e) => {
        // prefer charIndex/charLength; fall back to word boundaries if provided
        if (e.charIndex !== undefined) {
          const len = e.charLength && e.charLength > 0 ? e.charLength : 1;
          this.activeStart = e.charIndex;
          this.activeEnd = this.activeStart + len;
        } else if (e.name === "word" && typeof e.charIndex === "number") {
          // some browsers set name + charIndex
          const len = e.charLength && e.charLength > 0 ? e.charLength : 1;
          this.activeStart = e.charIndex;
          this.activeEnd = this.activeStart + len;
        } else if (e.name === "word") {
          // best-effort: approximate by finding the nth word
          // not perfect but better than nothing
          const words = this.text.split(/\s+/);
          let wordIndex = 0;
          // boundary event doesn't give index in this fallback; skip
        }
      };

      this.utterance = utter;
      try {
        window.speechSynthesis.speak(utter);
      } catch (err) {
        console.warn("speak failed:", err);
      }
    },
    stopSpeech() {
      window.speechSynthesis.cancel();
      this.isSpeaking = false;
      this.activeStart = -1;
      this.activeEnd = -1;
    },
  },
};
</script>

<style scoped>
.speak-text {
  max-width: 720px;
  margin: 2rem auto;
  text-align: center;
  font-family: system-ui, sans-serif;
}
textarea {
  width: 100%;
  font-size: 1rem;
  padding: 1rem;
  border-radius: 6px;
  border: 1px solid #ccc;
  resize: vertical;
  box-sizing: border-box;
}
/* changed code: make buttons appear in one row */
.controls {
  display: flex;
  flex-direction: row;
  gap: 0.75rem;
  align-items: center;
  justify-content: center;
  flex-wrap: nowrap;
  margin-top: 1rem;
}
button {
  margin: 0;
  padding: 0.6rem 1.2rem;
  font-size: 1rem;
  cursor: pointer;
  border: none;
  border-radius: 6px;
  background-color: #4a90e2;
  color: white;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

/* preview area shows the full text and highlights spoken part */
.preview {
  margin-top: 1rem;
  padding: 0.75rem;
  border-radius: 6px;
  border: 1px dashed #ddd;
  text-align: left;
  white-space: pre-wrap;
  word-break: break-word;
  min-height: 3.2rem;
  background: #fafafa;
  font-size: 1rem;
  line-height: 1.4;
}
.preview mark.spoken {
  background: #87CEEB; /* sky blue */
  color: #000;
  padding: 0 2px;
  border-radius: 2px;
}
</style>