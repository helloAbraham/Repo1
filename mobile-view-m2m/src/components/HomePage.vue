<template>
  <div class="live-page">

    <!-- ==================================================
         HEADER
         ================================================== -->
    <div class="live-header">
      

      <h1>m2M TV USA — Live Bangla News & Television</h1>
      <p>m2M TV USA brings Bangla news, stories, and television programming  
        <br>from New York City to audiences across the United States and around the world.</p>
      <div
        class="stream-status"
        :class="{ offline: !streamOnline }"
      >
        <span class="status-dot"></span>

        {{ streamOnline ? "Stream Online" : "Stream Offline" }}
      </div>

    </div>


    <!-- ==================================================
         VIDEO CONTAINER
         ================================================== -->

    <div
      ref="videoContainer"
      class="video-container"
      @dblclick="toggleFullscreen"
    >

      <!-- ==================================================
           VIDEO
           ================================================== -->

      <video
        ref="video"
        class="video-player"
        controls
        controlslist="nofullscreen"
        autoplay
        playsinline
        webkit-playsinline
        @play="onVideoPlay"
        @playing="streamOnline = true"
        @error="streamOnline = false"
        @stalled="streamOnline = false"
        @waiting="streamOnline = false"
      ></video>


      <!-- ==================================================
           DATE + TIME
           ================================================== -->

      <div class="datetime-overlay">

        <div class="date-text">
          {{ currentDate }}
        </div>

        <div class="time-text">
          {{ currentTime }}
        </div>

      </div>

        <!-- ==============================================
            Play button for play with sound 
          ============================================== -->

          <button
        v-if="autoplayBlocked && !isMobile"
        class="live-tv-button"
        type="button"
        @click.stop="playLiveTV"
      >
        <span class="live-tv-icon">▶</span>
        <span>Live TV</span>
      </button>

      <!-- ==================================================
           BREAKING NEWS
           ================================================== -->

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


      <!-- ==================================================
           CUSTOM FULLSCREEN BUTTON
           ================================================== -->

      <button
  class="fullscreen-button"
  type="button"
  @click.stop="toggleFullscreen"
  :title="isFullscreen ? 'Exit Fullscreen' : 'Fullscreen'"
>
  <span class="fullscreen-icon">⛶</span>

  <span class="fullscreen-text">
    {{ isFullscreen ? "Exit Fullscreen" : "Fullscreen" }}
  </span>
</button>



      <!-- ==================================================
           CHANNEL LOGO
           ================================================== -->

      <div class="channel-logo">

        <img
          src="@/assets/tv_logo.jpg"
          alt="Channel Logo"
          class="logo-image"
        />

      </div>

    </div>


    <!-- ==================================================
         INFORMATION
         ================================================== -->

    <div class="live-info">

      <h2>
        Thanks, You are watching m2M TV usa.
      </h2>

      <p>
        A Bias-free news broadcasting service from m2M TV usa.
      </p>

    </div>


    <!-- ==================================================
         GLOW LINE
         ================================================== -->

    <div class="glow-line"></div>


    <!-- ==================================================
         ABOUT SECTION
         ================================================== -->

    <section class="about">

      <h2>
        About the News Media
      </h2>

      <p>

        <strong>m2M TV USA</strong>
        is a television news and media platform focused on real news,
        diverse perspectives, community stories, cultural insights,
        and untold stories from everyday people and special guests.

        We bring viewers informative coverage,
        meaningful conversations,
        and stories that connect communities
        across the USA and around the world.

      </p>

    </section>

  </div>
</template>


<script>

import Hls from "hls.js";


export default {

  name: "HomePage",


  /* ==================================================
     DATA
     ================================================== */

  data() {

    return {

      streamUrl:
        "https://m2musa.tv/live/stream.m3u8",

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

      isIOS: false,
      isIOSFullscreen: false,
      autoplayBlocked: false,
      isMobile: false,
    };

  },


  /* ==================================================
     MOUNTED
     ================================================== */

  mounted() {

      const video = this.$refs.video;

    

    // iPhone / iPad Safari
    // Chrome on iOS also uses WebKit
   
  
    this.isMobile = /Android|iPhone|iPad|iPod|Mobile/i.test(navigator.userAgent);
    if (!this.isMobile) {
      this.autoplayBlocked = true;
    }

    /* -----------------------------------------------
       DATE / TIME
       ----------------------------------------------- */

    this.updateDateTime();

    this.dateTimer = setInterval(() => {

      this.updateDateTime();

    }, 1000);


    /* -----------------------------------------------
       BREAKING NEWS
       ----------------------------------------------- */

    this.loadBreakingNews();

    this.newsTimer = setInterval(() => {

      this.loadBreakingNews();

    }, 30000);


    /* -----------------------------------------------
       START STREAM
       ----------------------------------------------- */

    this.startStream();


    /* -----------------------------------------------
       FULLSCREEN EVENTS
       ----------------------------------------------- */

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


    /* -----------------------------------------------
       AUTOPLAY FIX
       ----------------------------------------------- */

this.$nextTick(() => {

  const video = this.$refs.video;

  if (video) {

    video.muted = false;
    video.volume = 1.0;

    video.play().then(() => {

      this.streamOnline = true;

    }).catch((error) => {

      console.warn(
        "Autoplay with sound was blocked by the browser:",
        error
      );

      /*
       * Browser requires user interaction.
       * The user can press the native play button
       * or interact with the page to start audio.
       */
      });

    }

    });
 
    this.detectIOS();
  },


  /* ==================================================
     BEFORE UNMOUNT
     ================================================== */

  beforeUnmount() {
        const video = this.$refs.video;

    document.removeEventListener(
      "fullscreenchange",
      this.handleFullscreenChange
    );

    if (video) {
      video.removeEventListener(
        "webkitbeginfullscreen",
        this.handleVideoFullscreenStart
      );

      video.removeEventListener(
        "webkitendfullscreen",
        this.handleVideoFullscreenEnd
      );
    }

    this.destroyPlayer();


    /* -----------------------------------------------
       CLEAR DATE TIMER
       ----------------------------------------------- */

    if (this.dateTimer) {

      clearInterval(this.dateTimer);

      this.dateTimer = null;

    }


    /* -----------------------------------------------
       CLEAR NEWS TIMER
       ----------------------------------------------- */

    if (this.newsTimer) {

      clearInterval(this.newsTimer);

      this.newsTimer = null;

    }


    /* -----------------------------------------------
       REMOVE FULLSCREEN EVENTS
       ----------------------------------------------- */

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


  /* ==================================================
     METHODS
     ================================================== */

  methods: {

    /* ===============================================
       Try AutoPlay with sound
       =============================================== */
       async tryAutoplayWithSound() {
      const video = this.$refs.video;

      if (!video) return;

      video.autoplay = true;
      video.controls = true;

      // First attempt: autoplay WITH sound
      video.muted = false;
      video.defaultMuted = false;
      video.volume = 1.0;

        try {
          await video.play();

          console.log("Autoplay with sound succeeded.");

          this.streamOnline = true;
          this.autoplayBlocked = false;

          return;

        } catch (error) {

    console.warn(
      "Autoplay with sound was blocked:",
      error
    );

    /*
     * Desktop browsers commonly block autoplay
     * when audio is enabled.
     *
     * Show the Live TV button immediately.
     */
    if (!this.isMobile) {
      this.autoplayBlocked = true;
    }

    /*
     * Try muted autoplay.
     *
     * This allows the video itself to start even when
     * autoplay with sound is prohibited.
     */
    try {

      video.muted = true;
      video.defaultMuted = true;

      await video.play();

      console.log(
        "Muted autoplay started. Waiting for user to enable sound."
      );

      this.streamOnline = true;

    } catch (mutedError) {

        console.warn(
          "Muted autoplay was also blocked:",
          mutedError
        );

        this.streamOnline = false;

        if (!this.isMobile) {
          this.autoplayBlocked = true;
         }
        }
      }
    },



    async playLiveTV() {

        const video = this.$refs.video;

        if (!video) {
          console.error("Video element not found.");
          return;
        }

      try {

        video.muted = false;
        video.defaultMuted = false;
        video.volume = 1.0;

        await video.play();

        console.log("Live TV started with sound.");

        this.streamOnline = true;
        this.autoplayBlocked = false;

      } catch (error) {

        console.error(
          "Live TV could not start:",
          error
        );

        // Keep the button visible
        this.autoplayBlocked = true;
      }
    },



 // ==========================================
    // STANDARD FULLSCREEN CHANGE
    // Android / Desktop
    // ==========================================

    handleFullscreenChange() {
      this.isFullscreen = !!document.fullscreenElement;
    },

    // ==========================================
    // iOS ENTERED FULLSCREEN
    // ==========================================

    handleVideoFullscreenStart() {
      this.isFullscreen = true;
    },

    // ==========================================
    // iOS EXITED FULLSCREEN
    // ==========================================

    handleVideoFullscreenEnd() {
      this.isFullscreen = false;
    },

    // ==========================================
    // TOGGLE FULLSCREEN
    // ==========================================

    async toggleFullscreen() {
      const video = this.$refs.video;
      const container = this.$refs.videoContainer;

      if (!video || !container) {
        return;
    }

  /*
   * ==========================================
   * iPHONE / iPAD
   * ==========================================
   *
   * Do NOT use:
   *
   * video.webkitEnterFullscreen()
   *
   * because that creates Apple's native
   * video fullscreen and hides our overlays.
   *
   * Instead, make our container fixed/fullscreen.
   */

  if (this.isIOS) {

  const entering = !this.isIOSFullscreen;

  this.isIOSFullscreen = entering;
  this.isFullscreen = entering;

    container.classList.toggle(
      "ios-fullscreen",
      entering
      );

  document.body.classList.toggle(
    "ios-player-fullscreen",
    entering
     );

   return;
  
  }


  /*
   * ==========================================
   * ANDROID / DESKTOP
   * ==========================================
   */

  try {

    /*
     * EXIT
     */

    if (
      document.fullscreenElement === container
    ) {

      await document.exitFullscreen();

      return;
    }


    /*
     * ENTER
     */

    if (
      container.requestFullscreen
    ) {

      await container.requestFullscreen();

      return;
    }


    /*
     * Older WebKit fallback
     */

    if (
      container.webkitRequestFullscreen
        ) {

      container.webkitRequestFullscreen();

      return;
       }


    console.warn(
      "Fullscreen is not supported on this device."
      );

      }

  catch (error) {

    console.error(
      "Fullscreen error:",
      error
        );

      }
    },


  detectIOS() {
    const ua = window.navigator.userAgent;

    this.isIOS =
      /iPhone|iPad|iPod/i.test(ua) ||
      (
        navigator.platform === "MacIntel" &&
        navigator.maxTouchPoints > 1
      );
  },


    /* ==================================================
       VIDEO PLAY
       ================================================== */

    onVideoPlay() {

      this.streamOnline = true;

      const video = this.$refs.video;

      if (video) {

        /*
         * Do not force unmute here.
         *
         * Browsers commonly block autoplay
         * when sound is enabled.
         */

        video.muted = false;
        video.volume = 1.0;

        }
      },


    /* ==================================================
       DATE / TIME
       ================================================== */

    updateDateTime() {

      const now = new Date();


      this.currentDate =
        now.toLocaleDateString(
          "en-US",
          {
            month: "short",
            day: "2-digit",
            year: "numeric",
          }
        );


      this.currentTime =
        now.toLocaleTimeString(
          "en-US",
          {
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit",
            hour12: true,
          }
        );

     },


    /* ==================================================
       START HLS STREAM
       ================================================== */

    startStream() {

      const video = this.$refs.video;


      if (!video) {

        console.error(
          "Video element not found."
        );

        return;

      }


      /* -----------------------------------------------
         NATIVE HLS
         Safari / iOS
         ----------------------------------------------- */

      if (
        video.canPlayType(
          "application/vnd.apple.mpegurl"
        )
      ) {

        console.log(
          "Using native HLS support"
        );


        video.src = this.streamUrl;


        video.addEventListener(
          "loadedmetadata",
          () => {

            video
              .play()
              .catch((error) => {

                console.warn(
                  "Autoplay was prevented:",
                  error
                );

              });

          }
        );


        video.addEventListener(
          "error",
          (e) => {

            console.error(
              "Native HLS error:",
              e
            );

            this.loadWithHlsJs(video);

          }
        );


        return;

      }


      /* -----------------------------------------------
         HLS.JS
         ----------------------------------------------- */

      if (Hls.isSupported()) {

        console.log(
          "Using HLS.js for playback"
        );

        this.loadWithHlsJs(video);

        return;

      }


      /* -----------------------------------------------
         NOT SUPPORTED
         ----------------------------------------------- */

      console.error(
        "This browser does not support HLS playback."
      );

      this.streamOnline = false;

    },


    /* ==================================================
       LOAD WITH HLS.JS
       ================================================== */

    loadWithHlsJs(video) {

      /*
       * Destroy an existing instance first.
       */

      if (this.hls) {

        this.hls.destroy();

        this.hls = null;

      }


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

          xhr.withCredentials = false;

          console.log(
            "Loading HLS resource:",
            url
          );

        }

      });


      /* -----------------------------------------------
         LOAD SOURCE
         ----------------------------------------------- */

      this.hls.loadSource(
        this.streamUrl
      );


      this.hls.attachMedia(
        video
      );


      /* -----------------------------------------------
         MANIFEST LOADED
         ----------------------------------------------- */

         
    this.hls.on(Hls.Events.MANIFEST_PARSED, () => {

          console.log("HLS manifest loaded");

          this.streamOnline = true;

          this.tryAutoplayWithSound();

      });

      


      /* -----------------------------------------------
         HLS ERRORS
         ----------------------------------------------- */

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


            /* -----------------------------------------
               NETWORK ERROR
               ----------------------------------------- */

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


            /* -----------------------------------------
               MEDIA ERROR
               ----------------------------------------- */

            case Hls.ErrorTypes.MEDIA_ERROR:

              console.warn(
                "HLS media error. Recovering..."
              );


              if (this.hls) {

                this.hls.recoverMediaError();

              }

              break;


            /* -----------------------------------------
               OTHER FATAL ERROR
               ----------------------------------------- */

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


    /* ==================================================
       RESTART STREAM
       ================================================== */

    restartStream() {

      this.destroyPlayer();


      setTimeout(() => {

        this.startStream();

      }, 2000);

    },


    /* ==================================================
       DESTROY PLAYER
       ================================================== */

    destroyPlayer() {

      if (this.hls) {

        this.hls.destroy();

        this.hls = null;

      }


      const video = this.$refs.video;


      if (video) {

        video.pause();

        video.removeAttribute(
          "src"
        );

        video.load();

      }

    },


    /* ==================================================
       BREAKING NEWS
       ================================================== */

    async loadBreakingNews() {

      try {

        const separator =
          this.newsUrl.includes("?")
            ? "&"
            : "?";


        const url =
          `${this.newsUrl}` +
          `${separator}t=${Date.now()}`;


        const response =
          await fetch(
            url,
            {
              cache: "no-store",
            }
          );


        if (!response.ok) {

          throw new Error(
            `HTTP ${response.status}`
          );

        }


        const data =
          await response.json();


        /*
         * Extract headline.
         */

        if (data.headline) {

          this.breakingNews =
            data.headline;

        }

        else if (data.title) {

          this.breakingNews =
            data.title;

        }

        else if (data.message) {

          this.breakingNews =
            data.message;

        }

        else if (data.content) {

          this.breakingNews =
            data.content;

        }

        else if (
          typeof data === "string"
        ) {

          this.breakingNews =
            data.trim();

        }

        else {

          console.log(
            "Breaking news data received:",
            data
          );

          this.breakingNews =
            "Welcome to m2M TV usa — Live IPTV Broadcasting";

        }

      }

      catch (error) {

        console.warn(
          "Unable to load breaking news:",
          error
        );

        /*
         * Keep previous breaking news.
         */

      }

    },




    


    /* ==================================================
       FULLSCREEN STATE
       ================================================== */

    handleFullscreenChange() {

      const container =
        this.$refs.videoContainer;


      const fullscreenElement =
        document.fullscreenElement ||
        document.webkitFullscreenElement ||
        document.mozFullScreenElement ||
        document.msFullscreenElement;


      this.isFullscreen =
        fullscreenElement ===
        container;

        },


  }

  };

</script>


<style scoped>
/* ==================================================
   MAIN PAGE
   ================================================== */

.live-page {
  width: 100%;
  min-height: 100vh;

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
  padding-top: 150px;
}


/* ==================================================
   HEADER
   ================================================== */

.live-header {
  width: 100%;
  max-width: 1200px;

  margin: 0 auto 20px auto;

  display: flex;
  justify-content: space-between;
  align-items: center;

  gap: 15px;
  flex-wrap: wrap;

  height: auto;
}

.live-header h1 {
  margin: 0;

  color: #14dcff;

  font-size: clamp(22px, 3vw, 34px);

  font-weight: 800;

  text-shadow:
    0 0 5px #7214ff,
    0 0 15px #eae8ee,
    0 0 30px rgba(57, 255, 20, 0.5);
}


/* ==================================================
   STREAM STATUS
   ================================================== */

.stream-status {
  display: flex;
  align-items: center;

  gap: 8px;

  padding: 8px 14px;

  border: 1px solid blue;

  border-radius: 20px;

  color: #6214ff;

  font-size: 14px;
  font-weight: 700;

  background: rgba(0, 0, 0, 0.55);

  box-shadow:
    0 0 10px rgba(57, 255, 20, 0.35);
}

.stream-status.offline {
  color: #ff3b3b;

  border-color: #ff3b3b;

  box-shadow:
    0 0 10px rgba(255, 59, 59, 0.35);
}

.status-dot {
  width: 10px;
  height: 10px;

  border-radius: 50%;

  background: #39ff14;

  box-shadow:
    0 0 8px #0753e1;
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

  border: 2px solid #10b7ef;

  box-shadow:
    0 0 10px #1404ed,
    0 0 30px rgba(57, 255, 20, 0.35);

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

.video-player::-webkit-media-controls-fullscreen-button {
  display: none;
}


/* ==================================================
   DATE + TIME
   ================================================== */

.datetime-overlay {
  position: absolute;

  right: 18px;

  bottom: 72px;

  z-index: 35;

  pointer-events: none;

  text-align: right;

  user-select: none;

  padding: 5px 9px;

  border-radius: 4px;

 background-color: rgba(233, 13, 200, 0.4); /* Black color with 40% opacity */


  text-shadow:
    0 2px 4px #000000,
    0 0 8px #000000;
}

.date-text {
  font-size: clamp(13px, 1.8vw, 21px);

  font-weight: 700;

  color: #ffffff;

  line-height: 1.2;
}

.time-text {
  margin-top: 3px;

  font-size: clamp(13px, 1.8vw, 21px);

  font-weight: 800;

  color: #ffffff;

  line-height: 1.1;
}


/* ==================================================
   BREAKING NEWS
   ================================================== */

.breaking-news-overlay {
  position: absolute;

  left: 18px;
  right: 18px;

  bottom: 18px;

  height: 42px;

  z-index: 40;

  display: flex;
  align-items: stretch;

  overflow: hidden;

  border-radius: 5px;

  pointer-events: none;


  border: 1px solid rgba(255, 255, 255, 0.25);

   background: 
   rgba(165, 233, 238, 0.504);
}


/* ==================================================
   BREAKING LABEL
   ================================================== */

.breaking-label {
  flex-shrink: 0;

  display: flex;
  align-items: center;

  padding: 0 16px;

  background: #f42a1f;

  color: #ffffff;

  font-size: clamp(11px, 1.3vw, 15px);

  font-weight: 900;

  letter-spacing: 0.5px;

  white-space: nowrap;

  box-shadow:
    0 0 10px rgba(237, 14, 14, 0.5);
}


/* ==================================================
   BREAKING CONTENT
   ================================================== */

.breaking-content {
  position: relative;

  flex: 1;

  min-width: 0;

  overflow: hidden;

  display: flex;

  align-items: center;
}

.breaking-marquee {
  display: inline-block;

  white-space: nowrap;

  padding-left: 100%;

  font-size: clamp(13px, 1.6vw, 18px);

  color: #0b0b0b;

  font-weight: 900;

  animation:
    breakingNewsScroll 25s linear infinite;

  text-shadow:
    0 0 5px rgba(0, 0, 0, 0.5);
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

  top: 18px;
  right: 18px;

  z-index: 50;

  display: flex;
  align-items: center;

  gap: 7px;

  padding: 8px 13px;

  border: 1px solid rgba(57, 255, 20, 0.8);

  border-radius: 5px;

  background: rgba(0, 0, 0, 0.78);

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
    0 0 8px rgba(57, 255, 20, 0.35);

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
   TOP-LEFT OF VIDEO
   ================================================== */

.channel-logo {
  position: absolute;

  top: 18px;
  left: 18px;

  z-index: 30;

  padding: 0;

  border-radius: 6px;

  background: transparent;

  pointer-events: none;

  perspective: 1000px;
}

.logo-image {
  display: block;

  width: clamp(65px, 8vw, 115px);

  height: auto;

  max-height: 75px;

  object-fit: contain;

  transform-style: preserve-3d;

  backface-visibility: hidden;

  transform-origin: center center;

  filter:
    drop-shadow(0 2px 4px rgba(0, 0, 0, 0.7))
    drop-shadow(0 0 8px rgba(0, 0, 0, 0.45));

  animation:
    tvLogoFlip 15s ease-in-out infinite;
}


/* ==================================================
   LOGO ANIMATION
   ================================================== */

@keyframes tvLogoFlip {

  0% {
    opacity: 1;

    transform:
      perspective(1000px)
      rotateY(0deg)
      scale(1);
  }

  33.333% {
    opacity: 0;

    transform:
      perspective(1000px)
      rotateY(180deg)
      scale(0.95);
  }

  40% {
    opacity: 0.15;

    transform:
      perspective(1000px)
      rotateY(216deg)
      scale(0.96);
  }

  50% {
    opacity: 0.5;

    transform:
      perspective(1000px)
      rotateY(270deg)
      scale(0.98);
  }

  56.667% {
    opacity: 0.8;

    transform:
      perspective(1000px)
      rotateY(306deg)
      scale(1);
  }

  66.667% {
    opacity: 1;

    transform:
      perspective(1000px)
      rotateY(360deg)
      scale(1);
  }

  100% {
    opacity: 1;

    transform:
      perspective(1000px)
      rotateY(360deg)
      scale(1);
  }
}


/* ==================================================
   FULLSCREEN
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

.video-container:fullscreen .video-player {
  width: 100%;
  height: 100%;

  aspect-ratio: auto;

  object-fit: contain;
}


/* WebKit fullscreen */

.video-container:-webkit-full-screen {
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

.video-container:-webkit-full-screen .video-player {
  width: 100%;
  height: 100%;

  object-fit: contain;
}


/* ==================================================
   INFORMATION
   ================================================== */

.live-info {
  width: 100%;
  max-width: 1200px;

  margin: 20px auto 0 auto;

  padding: 18px;

  box-sizing: border-box;

  border-left: 3px solid #39ff14;

  background: rgba(0, 0, 0, 0.45);
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
    margin-top: 150px;
  }

  .live-header h1 {
    font-size: 21px;
  }

  .stream-status {
    font-size: 12px;

    padding: 6px 10px;
  }


  /* Video */

  .video-container {
    width: 100%;

    border-radius: 7px;

    margin: 0 auto;
  }


  /* Logo */

  .channel-logo {
    top: 10px;
    left: 10px;

    z-index: 30;
  }

  .logo-image {
    width: 55px;

    max-width: 18vw;

    max-height: 50px;
  }


  /* Fullscreen */

  .fullscreen-button {
    top: 8px;
    right: 8px;

    padding: 10px 12px;

    min-width: 44px;
    min-height: 44px;

    z-index: 50;
  }

  .fullscreen-text {
    display: none;
  }


  /* Date / Time */

  .datetime-overlay {
    right: 10px;

    bottom: 55px;

    padding: 3px 6px;

    z-index: 35;
  }

  .date-text {
    font-size: 11px;
  }

  .time-text {
    font-size: 11px;
  }


  /* Breaking News */

  .breaking-news-overlay {
    left: 7px;
    right: 7px;

    bottom: 7px;

    height: 34px;

    z-index: 40;
  }

  .breaking-label {
    padding: 0 8px;

    font-size: 9px;
  }

  .breaking-marquee {
    font-size: 11px;

    animation-duration: 20s;
  }


  /* Information */

  .live-info {
    margin-top: 12px;

    padding: 12px;
  }
}


/* ==================================================
   VERY SMALL MOBILE
   ================================================== */

@media (max-width: 480px) {

  .live-page {
    padding: 6px;
  }

  .channel-logo {
    top: 7px;
    left: 7px;
  }

  .logo-image {
    width: 45px;

    max-width: 17vw;

    max-height: 42px;
  }

  .fullscreen-button {
    top: 5px;
    right: 5px;

    min-width: 40px;
    min-height: 40px;

    padding: 8px;
  }

  .datetime-overlay {
    right: 7px;

    bottom: 45px;

    padding: 2px 5px;
  }

  .date-text {
    font-size: 9px;
  }

  .time-text {
    font-size: 9px;
  }

  .breaking-news-overlay {
    left: 5px;
    right: 5px;

    bottom: 5px;

    height: 28px;
  }

  .breaking-label {
    padding: 0 6px;

    font-size: 8px;
  }

  .breaking-marquee {
    font-size: 9px;

    animation-duration: 18s;
  }
}

/* ==================================================
   iPHONE FULLSCREEN-LIKE PLAYER
   ================================================== */

.ios-player-fullscreen {
  overflow: hidden;
}


/*
 * Vue scoped CSS cannot normally style body,
 * so use :global() here.
 */

:global(body.ios-player-fullscreen) {
  overflow: hidden;
}


/*
 * iPhone fullscreen player
 */

.ios-fullscreen {
  position: fixed !important;

  top: 0 !important;
  left: 0 !important;
  right: 0 !important;
  bottom: 0 !important;

  width: 100vw !important;
  height: 100dvh !important;

  max-width: none !important;

  margin: 0 !important;

  aspect-ratio: auto !important;

  border: none !important;
  border-radius: 0 !important;

  background: #000000 !important;

  z-index: 999999 !important;
}


/*
 * Video stays inside our container
 */

.ios-fullscreen .video-player {
  width: 100% !important;
  height: 100% !important;

  object-fit: contain !important;
}


/*
 * Keep logo visible
 */

.ios-fullscreen .channel-logo {
  z-index: 1000000 !important;
}


/*
 * Keep date/time visible
 */

.ios-fullscreen .datetime-overlay {
  z-index: 1000001 !important;

  bottom: 70px;
}


/*
 * Keep breaking news visible
 */

.ios-fullscreen .breaking-news-overlay {
  z-index: 1000002 !important;

  bottom: 15px;
}


/*
 * Keep fullscreen button visible
 */

.ios-fullscreen .fullscreen-button {
  z-index: 1000003 !important;
}

/* ==================================================
   CENTER LIVE TV BUTTON
   ================================================== */

.live-tv-button {
  position: absolute;

  top: 50%;
  left: 50%;

  transform: translate(-50%, -50%);

  z-index: 9999;

  display: flex;
  align-items: center;
  justify-content: center;

  gap: 7px;

  width: 125px;
  height: 44px;

  padding: 0;

  background: rgba(210, 0, 0, 0.95);

  color: #ffffff;

  border: 1px solid rgba(255, 255, 255, 0.9);

  border-radius: 7px;

  font-size: 15px;
  font-weight: 700;

  cursor: pointer;

  pointer-events: auto;

  box-shadow:
    0 0 10px rgba(60, 160, 218, 0.75),
    0 0 20px rgba(0, 162, 255, 0.35);

  backdrop-filter: blur(4px);

  transition:
    transform 0.2s ease,
    background 0.2s ease,
    box-shadow 0.2s ease;

  touch-action: manipulation;

  user-select: none;
  -webkit-user-select: none;

  -webkit-tap-highlight-color: transparent;
}


/* ==================================================
   HOVER
   ================================================== */

.live-tv-button:hover {
  background: #ff0000;

  transform:
    translate(-50%, -50%)
    scale(1.05);

  box-shadow:
    0 0 14px rgba(12, 12, 230, 0.9),
    0 0 28px rgba(9, 31, 230, 0.5);
}


/* ==================================================
   CLICK
   ================================================== */

.live-tv-button:active {
  transform:
    translate(-50%, -50%)
    scale(0.96);
}


/* ==================================================
   PLAY ICON
   ================================================== */

.live-tv-icon {
  font-size: 16px;

  line-height: 1;
}

/* ==================================================
   MOBILE
   ================================================== */

/* ==================================================
   MOBILE EMBEDDED PLAYER
   ================================================== */

@media (max-width: 768px) {

  .video-container {
    position: relative;

    width: 100%;
    height: auto;

    aspect-ratio: 16 / 9;

    max-width: 100%;

    margin: 0 auto;

    overflow: hidden;

    border-radius: 7px;
  }

  .video-player {
    width: 100%;
    height: 100%;

    object-fit: contain;
  }

  /* Hide desktop Live TV button on mobile */
  .live-tv-button {
    display: none !important;
  }


  /* ==================================================
     iOS CUSTOM FULLSCREEN
     ================================================== */

  .video-container.ios-fullscreen {
    position: fixed !important;

    top: 0 !important;
    left: 0 !important;
    right: 0 !important;
    bottom: 0 !important;

    width: 100vw !important;
    height: 100dvh !important;

    max-width: none !important;

    margin: 0 !important;

    aspect-ratio: auto !important;

    border: none !important;
    border-radius: 0 !important;

    background: #000000 !important;

    z-index: 999999 !important;

    overflow: hidden !important;
  }

  .video-container.ios-fullscreen .video-player {
    width: 100% !important;
    height: 100% !important;

    object-fit: contain !important;
  }

  .video-container.ios-fullscreen .channel-logo {
    z-index: 1000000 !important;
  }

  .video-container.ios-fullscreen .datetime-overlay {
    z-index: 1000001 !important;

    bottom: 70px !important;
  }

  .video-container.ios-fullscreen .breaking-news-overlay {
    z-index: 1000002 !important;

    bottom: 15px !important;
  }

  .video-container.ios-fullscreen .fullscreen-button {
    z-index: 1000003 !important;

    display: flex !important;
  }

}


/* ==================================================
   VERY SMALL MOBILE
   ================================================== */

@media (max-width: 480px) {

  .video-container {
    width: 100%;

    aspect-ratio: 16 / 9;

    height: auto;
  }

  .video-container.ios-fullscreen {
    width: 100vw !important;
    height: 100dvh !important;

    aspect-ratio: auto !important;
  }

}




</style>