
<template>
  <div class="music-app">

    <!-- Header -->
    <header class="topbar">
      <div class="brand">
        <span class="brand-icon">♫</span>
        <span>MP3 Music Player</span>
      </div>
      <a
        class="library-label"
        href="https://www.abrahamparker.com/songlist/songs.json"
        target="_blank"
        rel="noopener noreferrer"
      >
        <span>▤</span> My Music
      </a>
    </header>

    <main class="layout">
      <!-- Sidebar -->
      <aside class="sidebar">
        <h2>📁 Song Library</h2>

        <button
          class="folder"
          :class="{ active: selectedFolder === 'All Songs' }"
          @click="selectedFolder = 'All Songs'"
        >
          <span>🎵 All Songs</span>
          <span class="folder-count">{{ songs.length }}</span>
        </button>

        <button
          v-for="folder in folders"
          :key="folder"
          class="folder"
          :class="{ active: selectedFolder === folder }"
          @click="selectedFolder = folder"
        >
          <span>📂 {{ folder }}</span>
          <span class="folder-count">
            {{ songs.filter(song => song.folder === folder).length }}
          </span>
        </button>

        <div class="sidebar-info">
          <span class="status-dot"></span>
          Music Library
          <p>Powered by your MP3 collection</p>
        </div>
      </aside>

      <!-- Main content -->
      <section class="content">
        <!-- Now playing -->
        <div class="now-playing">
          <div class="album-art">
            <span>♫</span>
          </div>

          <div class="track-info">
            <p class="eyebrow">NOW PLAYING</p>

            <h1>
              {{ currentSong ? currentSong.title : 'Welcome to My Music' }}
            </h1>

            <p class="artist">
              {{ currentSong ? currentSong.artist : 'Select a song to begin' }}
            </p>

            <div class="progress-area">
              <input
                class="progress-slider"
                type="range"
                min="0"
                :max="duration || 1"
                step="0.1"
                :value="currentTime"
                :disabled="!currentSong || !duration"
                aria-label="Seek through song"
                @input="seek"
              />

              <div class="time-row">
                <span>{{ formatTime(currentTime) }}</span>
                <span>{{ formatTime(duration) }}</span>
              </div>
            </div>

            <div class="controls">
              <button
                class="control-button"
                :class="{ enabled: shuffle }"
                title="Shuffle"
                aria-label="Toggle shuffle"
                @click="shuffle = !shuffle"
              >⤨</button>

              <button
                class="control-button"
                title="Previous song"
                aria-label="Previous song"
                @click="previousSong"
              >|◀</button>

              <button
                class="play-button"
                :disabled="!currentSong && !songs.length"
                :aria-label="isPlaying ? 'Pause' : 'Play'"
                @click="togglePlay"
              >
                {{ isPlaying ? 'Ⅱ' : '▶' }}
              </button>

              <button
                class="control-button"
                title="Next song"
                aria-label="Next song"
                @click="nextSong"
              >▶|</button>

              <button
                class="control-button"
                :class="{ enabled: repeat }"
                title="Repeat current song"
                aria-label="Toggle repeat"
                @click="repeat = !repeat"
              >⟲</button>
            </div>
          </div>
        </div>

        <!-- Playlist heading and search -->
        <div class="playlist-header">
          <div>
            <h2>Playlist</h2>
            <p>{{ filteredSongs.length }} songs available</p>
          </div>

          <input
            v-model="search"
            class="search"
            type="search"
            placeholder="⌕  Search songs..."
            aria-label="Search songs"
          />
        </div>

        <!-- Loading / errors -->
        <div v-if="loading" class="message">
          <span class="loading-spinner"></span>
          Loading your music library...
        </div>

        <div v-else-if="error" class="error-box">
          <strong>Unable to load music</strong>
          <p>{{ error }}</p>
          <button class="retry-button" @click="loadSongs">
            Try Again
          </button>
        </div>

        <!-- Songs table -->
        <div v-else class="song-list">
          <div class="song-row table-heading">
            <span>#</span>
            <span>Title</span>
            <span>Artist</span>
            <span>Duration</span>
          </div>

          <button
            v-for="(song, index) in filteredSongs"
            :key="song.url"
            class="song-row song"
            :class="{ selected: currentSong?.url === song.url }"
            @click="playSong(song)"
          >
            <span class="song-number">
              <span v-if="currentSong?.url === song.url && isPlaying">
                ♪
              </span>
              <span v-else>{{ index + 1 }}</span>
            </span>

            <span class="song-title">
              <span class="music-note">♫</span>
              <span class="song-name">{{ song.title }}</span>
            </span>

            <span class="song-artist">{{ song.artist }}</span>

            <span class="song-duration">
              {{ song.duration || '—:——' }}
            </span>
          </button>

          <div
            v-if="filteredSongs.length === 0"
            class="empty-state"
          >
            <span class="empty-icon">♫</span>
            <p>No songs found.</p>
            <small>Try another search or select All Songs.</small>
          </div>
        </div>

        <div class="library-footer">
          <span>{{ songs.length }} tracks in your library</span>
          <button @click="loadSongs" :disabled="loading">
            ↻ Refresh playlist
          </button>
        </div>
      </section>
    </main>

    <!-- HTML audio engine -->
    <audio
      ref="audio"
      :src="currentSong ? currentSong.url : ''"
      preload="metadata"
      @loadedmetadata="updateDuration"
      @durationchange="updateDuration"
      @timeupdate="updateTime"
      @ended="onEnded"
      @play="isPlaying = true"
      @pause="isPlaying = false"
      @error="onAudioError"
    ></audio>

    <!-- Persistent bottom player -->
    <footer class="bottom-player">
      <div class="bottom-song">
        <div class="mini-art">♫</div>

        <div class="bottom-track-text">
          <strong>
            {{ currentSong ? currentSong.title : 'No song selected' }}
          </strong>
          <small>
            {{ currentSong ? currentSong.artist : 'Choose a song to play' }}
          </small>
        </div>
      </div>

      <div class="bottom-controls">
        <button
          title="Previous song"
          aria-label="Previous song"
          @click="previousSong"
        >|◀</button>

        <button
          class="bottom-play-button"
          :disabled="!currentSong && !songs.length"
          :aria-label="isPlaying ? 'Pause' : 'Play'"
          @click="togglePlay"
        >
          {{ isPlaying ? 'Ⅱ' : '▶' }}
        </button>

        <button
          title="Next song"
          aria-label="Next song"
          @click="nextSong"
        >▶|</button>
      </div>

      <div class="volume-control">
        <span>◖))</span>
        <input
          v-model.number="volume"
          type="range"
          min="0"
          max="1"
          step="0.05"
          aria-label="Volume"
          @input="setVolume"
        />
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'Mp3Player',

  data() {
    return {
      // Your actual music playlist.
      songListUrl:
        'https://www.abrahamparker.com/songlist/songs.json',

      // Audio files are also loaded from this server.
      musicBaseUrl: 'https://www.abrahamparker.com/',

      songs: [],
      currentSong: null,

      selectedFolder: 'All Songs',
      search: '',

      loading: false,
      error: '',

      isPlaying: false,
      currentTime: 0,
      duration: 0,
      volume: 0.8,

      shuffle: false,
      repeat: false
    };
  },

  computed: {
    folders() {
      return [
        ...new Set(
          this.songs
            .map(song => song.folder)
            .filter(Boolean)
        )
      ];
    },

    filteredSongs() {
      const query = this.search.trim().toLowerCase();

      return this.songs.filter(song => {
        const matchesFolder =
          this.selectedFolder === 'All Songs' ||
          song.folder === this.selectedFolder;

        const matchesSearch =
          !query ||
          [song.title, song.artist, song.folder]
            .some(value =>
              String(value || '').toLowerCase().includes(query)
            );

        return matchesFolder && matchesSearch;
      });
    }
  },

  async mounted() {
    await this.loadSongs();
  },

  beforeUnmount() {
    const audio = this.$refs.audio;

    if (audio) {
      audio.pause();
      audio.removeAttribute('src');
      audio.load();
    }
  },

  methods: {
    // Load your existing JSON array.
    async loadSongs() {
      this.loading = true;
      this.error = '';

      try {
        const response = await fetch(this.songListUrl, {
          cache: 'no-cache'
        });

        if (!response.ok) {
          throw new Error(
            `Playlist request failed: HTTP ${response.status}`
          );
        }

        const data = await response.json();

        // Your existing file contains an array:
        // [{ "title": "...", "url": "/songlist/song.mp3" }]
        const list = Array.isArray(data)
          ? data
          : data.songs;

        if (!Array.isArray(list)) {
          throw new Error(
            'The JSON file must contain an array of songs.'
          );
        }

        this.songs = list
          .filter(song =>
            song &&
            typeof song.url === 'string' &&
            song.url.trim() !== ''
          )
          .map(song => {
            const fullUrl = new URL(
              song.url,
              this.musicBaseUrl
            ).href;

            return {
              title:
                song.title ||
                this.titleFromUrl(fullUrl),

              artist:
                song.artist ||
                'Unknown Artist',

              folder:
                song.folder ||
                this.folderFromUrl(fullUrl),

              url: fullUrl,
              duration: ''
            };
          });

        if (!this.songs.length) {
          this.error = 'Your playlist contains no valid song URLs.';
        }
      } catch (err) {
        console.error('Playlist loading error:', err);

        this.error =
          'Could not load songs.json. Please check that the playlist ' +
          'is accessible and that cross-origin access (CORS) is allowed ' +
          'if this page is hosted on a different domain. ' +
          err.message;
      } finally {
        this.loading = false;
      }
    },

    // Derive a readable title if the JSON title is missing.
    titleFromUrl(url) {
      try {
        const filename = new URL(url).pathname.split('/').pop();

        return decodeURIComponent(filename || 'Untitled Song')
          .replace(/\.[^/.]+$/, '')
          .replace(/[_-]+/g, ' ');
      } catch {
        return 'Untitled Song';
      }
    },

    // Your current JSON has no folder field.
    // Group songs by their URL directory where possible.
    folderFromUrl(url) {
      try {
        const pathname = new URL(url).pathname;
        const parts = pathname.split('/').filter(Boolean);

        if (parts.length > 2) {
          return decodeURIComponent(parts[parts.length - 2]);
        }

        return 'General';
      } catch {
        return 'General';
      }
    },

    async playSong(song) {
      this.error = '';
      this.currentSong = song;
      this.currentTime = 0;
      this.duration = 0;

      await this.$nextTick();

      const audio = this.$refs.audio;

      if (!audio) return;

      audio.volume = this.volume;

      try {
        await audio.play();
      } catch (err) {
        console.error('Playback error:', err);

        this.isPlaying = false;
        this.error =
          'Unable to play this song. Check the MP3 URL, file format, ' +
          'and server access.';
      }
    },

    async togglePlay() {
      if (!this.currentSong) {
        if (this.songs.length) {
          await this.playSong(this.songs[0]);
        }

        return;
      }

      const audio = this.$refs.audio;

      if (!audio) return;

      try {
        if (audio.paused) {
          this.error = '';
          await audio.play();
        } else {
          audio.pause();
        }
      } catch (err) {
        console.error('Playback error:', err);
        this.error = 'Playback could not start. Please try again.';
      }
    },

    nextSong() {
      if (!this.songs.length) return;

      const currentIndex = this.songs.findIndex(
        song => song.url === this.currentSong?.url
      );

      let nextIndex;

      if (this.shuffle && this.songs.length > 1) {
        do {
          nextIndex = Math.floor(
            Math.random() * this.songs.length
          );
        } while (nextIndex === currentIndex);
      } else {
        nextIndex = (currentIndex + 1) % this.songs.length;
      }

      this.playSong(this.songs[nextIndex]);
    },

    previousSong() {
      if (!this.songs.length) return;

      const audio = this.$refs.audio;

      if (audio && audio.currentTime > 3) {
        audio.currentTime = 0;
        return;
      }

      const currentIndex = this.songs.findIndex(
        song => song.url === this.currentSong?.url
      );

      const previousIndex =
        (currentIndex - 1 + this.songs.length) %
        this.songs.length;

      this.playSong(this.songs[previousIndex]);
    },

    onEnded() {
      if (this.repeat && this.$refs.audio) {
        this.$refs.audio.currentTime = 0;

        this.$refs.audio.play().catch(err => {
          console.error('Repeat playback error:', err);
        });
      } else {
        this.nextSong();
      }
    },

    seek(event) {
      const audio = this.$refs.audio;

      if (audio && Number.isFinite(audio.duration)) {
        audio.currentTime = Number(event.target.value);
      }
    },

    updateTime() {
      const audio = this.$refs.audio;

      if (audio) {
        this.currentTime = audio.currentTime || 0;
      }
    },

    updateDuration() {
      const audio = this.$refs.audio;

      if (audio && Number.isFinite(audio.duration)) {
        this.duration = audio.duration;

        // Update the selected song's displayed duration.
        if (this.currentSong) {
          this.currentSong.duration = this.formatTime(
            audio.duration
          );
        }
      }
    },

    setVolume() {
      if (this.$refs.audio) {
        this.$refs.audio.volume = this.volume;
      }
    },

    onAudioError() {
      if (this.currentSong) {
        this.isPlaying = false;

        this.error =
          'This audio file could not be loaded. Please check the song URL, ' +
          'file permissions, and supported audio format.';
      }
    },

    formatTime(seconds) {
      if (!Number.isFinite(seconds) || seconds < 0) {
        return '0:00';
      }

      const minutes = Math.floor(seconds / 60);
      const remainingSeconds = Math.floor(seconds % 60);

      return (
        minutes +
        ':' +
        String(remainingSeconds).padStart(2, '0')
      );
    }
  }
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.music-app {
    margin-top:-40px;
  min-height: 100vh;
  padding-bottom: 50px;
  background: #0b1220;
  color: #edf3ff;
  font-family: Arial, Helvetica, sans-serif;
  background-position: center 100%;
  background-size: contain;
background-position: center top;
background-repeat: no-repeat;
background-color: #07152f;
background-attachment: scroll;

}

/* Header */

.topbar {
   margin-top: -40px;
  min-height: 72px;

  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;

  border-bottom: 1px solid #29364b;
}

.brand {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 25px;
  font-weight: 700;
   background:none;
}

.brand-icon {
  color: #7fb8ff;
  font-size: 32px;
 
}

.library-label {
  color: #c6d7f3;
  text-decoration: none;
  white-space: nowrap;
}

.library-label:hover {
  color: #66b0ff;
}

/* Main layout */

.layout {
  display: grid;
  grid-template-columns: 245px minmax(0, 1fr);
  min-height: calc(100vh - 166px);
}

.sidebar {
  padding: 24px 14px;
  background: #0e1829;
  border-right: 1px solid #26354b;
}

.sidebar h2 {
  margin: 0 0 20px;
  font-size: 19px;
}

.folder {
  width: 100%;
  display: flex;
  justify-content: space-between;
  gap: 8px;
  margin-bottom: 5px;
  padding: 13px 12px;
  border: 0;
  border-radius: 6px;
  background: transparent;
  color: #d7e4fa;
  font-size: 14px;
  text-align: left;
  cursor: pointer;
}

.folder:hover,
.folder.active {
  background: #1b5dc1;
  color: white;
}

.folder-count {
  color: #a7bfdf;
}

.sidebar-info {
  margin-top: 34px;
  padding: 12px;
  border-top: 1px solid #29364b;
  color: #a7bfdf;
  font-size: 12px;
}

.sidebar-info p {
  color: #8194b2;
  line-height: 1.5;
}

.status-dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  margin-right: 6px;
  border-radius: 50%;
  background: #35d49a;
}

/* Player area */

.content {
  min-width: 0;
}

.now-playing {
  display: flex;
  align-items: center;
  gap: 28px;
  padding: 30px;
  background: linear-gradient(120deg, #13233d, #0c1423);
  border-bottom: 1px solid #26354b;
}

.album-art {
  display: grid;
  place-items: center;
  flex-shrink: 0;
  width: 160px;
  height: 160px;
  border-radius: 10px;
  background: linear-gradient(135deg, #2dc8e9, #a347de, #245bf2);
  box-shadow: 0 10px 35px #0003;
}

.album-art span {
  color: white;
  font-size: 86px;
}

.track-info {
  flex: 1;
  min-width: 0;
}

.eyebrow {
  margin: 0 0 8px;
  color: #5bb5ff;
  font-size: 11px;
  font-weight: bold;
  letter-spacing: 2px;
}

.track-info h1 {
  margin: 0 0 8px;
  font-size: clamp(20px, 3vw, 30px);
  overflow-wrap: anywhere;
}

.artist {
  margin: 0;
  color: #a8bedf;
  font-size: 15px;
}

.progress-area {
  margin-top: 24px;
}

.progress-slider {
  display: block;
  width: 100%;
  margin: 0;
  accent-color: #2785ff;
  cursor: pointer;
}

.time-row {
  display: flex;
  justify-content: space-between;
  margin-top: 7px;
  color: #b8c9e7;
  font-size: 12px;
}

.controls {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: clamp(12px, 3vw, 34px);
  margin-top: 20px;
}

.control-button {
  border: 0;
  background: transparent;
  color: #eaf2ff;
  font-size: 22px;
  cursor: pointer;
}

.control-button:hover,
.control-button.enabled {
  color: #42a2ff;
}

.play-button,
.bottom-play-button {
  display: grid;
  place-items: center;
  flex-shrink: 0;
  width: 54px;
  height: 54px;
  border: 1px solid #8dcaff;
  border-radius: 50%;
  background: #2083f6;
  color: white;
  font-size: 21px;
  cursor: pointer;
}

.play-button:hover,
.bottom-play-button:hover {
  background: #3995ff;
}

.play-button:disabled,
.bottom-play-button:disabled {
  opacity: 0.45;
  cursor: not-allowed;
}

/* Playlist */

.playlist-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
  padding: 24px 26px 18px;
  
}

.playlist-header h2 {
  margin: 0 0 5px;
  font-size: 21px;
}

.playlist-header p {
  margin: 0;
  color: #91a5c5;
  font-size: 13px;
}

.search {
  width: min(300px, 48%);
  padding: 12px 14px;
  border: 1px solid #34445d;
  border-radius: 7px;
  outline: none;
  background: #101c2e;
  color: white;
  font-size: 14px;
}

.search:focus {
  border-color: #358dff;
}

.song-list {
  margin: 0 26px;
  overflow-x: auto;
  border: 1px solid #26364e;
  border-radius: 6px;
}

.song-row {
  display: grid;
  grid-template-columns: 44px minmax(150px, 2fr) minmax(100px, 1fr) 80px;
  align-items: center;
  gap: 12px;
  width: 100%;
  min-width: 540px;
  padding: 14px 15px;
  text-align: left;
}

.table-heading {
  background: #142137;
  color: #a9bddc;
  font-size: 12px;
  font-weight: 600;
}

.song {
  border: 0;
  border-top: 1px solid #233148;
  background: #0e192a;
  color: #e1ebfa;
  font-size: 14px;
  cursor: pointer;
}

.song:hover {
  background: #172c49;
}

.song.selected {
  background: #174b91;
}

.song-number {
  text-align: center;
  color: #c1d3ef;
}

.song-title {
  display: flex;
  align-items: center;
  gap: 10px;
  min-width: 0;
}

.music-note {
  flex-shrink: 0;
  color: #81b9ff;
  font-size: 19px;
}

.song-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.song-artist,
.song-duration {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.song-artist {
  color: #b8cbea;
}

.song-duration {
  color: #c1d3ef;
  font-variant-numeric: tabular-nums;
}

.empty-state {
  padding: 45px 20px;
  color: #a7bad8;
  text-align: center;
}

.empty-icon {
  font-size: 38px;
}

.empty-state p {
  margin-bottom: 5px;
}

.empty-state small {
  color: #8297b7;
}

.message {
  padding: 30px;
  color: #b4c5df;
  text-align: center;
}

.loading-spinner {
  display: inline-block;
  width: 15px;
  height: 15px;
  margin-right: 8px;
  border: 2px solid #5b7394;
  border-top-color: #42a2ff;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
  vertical-align: middle;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.error-box {
  margin: 20px 26px;
  padding: 20px;
  border: 1px solid #6b343d;
  border-radius: 8px;
  background: #2b1822;
  color: #ffb8bf;
  overflow-wrap: anywhere;
}

.error-box p {
  line-height: 1.6;
  font-size: 13px;
}

.retry-button {
  padding: 9px 14px;
  border: 0;
  border-radius: 5px;
  background: #a72f48;
  color: white;
  cursor: pointer;
}

.library-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  margin: 15px 26px;
  color: #8297b7;
  font-size: 12px;
}

.library-footer button {
  border: 0;
  background: transparent;
  color: #73b4ff;
  cursor: pointer;
}

/* Fixed bottom playback bar */

.bottom-player {
  position: fixed;
  z-index: 10;
  right: 0;
  bottom: 0;
  left: 0;
  display: grid;
  grid-template-columns: minmax(0, 1fr) auto minmax(0, 1fr);
  align-items: center;
  gap: 20px;
  min-height: 82px;
  padding: 12px 25px;
  border-top: 1px solid #34445d;
  background: #142137;
}

.bottom-song {
  display: flex;
  align-items: center;
  gap: 12px;
  min-width: 0;
}

.mini-art {
  display: grid;
  place-items: center;
  flex-shrink: 0;
  width: 46px;
  height: 46px;
  border-radius: 6px;
  background: linear-gradient(135deg, #2dc8e9, #a347de, #245bf2);
  color: white;
  font-size: 26px;
}

.bottom-track-text {
  min-width: 0;
}

.bottom-song strong,
.bottom-song small {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.bottom-song strong {
  font-size: 13px;
}

.bottom-song small {
  margin-top: 5px;
  color: #9bb0cf;
  font-size: 12px;
}

.bottom-controls {
  display: flex;
  align-items: center;
  gap: 18px;
}

.bottom-controls > button:not(.bottom-play-button) {
  border: 0;
  background: transparent;
  color: #eaf2ff;
  font-size: 20px;
  cursor: pointer;
}

.bottom-play-button {
  width: 42px;
  height: 42px;
  font-size: 17px;
}

.volume-control {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 10px;
  color: #c7d8f3;
}

.volume-control input {
  width: 105px;
  accent-color: #2785ff;
  cursor: pointer;
}

/* Responsive design */

@media (max-width: 900px) {
  .layout {
    grid-template-columns: 200px minmax(0, 1fr);
  }

  .now-playing {
    padding: 24px 18px;
    gap: 18px;
  }

  .album-art {
    width: 115px;
    height: 115px;
  }

  .album-art span {
    font-size: 65px;
  }

  .song-list {
    margin: 0 16px;
  }

  .playlist-header {
    padding-right: 16px;
    padding-left: 16px;
  }

  .volume-control input {
    width: 75px;
  }
}

@media (max-width: 650px) {
  .topbar {
    min-height: 62px;
    padding: 12px 16px;
  }

  .brand {
    font-size: 19px;
  }

  .brand-icon {
    font-size: 27px;
  }

  .layout {
    grid-template-columns: 1fr;
  }

  .sidebar {
    padding: 14px;
    border-right: 0;
    border-bottom: 1px solid #26354b;
  }

  .sidebar h2 {
    margin-bottom: 10px;
  }

  .folder {
    display: inline-flex;
    width: auto;
    gap: 10px;
    margin-right: 4px;
    padding: 10px;
    font-size: 12px;
  }

  .sidebar-info {
    display: none;
  }

  .now-playing {
    padding: 20px 14px;
    gap: 14px;
  }

  .album-art {
    width: 75px;
    height: 75px;
  }

  .album-art span {
    font-size: 42px;
  }

  .track-info h1 {
    font-size: 19px;
  }

  .artist {
    font-size: 13px;
  }

  .progress-area {
    margin-top: 16px;
  }

  .controls {
    gap: 12px;
    margin-top: 16px;
  }

  .control-button {
    font-size: 17px;
  }

  .play-button {
    width: 44px;
    height: 44px;
  }

  .playlist-header {
    align-items: flex-start;
    padding: 20px 14px 14px;
  }

  .playlist-header h2 {
    font-size: 18px;
  }

  .search {
    width: 52%;
    min-width: 0;
    padding: 10px;
    font-size: 12px;
  }

  .song-list {
    margin: 0 12px;
  }

  .library-footer {
    margin: 14px 12px;
  }

  .bottom-player {
    grid-template-columns: minmax(0, 1fr) auto;
    gap: 10px;
    padding: 10px 12px;
  }

  .volume-control {
    display: none;
  }

  .bottom-controls {
    gap: 10px;
  }

  .bottom-song strong {
    font-size: 12px;
  }

  .bottom-song small {
    font-size: 11px;
  }

  .mini-art {
    width: 38px;
    height: 38px;
  }
}

@media (max-width: 380px) {
  .library-label {
    font-size: 12px;
  }

  .album-art {
    width: 58px;
    height: 58px;
  }

  .album-art span {
    font-size: 33px;
  }

  .track-info h1 {
    font-size: 16px;
  }

  .controls {
    gap: 8px;
  }

  .bottom-controls {
    gap: 6px;
  }

  .bottom-controls > button:not(.bottom-play-button) {
    font-size: 16px;
  }

  .bottom-play-button {
    width: 36px;
    height: 36px;
  }
  
}

.music-app {
  min-height: 100vh;
  padding-bottom: 94px;

  /* Your m2M TV USA background image */
  background-image:
    linear-gradient(
      rgba(5, 15, 40, 0.38),
      rgba(5, 15, 40, 0.65)
    ),
    url('@/assets/m2m-tv-background.png');

  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;

  color: #ffffff;
  font-family: Arial, Helvetica, sans-serif;
}

.topbar {
    padding-left: 28px;
    padding-right: 28px;
 background: rgba(8, 20, 55, 0.48);
}

.sidebar {
  background: rgba(8, 18, 45, 0.78);
  backdrop-filter: blur(8px);
}

.now-playing {
  background: rgba(8, 20, 55, 0.48);
  border-bottom: 1px solid rgba(255, 255, 255, 0.15);
}

.song-list {
  background: rgba(8, 18, 40, 0.75);
  border-color: rgba(255, 255, 255, 0.18);
}

.table-heading {
  background: rgba(15, 35, 75, 0.90);
}

.song {
  background: rgba(8, 18, 40, 0.72);
  color: #ffffff;
}

.song:hover {
  background: rgba(30, 100, 200, 0.75);
}

.song.selected {
  background: rgba(20, 100, 220, 0.85);
}

.bottom-player {
  background: rgba(8, 18, 45, 0.92);
  /* backdrop-filter: blur(12px); */
  border-top: 1px solid rgba(255, 255, 255, 0.2);
}

</style>
