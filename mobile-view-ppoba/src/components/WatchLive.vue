```vue
<template>
  <div class="live-page">
    <div class="live-header">
      <h1>🔴 Live IPTV Broadcasting</h1>
      <div class="stream-status" :class="{ offline: !streamOnline }">
        <span class="status-dot"></span>
        {{ streamOnline ? "Stream Online" : "Stream Offline" }}
      </div>
    </div>

    <!-- Video Container -->
    <div
      ref="videoContainer"
      class="video-container"
      @dblclick="toggleFullscreen"
    >
      <!-- Video -->
      <video
        ref="video"
        class="video-player"
        controls
        controlslist="nofullscreen"
        autoplay
        muted
        playsinline
        @play="onVideoPlay"
        @playing="streamOnline = true"
        @error="streamOnline = false"
        @stalled="streamOnline = false"
        @waiting="streamOnline = false"
      ></video>

      <!-- Date + Time Overlay -->
      <div class="datetime-overlay">
        <div class="date-text">{{ currentDate }}</div>
        <div class="date-text">{{ currentTime }}</div>
      </div>

      <!-- Breaking News Overlay -->
      <div class="breaking-news-overlay">
        <div class="breaking-label">
          BREAKING NEWS:
        </div>

        <div class="breaking-content">
          <div
            v-if="breakingNews"
            class="breaking-marquee"
          >
            {{ breakingNews }}
          </div>

          <div
            v-else
            class="breaking-marquee"
          >
            Welcome to m2M TV usa — Live IPTV Broadcasting
          </div>
        </div>
      </div>

      <!-- Custom Fullscreen Button -->
      <button
        class="fullscreen-button"
        type="button"
        @click.stop="toggleFullscreen"
        :title="isFullscreen ? 'Exit Fullscreen' : 'Fullscreen'"
      >
        <span class="fullscreen-icon">
          {{ isFullscreen ? "⛶" : "⛶" }}
        </span>
        <span class="fullscreen-text">
          {{ isFullscreen ? "Exit Fullscreen" : "Fullscreen" }}
        </span>
      </button>

      <!-- Small Channel Branding -->
      <div class="channel-logo">
        <img src="@/assets/tv_logo.jpg" alt="Channel Logo" class="logo-image" />
      </div>
    </div>

    <!-- Information -->
    <div class="live-info">
      <h2>Thanks, You are watching m2M TV usa.</h2>
      <p>
        A Bais free news broadcasting service from m2M TV usa.
      </p>
    </div>
  </div>
</template>

<script>
import Hls from "hls.js";

export default {
  name: "WatchLive",

  data() {
    return {
      streamUrl:
        "https://www.abrahamparker.com/hls/stream.m3u8",

      newsUrl:
        "https://api.abrahamparker.com/api/content/1",

      hls: null,

      breakingNews: "",

      currentDate: "",
      currentTime: "",

      dateTimer: null,
      newsTimer: null,

      streamOnline: false,

      isFullscreen: false,
    };
  },

  mounted() {
    this.updateDateTime();

    this.dateTimer = setInterval(() => {
      this.updateDateTime();
    }, 1000);

    this.loadBreakingNews();

    this.newsTimer = setInterval(() => {
      this.loadBreakingNews();
    }, 30000);

    this.startStream();

    document.addEventListener(
      "fullscreenchange",
      this.handleFullscreenChange
    );

    document.addEventListener(
      "webkitfullscreenchange",
      this.handleFullscreenChange
    );

    document.addEventListener(
      "mozfullscreenchange",
      this.handleFullscreenChange
    );

    document.addEventListener(
      "msfullscreenchange",
      this.handleFullscreenChange
    );
  },

  beforeUnmount() {
    this.destroyPlayer();

    if (this.dateTimer) {
      clearInterval(this.dateTimer);
      this.dateTimer = null;
    }

    if (this.newsTimer) {
      clearInterval(this.newsTimer);
      this.newsTimer = null;
    }

    document.removeEventListener(
      "fullscreenchange",
      this.handleFullscreenChange
    );

    document.removeEventListener(
      "webkitfullscreenchange",
      this.handleFullscreenChange
    );

    document.removeEventListener(
      "mozfullscreenchange",
      this.handleFullscreenChange
    );

    document.removeEventListener(
      "msfullscreenchange",
      this.handleFullscreenChange
    );
  },

  methods: {
    /*
     * -----------------------------------------
     * ON VIDEO PLAY
     * -----------------------------------------
     */
    onVideoPlay() {
      this.streamOnline = true;
      
      // Unmute video after it starts playing
      const video = this.$refs.video;
      if (video) {
        video.muted = false;
      }
    },

    /*
     * -----------------------------------------
     * DATE / TIME
     * -----------------------------------------
     */
    updateDateTime() {
      const now = new Date();

      this.currentDate = now.toLocaleDateString("en-US", {
        month: "short",
        day: "2-digit",
        year: "numeric",
      });

      this.currentTime = now.toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: true,
      });
    },

    /*
     * -----------------------------------------
     * HLS STREAM
     * -----------------------------------------
     */
    startStream() {
      const video = this.$refs.video;

      if (!video) {
        console.error("Video element not found.");
        return;
      }

      /*
       * Native HLS support
       * Safari / iOS / some browsers
       */
      if (video.canPlayType("application/vnd.apple.mpegurl")) {
        console.log("Using native HLS support");
        video.src = this.streamUrl;

        video.addEventListener("loadedmetadata", () => {
          video.play().catch((error) => {
            console.warn(
              "Autoplay was prevented:",
              error
            );
          });
        });

        video.addEventListener("error", (e) => {
          console.error("Native HLS error:", e);
          // Fallback to HLS.js if native fails
          this.loadWithHlsJs(video);
        });

        return;
      }

      /*
       * hls.js for Firefox and other browsers
       */
      if (Hls.isSupported()) {
        console.log("Using HLS.js for playback");
        this.loadWithHlsJs(video);
        return;
      }

      /*
       * Browser does not support HLS
       */
      console.error(
        "This browser does not support HLS playback."
      );
      this.streamOnline = false;
    },

    /*
     * -----------------------------------------
     * LOAD WITH HLS.JS
     * -----------------------------------------
     */
    loadWithHlsJs(video) {
      this.hls = new Hls({
        enableWorker: true,
        lowLatencyMode: true,
        backBufferLength: 30,
        maxBufferLength: 30,
        maxMaxBufferLength: 60,
        liveSyncDurationCount: 3,
        liveMaxLatencyDurationCount: 6,
        manifestLoadingMaxRetry: 5,
        manifestLoadingRetryDelay: 1000,
        levelLoadingMaxRetry: 5,
        levelLoadingRetryDelay: 1000,
        fragLoadingMaxRetry: 5,
        fragLoadingRetryDelay: 1000,
        xhrSetup: (xhr, url) => {
          // CORS setup for better cross-origin support
          xhr.withCredentials = false;
          
          // Log attempts to help debug CORS issues
          console.log("Loading HLS resource:", url);
          
          // Set headers that might help with CORS
          xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
        }
      });

      this.hls.loadSource(this.streamUrl);
      this.hls.attachMedia(video);

      /*
       * Manifest loaded
       */
      this.hls.on(
        Hls.Events.MANIFEST_PARSED,
        () => {
          console.log("HLS manifest loaded");
          this.streamOnline = true;

          video
            .play()
            .then(() => {
              this.streamOnline = true;
            })
            .catch((error) => {
              console.warn(
                "Autoplay was prevented:",
                error
              );
            });
        }
      );

      /*
       * HLS errors
       */
      this.hls.on(
        Hls.Events.ERROR,
        (event, data) => {
          console.error(
            "HLS error:",
            data
          );

          if (!data.fatal) {
            return;
          }

          this.streamOnline = false;

          switch (data.type) {
            case Hls.ErrorTypes.NETWORK_ERROR:
              console.warn(
                "HLS network error. Restarting stream..."
              );

              setTimeout(() => {
                if (this.hls) {
                  this.hls.startLoad();
                }
              }, 2000);

              break;

            case Hls.ErrorTypes.MEDIA_ERROR:
              console.warn(
                "HLS media error. Recovering..."
              );

              if (this.hls) {
                this.hls.recoverMediaError();
              }

              break;

            default:
              console.warn(
                "Fatal HLS error. Restarting player..."
              );

              this.restartStream();

              break;
          }
        }
      );
    },

    /*
     * -----------------------------------------
     * RESTART STREAM
     * -----------------------------------------
     */
    restartStream() {
      this.destroyPlayer();

      setTimeout(() => {
        this.startStream();
      }, 2000);
    },

    /*
     * -----------------------------------------
     * DESTROY PLAYER
     * -----------------------------------------
     */
    destroyPlayer() {
      if (this.hls) {
        this.hls.destroy();
        this.hls = null;
      }

      const video = this.$refs.video;

      if (video) {
        video.pause();
        video.removeAttribute("src");
        video.load();
      }
    },

    /*
     * -----------------------------------------
     * BREAKING NEWS
     * -----------------------------------------
     */
    async loadBreakingNews() {
      try {
        const separator =
          this.newsUrl.includes("?")
            ? "&"
            : "?";

        const url =
          `${this.newsUrl}` +
          `${separator}t=${Date.now()}`;

        const response = await fetch(url, {
          cache: "no-store",
        });

        if (!response.ok) {
          throw new Error(
            `HTTP ${response.status}`
          );
        }

        const data = await response.json();

        // Extract only the headline/title from the database
        // Common field names: headline, title, message, content
        if (data.headline) {
          this.breakingNews = data.headline;
        } else if (data.title) {
          this.breakingNews = data.title;
        } else if (data.message) {
          this.breakingNews = data.message;
        } else if (data.content) {
          this.breakingNews = data.content;
        } else if (typeof data === 'string') {
          this.breakingNews = data.trim();
        } else {
          console.log("Breaking news data received:", data);
          this.breakingNews = "Welcome to m2M TV usa — Live IPTV Broadcasting";
        }
      } catch (error) {
        console.warn(
          "Unable to load breaking news:",
          error
        );

        /*
         * Keep previous breaking news
         * instead of clearing the banner.
         */
      }
    },

    /*
     * -----------------------------------------
     * FULLSCREEN
     * -----------------------------------------
     *
     * IMPORTANT:
     *
     * We fullscreen the VIDEO CONTAINER,
     * NOT the <video> element.
     *
     * Therefore:
     *
     * Date/time
     * Breaking news
     * Channel logo
     * Fullscreen button
     *
     * all remain visible.
     */
    async toggleFullscreen() {
      const container =
        this.$refs.videoContainer;

      if (!container) {
        return;
      }

      try {
        /*
         * Check current fullscreen state.
         */
        const fullscreenElement =
          document.fullscreenElement ||
          document.webkitFullscreenElement ||
          document.mozFullScreenElement ||
          document.msFullscreenElement;

        if (
          fullscreenElement === container
        ) {
          await this.exitFullscreen();
          return;
        }

        /*
         * Enter fullscreen.
         */
        if (
          container.requestFullscreen
        ) {
          await container.requestFullscreen();
        } else if (
          container.webkitRequestFullscreen
        ) {
          await container.webkitRequestFullscreen();
        } else if (
          container.mozRequestFullScreen
        ) {
          await container.mozRequestFullScreen();
        } else if (
          container.msRequestFullscreen
        ) {
          await container.msRequestFullscreen();
        } else {
          console.warn(
            "Fullscreen API is not supported on this device."
          );
          alert("Fullscreen is not supported on your device.");
        }
      } catch (error) {
        console.error(
          "Fullscreen error:",
          error
        );
        console.log("Error entering fullscreen:", error.message);
      }
    },

    /*
     * -----------------------------------------
     * EXIT FULLSCREEN
     * -----------------------------------------
     */
    async exitFullscreen() {
      try {
        if (
          document.exitFullscreen
        ) {
          await document.exitFullscreen();
        } else if (
          document.webkitExitFullscreen
        ) {
          await document.webkitExitFullscreen();
        } else if (
          document.mozCancelFullScreen
        ) {
          await document.mozCancelFullScreen();
        } else if (
          document.msExitFullscreen
        ) {
          await document.msExitFullscreen();
        }
      } catch (error) {
        console.error(
          "Exit fullscreen error:",
          error
        );
      }
    },

    /*
     * -----------------------------------------
     * FULLSCREEN STATE
     * -----------------------------------------
     */
    handleFullscreenChange() {
      const container =
        this.$refs.videoContainer;

      const fullscreenElement =
        document.fullscreenElement ||
        document.webkitFullscreenElement ||
        document.mozFullScreenElement ||
        document.msFullscreenElement;

      this.isFullscreen =
        fullscreenElement === container;
    },
  },
};
</script>

<style scoped>
/* ==================================================
   MAIN PAGE
   ================================================== */

.live-page {
  width: 100%;
  min-height: 120vh;
  background:
    radial-gradient(
      circle at top,
      #062b18 0%,
      #020805 45%,
      #000000 100%
    );

  color: #ffffff;

  padding: 20px;

  box-sizing: border-box;
}

/* ==================================================
   HEADER
   ================================================== */

.live-header {
  height: 50vh;
  width: 100%;
  max-width: 1200px;

  margin: 0 auto 30px auto;

  display: flex;
  justify-content: space-between;
  align-items: center;

  gap: 15px;

  flex-wrap: wrap;
}

.live-header h1 {
  margin: 0;

  color: #39ff14;

  font-size: clamp(
    22px,
    3vw,
    34px
  );

  font-weight: 800;

  text-shadow:
    0 0 5px #39ff14,
    0 0 15px #39ff14,
    0 0 30px rgba(
      57,
      255,
      20,
      0.5
    );
}

/* ==================================================
   STREAM STATUS
   ================================================== */

.stream-status {
  display: flex;
  align-items: center;

  gap: 8px;

  padding: 8px 14px;

  border: 1px solid #39ff14;

  border-radius: 20px;

  color: #39ff14;

  font-size: 14px;
  font-weight: 700;

  background: rgba(
    0,
    0,
    0,
    0.55
  );

  box-shadow:
    0 0 10px rgba(
      57,
      255,
      20,
      0.35
    );
}

.stream-status.offline {
  color: #ff3b3b;

  border-color: #ff3b3b;

  box-shadow:
    0 0 10px rgba(
      255,
      59,
      59,
      0.35
    );
}

.status-dot {
  width: 10px;
  height: 10px;

  border-radius: 50%;

  background: #39ff14;

  box-shadow:
    0 0 8px #39ff14;
}

.offline .status-dot {
  background: #ff3b3b;

  box-shadow:
    0 0 8px #ff3b3b;
}

/* ==================================================
   VIDEO CONTAINER
   ================================================== */

.video-container {
  position: relative;

  width: 100%;
  max-width: 1200px;

  margin: 0 auto;

  aspect-ratio: 16 / 9;

  background: #000000;

  overflow: hidden;

  border-radius: 12px;

  border: 2px solid #39ff14;
  /* Pulls the player up. Adjust this value (-100px, -150px, etc.) 
     until the visual gap matches exactly 20px */
  margin-top: -160px; 
  box-shadow:
    0 0 10px #39ff14,
    0 0 30px rgba(
      57,
      255,
      20,
      0.35
    );

  isolation: isolate;
}

/* ==================================================
   VIDEO
   ================================================== */

.video-player {
  display: block;

  width: 100%;
  height: 100%;

  background: #000000;

  object-fit: contain;
  
}

/*
 * Hide native fullscreen button where
 * controlsList is supported.
 */
.video-player::-webkit-media-controls-fullscreen-button {
  display: none;
}

/* ==================================================
   DATE + TIME
   ================================================== */

.datetime-overlay {
  position: absolute;

  right: 18px;

  /*
   * Keep it above the breaking-news
   * banner and native video controls.
   */
  bottom: 98px;

  z-index: 10;

  pointer-events: none;

  text-align: right;

  user-select: none;
background-color: rgba(233, 13, 200, 0.4); /* Black color with 40% opacity */

  text-shadow:
    0 2px 4px #000000,
    0 0 8px #000000;
    
}

.date-text {
  font-size: clamp(
    13px,
    1.8vw,
    21px
  );

  font-weight: 700;

  color: #ffffff;

  line-height: 1.2;
}

.time-text {
  margin-top: 3px;

  font-size: clamp(
    18px,
    2.8vw,
    32px
  );

  font-weight: 800;

  color: #030b01;

  line-height: 1.1;

}

/* ==================================================
   BREAKING NEWS
   ================================================== */

.breaking-news-overlay {
  position: absolute;

  left: 15px;
  right: 15px;

  bottom: 50px;

  height: 40px;

  z-index: 11;

  display: flex;
  align-items: stretch;

  overflow: hidden;

  border-radius: 5px;

  pointer-events: none;

  background: rgba(165, 233, 238, 0.504);

  border: 1px solid
    rgba(
      255,
      255,
      255,
      0.25
    );

  box-shadow:
    0 0 10px rgba(
      0,
      0,
      0,
      0.8
    );
}

/* ==================================================
   BREAKING LABEL
   ================================================== */

.breaking-label {
  flex-shrink: 0;

  display: flex;
  align-items: center;

  padding: 0 14px;

  background: #f42a1f;

  color: #edf5f7;

  font-size: clamp(
    11px,
    1.3vw,
    15px
  );

  font-weight: 900;

  letter-spacing: 0.5px;

  white-space: nowrap;

  box-shadow:
    0 0 10px
      rgba(237, 14, 14, 0.5);
}

/* ==================================================
   BREAKING CONTENT
   ================================================== */

.breaking-content {
  position: relative;

  flex: 1;

  overflow: hidden;

  display: flex;

  align-items: center;
}

.breaking-marquee {
  display: inline-block;
  white-space: nowrap;
  padding-left: 100%;
  font-size: clamp(13px, 1.6vw, 18px);
  color: #010000;
  font-weight: 900; /* Changed from 'bold' (700) to '900' for maximum thickness */
  animation: breakingNewsScroll 25s linear infinite;
  text-shadow: 0 0 5px rgba(255, 255, 255, 0.5);
}

@keyframes breakingNewsScroll {
  from {
    transform: translateX(0);
  }

  to {
    transform: translateX(-100%);
  }
}

/* ==================================================
   CUSTOM FULLSCREEN BUTTON
   ================================================== */

.fullscreen-button {
  position: absolute;

  top: 15px;
  right: 15px;

  z-index: 20;

  display: flex;
  align-items: center;

  gap: 7px;

  padding: 8px 13px;

  border: 1px solid
    rgba(
      57,
      255,
      20,
      0.8
    );

  border-radius: 5px;

  background: rgba(
    0,
    0,
    0,
    0.78
  );

  color: #ff1427;

  font-size: 13px;

  font-weight: 700;

  cursor: pointer;

  pointer-events: auto;

  touch-action: manipulation;

  -webkit-user-select: none;
  user-select: none;

  -webkit-tap-highlight-color: transparent;

  backdrop-filter: blur(4px);

  box-shadow:
    0 0 8px rgba(
      57,
      255,
      20,
      0.35
    );

  transition:
    background 0.2s ease,
    color 0.2s ease,
    box-shadow 0.2s ease,
    transform 0.2s ease;
}

.fullscreen-button:hover {
  background: #eb4ddc;

  color: #000000;

  box-shadow:
    0 0 15px #39ff14;

  transform: scale(1.04);
}

.fullscreen-button:active {
  transform: scale(0.97);
}

.fullscreen-icon {
  font-size: 18px;

  line-height: 1;
}

.fullscreen-text {
  white-space: nowrap;
}

/* ==================================================
   CHANNEL LOGO
   ================================================== */

.channel-logo {
  position: absolute;

  top: 30px;
  left: 15px;

  z-index: 10;

  padding: 0;

  border-radius: 4px;

  background: transparent;

  pointer-events: none;
}

.logo-image {
  width: clamp(60px, 10vw, 120px);
  height: auto;
  max-height: 80px;
  object-fit: contain;
  display: block;
  filter: drop-shadow(0 2px 4px rgba(0, 0, 0, 0.5));
}

/* ==================================================
   FULLSCREEN
   ==================================================
   
   IMPORTANT:
   The container itself becomes fullscreen.
   Therefore all overlays remain children
   of the fullscreen element.
   ================================================== */

.video-container:fullscreen {
  width: 100vw;
  height: 100vh;

  max-width: none;

  aspect-ratio: auto;

  margin: 0;

  border: none;

  border-radius: 0;

  box-shadow: none;

  background: #000000;
}

.video-container:fullscreen
  .video-player {
  width: 100%;
  height: 100%;

  aspect-ratio: auto;

  object-fit: contain;
}

/* WebKit fullscreen support */

.video-container:-webkit-full-screen {
  width: 100vw;
  height: 100vh;

  max-width: none;

  aspect-ratio: auto;
  margin-bottom: -45px;

  margin: 0;

  border: none;

  border-radius: 0;

  box-shadow: none;

  background: #000000;
}

.video-container:-webkit-full-screen
  .video-player {
  width: 100%;
  height: 100%;

  object-fit: contain;
}

/* ==================================================
   INFORMATION SECTION
   ================================================== */

.live-info {
  width: 100%;
  max-width: 1200px;

  margin: 20px auto 0 auto;

  padding: 18px;

  box-sizing: border-box;

  border-left: 3px solid #39ff14;

  background: rgba(
    0,
    0,
    0,
    0.45
  );
}

.live-info h2 {
  margin: 0 0 6px 0;

  color: #39ff14;

  font-size: 22px;
}

.live-info p {
  margin: 0;

  color: #cccccc;

  font-size: 15px;

  line-height: 1.6;
}

/* ==================================================
   MOBILE
   ================================================== */

@media (max-width: 768px) {
  .live-page {
    padding: 10px;
  }

  .live-header {
    margin-bottom: 12px;
  }

  .live-header h1 {
    font-size: 21px;
  }

  .stream-status {
    font-size: 12px;

    padding: 6px 10px;
  }

  .video-container {
    border-radius: 7px;
  }

  .datetime-overlay {
    right: 10px;

    bottom: 92px;
  }

  .breaking-news-overlay {
    left: 8px;
    right: 8px;

    bottom: 45px;
    height: 34px;
  }

  .breaking-label {
    padding: 0 8px;

    font-size: 10px;
  }

  .breaking-marquee {
    font-size: 12px;

    animation-duration: 20s;
  }

  .fullscreen-button {
    top: 8px;
    right: 8px;

    padding: 10px 12px;

    min-width: 44px;
    min-height: 44px;
  }

  .fullscreen-text {
    display: none;
  }

  .channel-logo {
    top: 55px;
    left: 8px;
  }

  .logo-image {
    width: clamp(40px, 8vw, 80px);
    max-height: 60px;
  }

  .live-info {
    margin-top: 12px;

    padding: 12px;
  }
}

/* ==================================================
   VERY SMALL SCREENS
   ================================================== */

@media (max-width: 480px) {
  .datetime-overlay {
    right: 8px;

    bottom: 82px;
  }

  .date-text {
    font-size: 11px;
  }

  .time-text {
    font-size: 16px;
  }

  .breaking-news-overlay {
    left: 5px;
    right: 5px;

    bottom: 40px;

    height: 30px;
  }

  .breaking-label {
    padding: 0 6px;

    font-size: 9px;
  }

  .breaking-marquee {
    font-size: 10px;
  }
}
</style>