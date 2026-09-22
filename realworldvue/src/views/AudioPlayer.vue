<template>
  <div class="audio-player">
    <input type="file" accept="audio/*" @change="onFileChange" multiple />
    <div style="margin: 10px 0;">
      <label for="urlInput">Select URL to play song:</label>
      <input
        id="urlInput"
        v-model="urlInput"
        type="text"
        placeholder="Paste audio file URL here"
        style="width: 70%;"
      />
      <button @click="addUrlSong">Add</button>
    </div>
    <div v-if="audioFiles.length || defaultSongs.length || urlSongs.length" class="file-list">
      <label>Select from uploaded files, server songs, or URLs:</label>
      <select v-model="selectedFileIndex" @change="onSelectFile">
        <optgroup label="Uploaded Files" v-if="audioFiles.length">
          <option v-for="(file, idx) in audioFiles" :key="'local-' + idx" :value="'local-' + idx">
            {{ file.name }}
          </option>
        </optgroup>
        <optgroup label="Server Songs" v-if="defaultSongs.length">
          <option v-for="(song, idx) in defaultSongs" :key="'server-' + idx" :value="'server-' + idx">
            {{ song.name }}
          </option>
        </optgroup>
        <optgroup label="URLs" v-if="urlSongs.length">
          <option v-for="(song, idx) in urlSongs" :key="'url-' + idx" :value="'url-' + idx">
            {{ song.name }}
          </option>
        </optgroup>
      </select>
    </div>
    <audio ref="audio" :src="audioSrc" @timeupdate="updateTime" @ended="onEnded"></audio>
    <div class="controls" v-if="audioSrc">
      <button @click="togglePlay">
        <span v-if="!isPlaying">Play</span>
        <span v-else>Pause</span>
      </button>
      <span>{{ formatTime(currentTime) }} / {{ formatTime(duration) }}</span>
      <input
        type="range"
        min="0"
        :max="duration"
        step="0.1"
        v-model="currentTime"
        @input="seek"
      />
      <button @click="reset">Reset</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AudioPlayer",
  data() {
    return {
      audioFiles: [],
      defaultSongs: [],
      urlSongs: [],
      urlInput: "",
      selectedFileIndex: null,
      audioSrc: "",
      isPlaying: false,
      currentTime: 0,
      duration: 0
    };
  },
  mounted() {
    this.$refs.audio.addEventListener("loadedmetadata", () => {
      this.duration = this.$refs.audio.duration;
    });
    this.fetchDefaultSongs();
  },
  methods: {
    async fetchDefaultSongs() {
      this.defaultSongs = [
        { name: "Sample Song 1", url: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3" },
        { name: "Sample Song 2", url: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3" }
      ];
    },
    addUrlSong() {
      if (this.urlInput.trim()) {
        this.urlSongs.push({
          name: this.urlInput,
          url: this.urlInput
        });
        this.selectedFileIndex = `url-${this.urlSongs.length - 1}`;
        this.audioSrc = this.urlInput;
        this.reset();
        this.urlInput = "";
      }
    },
    onFileChange(e) {
      this.audioFiles = Array.from(e.target.files);
      if (this.audioFiles.length) {
        this.selectedFileIndex = 'local-0';
        this.audioSrc = URL.createObjectURL(this.audioFiles[0]);
        this.reset();
      }
    },
    onSelectFile() {
      if (typeof this.selectedFileIndex === "string") {
        if (this.selectedFileIndex.startsWith("local-")) {
          const idx = parseInt(this.selectedFileIndex.replace("local-", ""));
          const file = this.audioFiles[idx];
          if (file) {
            this.audioSrc = URL.createObjectURL(file);
            this.reset();
          }
        } else if (this.selectedFileIndex.startsWith("server-")) {
          const idx = parseInt(this.selectedFileIndex.replace("server-", ""));
          const song = this.defaultSongs[idx];
          if (song) {
            this.audioSrc = song.url;
            this.reset();
          }
        } else if (this.selectedFileIndex.startsWith("url-")) {
          const idx = parseInt(this.selectedFileIndex.replace("url-", ""));
          const song = this.urlSongs[idx];
          if (song) {
            this.audioSrc = song.url;
            this.reset();
          }
        }
      }
    },
    togglePlay() {
      const audio = this.$refs.audio;
      if (audio.paused) {
        audio.play();
        this.isPlaying = true;
      } else {
        audio.pause();
        this.isPlaying = false;
      }
    },
    updateTime() {
      this.currentTime = this.$refs.audio.currentTime;
    },
    seek() {
      this.$refs.audio.currentTime = this.currentTime;
    },
    reset() {
      this.$refs.audio.currentTime = 0;
      this.isPlaying = false;
      this.$refs.audio.pause();
    },
    onEnded() {
      this.isPlaying = false;
    },
    formatTime(seconds) {
      const min = Math.floor(seconds / 60);
      const sec = Math.floor(seconds % 60).toString().padStart(2, "0");
      return `${min}:${sec}`;
    }
  }
};
</script>