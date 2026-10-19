<template>

  <div class="live-page">

    <!-- ==================================================

         HEADER

         ================================================== -->

    <div class="live-header">

      <div class="live-header-copy">
        <h1>m2M TV USA — Live Bangla News & Television</h1>

        <p>m2M TV USA brings Bangla news, stories, and television programming  

          <br>from New York City to audiences across the United States and around the world. বিশ্বজুড়ে বাংলা। আমাদের গর্ব</p>
        <div
          class="stream-status"
          :class="{ offline: !streamOnline }"
        >
          <span class="status-dot"></span>
          {{ streamOnline ? "Stream Online" : "Stream Offline" }}
        </div>
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
     NEWS & VIDEOS
     ================================================== -->

<section
  class="news-video-section"
  id="news-videos"
>

  <!-- SECTION HEADER -->

  <div class="news-section-header">

    <div>
      <span class="news-section-label">
        m2M TV USA
      </span>

      <h2>
        News &amp; Videos
      </h2>
    </div>

    <div class="news-section-line"></div>

  </div>


  <!-- LOADING -->

  <div
    v-if="videosLoading"
    class="news-loading"
  >
    Loading news and videos...
  </div>


  <!-- ERROR -->

  <div
    v-else-if="videosError"
    class="news-error"
  >
    {{ videosError }}
  </div>


  <!-- NEWS + TOP STORIES -->

  <div
    v-else-if="allVideos.length"
    class="news-layout"
  >

    <!-- ==========================================
         LEFT — NEWS & VIDEOS
         ========================================== -->

    <div class="video-area">

      <div class="news-video-grid">

        <article
          v-for="video in allVideos"
          :key="video.id"
          class="news-video-card"
          @click="openYoutubeVideo(video)"
        >

          <!-- THUMBNAIL -->

          <div class="news-thumbnail">

            <img
              :src="youtubeThumbnail(video.youtubeId)"
              :alt="video.title"
              loading="lazy"
              @error="handleThumbnailError"
            />

            <div class="thumbnail-overlay">
              <span class="youtube-play">
                ▶
              </span>
            </div>

            <div class="video-badge">
              VIDEO
            </div>

          </div>


          <!-- CARD CONTENT -->

          <div class="news-card-content">

            <div class="news-card-category">
              NEWS
            </div>

            <h3>
              {{ video.title }}
            </h3>

          </div>

        </article>

      </div>

    </div>


    <!-- ==========================================
         RIGHT — TOP STORIES
         ========================================== -->

    <aside
      id="top-stories"
      class="top-stories"
    >

      <div class="top-stories-header">
        Top Stories
      </div>


      <div class="top-stories-list">

        <button
          v-for="(story, index) in topStories"
          :key="`top-story-${story.id || index}`"
          type="button"
          class="top-story"
          :class="{ 'is-selected': selectedTopStoryKey === topStoryKey(story, index) }"
          @click="selectTopStory(story, index)"
        >

          <span class="top-story-number">
            {{ index + 1 }}
          </span>

          <span class="top-story-title">
            {{ story.title }}
          </span>

        </button>

      </div>

    </aside>

  </div>


  <!-- NO CONTENT -->

  <div
    v-else
    class="news-error"
  >
    No news videos are available right now.
  </div>

</section>

    <!-- ==================================================
         YOUTUBE VIDEO MODAL
         ================================================== -->
    <div v-if="selectedYoutubeVideo" class="youtube-modal" @click.self="closeYoutubeVideo">
      <div class="youtube-modal-content">
        <button type="button" class="youtube-close" aria-label="Close video" @click="closeYoutubeVideo">×</button>

        <div class="youtube-player-wrapper">
          <iframe
            :src="youtubeEmbedUrl"
            title="m2M TV USA YouTube Video"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen
          ></iframe>
        </div>

        <div class="youtube-modal-title">
          <div class="modal-category">m2M TV USA • NEWS &amp; VIDEOS</div>
          <h3>{{ selectedYoutubeVideo.title }}</h3>
          <a :href="youtubeWatchUrl(selectedYoutubeVideo.youtubeId)" target="_blank" rel="noopener noreferrer" class="youtube-direct-link">
            Watch directly on YouTube ↗
          </a>
        </div>
      </div>
    </div>

    <br>
    </br>    
    <br>
    </br>    
    <br>
    </br>
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
      <br>
      </br>
<!-- <section class="address" :style="{ backgroundColor: 'red' }"> 
  Address: 3714 73rd St. Suite 204, Jackson Heights, NY 11372, USA<br> 
    
</section> -->
  <section class="location-map" :style="{ backgroundColor: 'skyblue' }">
  <h2>Find Us</h2>

  <p class="address" >
    37-14 73rd St #204<br>
    Jackson Heights, NY 11372
  </p>

  <div class="map-container">
    <iframe
      src="https://www.google.com/maps?q=37-14+73rd+St+%23204,+Jackson+Heights,+NY+11372&output=embed"
      width="100%"
      height="450"
      style="border:0;"
      allowfullscreen=""
      loading="lazy"
      referrerpolicy="no-referrer-when-downgrade">
    </iframe>
  </div>
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

      // News & Videos
      videos: [],
      videosLoading: true,
      videosError: "",
      selectedYoutubeVideo: null,
      selectedTopStoryKey: null,
      liveWasPlayingBeforeNews: false,

      // 1. Maintain address fields as raw component state
      street: '3714 73rd St. Suite 204',
      cityStateZip: 'Jackson Heights, NY 11372',
      country: 'USA'

    };

  },



  /* ==================================================

     MOUNTED

     ================================================== */

  computed: {
          allVideos() {
            /*
            * The API returns seasons containing episodes.
            * Flatten everything into one News & Videos feed.
            *
            * Reverse the API order so the newest/last-added
            * database video appears first.
            */
            const videos = [];

            this.videos.forEach(season => {
              if (
                season.episodes &&
                Array.isArray(season.episodes)
              ) {
                season.episodes.forEach(video => {
                  videos.push({
                    ...video,
                    seasonName: season.name
                  });
                });
              }
            });

            return videos.slice().reverse();
          },

          topStories() {
            /*
            * Same logic as TestingFromPage.vue:
            * newest 8 videos become Top Stories.
            */
            return this.allVideos.slice(0, 8);
          },

              youtubeEmbedUrl() {
                if (!this.selectedYoutubeVideo) return "";
                const videoId = this.getYoutubeVideoId(this.selectedYoutubeVideo.youtubeId);
                if (!videoId) return "";
                return `https://www.youtube.com/embed/${videoId}?autoplay=1&rel=0`;
              },

              
            },

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
    this.loadVideos();

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



    /* ==================================================
       LOAD DATABASE VIDEOS
       ================================================== */
    async loadVideos() {
      this.videosLoading = true;
      this.videosError = "";

      try {
        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons?t=" + Date.now(),
          { cache: "no-store" }
        );

        if (!response.ok) throw new Error(`HTTP ${response.status}`);

        const data = await response.json();
        if (!Array.isArray(data)) {
          throw new Error("Invalid video data received from server.");
        }

        this.videos = data;
      } catch (error) {
        console.error("Unable to load database videos:", error);
        this.videosError = "Unable to load news videos right now.";
      } finally {
        this.videosLoading = false;
      }
    },

    topStoryKey(story, index) {
      return story.id ? `id:${story.id}` : `index:${index}`;
    },

    selectTopStory(story, index) {
      this.selectedTopStoryKey = this.topStoryKey(story, index);
      this.openYoutubeVideo(story);
    },

    getYoutubeVideoId(value) {
      if (!value) return "";
      const text = String(value).trim();
      const urlMatch = text.match(
        /(?:youtube\.com\/(?:watch\?v=|shorts\/|embed\/)|youtu\.be\/)([^?&/]+)/i
      );
      if (urlMatch && urlMatch[1]) return urlMatch[1];
      return text.split("?")[0].split("&")[0].trim();
    },

    youtubeThumbnail(value) {
      const videoId = this.getYoutubeVideoId(value);
      if (!videoId) return "";
      return `https://i.ytimg.com/vi/${videoId}/hqdefault.jpg`;
    },

    youtubeWatchUrl(value) {
      const videoId = this.getYoutubeVideoId(value);
      if (!videoId) return "#";
      return `https://www.youtube.com/watch?v=${videoId}`;
    },

    openYoutubeVideo(video) {

        if (!video) return;

        const videoId = this.getYoutubeVideoId(video.youtubeId);

        if (!videoId) return;

        // Pause Live TV BEFORE opening the YouTube video
        this.pauseLiveForNews();

        this.selectedYoutubeVideo = {
            ...video,
            youtubeId: videoId
        };

        document.body.classList.add("youtube-modal-open");
    },

    closeYoutubeVideo() {

    this.selectedYoutubeVideo = null;

    document.body.classList.remove("youtube-modal-open");

        // Resume Live TV if it was playing before
        // the News & Videos video was opened.
        this.resumeLiveAfterNews();
    },

    handleThumbnailError(event) {
      event.target.style.opacity = "0";
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

        //==================================================
            //  NEWS & VIDEOS — PAUSE LIVE TV WHILE PLAYING
            //==================================================

            pauseLiveForNews() {

            const liveVideo = this.$refs.video;

            if (!liveVideo) {
                return;
            }

            // Remember whether Live TV was actually playing
            this.liveWasPlayingBeforeNews = !liveVideo.paused;

            // Pause Live TV
            if (!liveVideo.paused) {
                liveVideo.pause();

                console.log("Live TV paused for News & Videos.");
            }
            },

            resumeLiveAfterNews() {

            const liveVideo = this.$refs.video;

            if (!liveVideo) {
                return;
            }

            // Only resume if Live TV was playing before
            // the News & Videos video was opened.
            if (this.liveWasPlayingBeforeNews) {

                liveVideo.play()
                .then(() => {
                    console.log("Live TV resumed after News & Videos.");
                })
                .catch((error) => {
                    console.warn(
                    "Live TV could not resume:",
                    error
                    );
                });
            }

        // Reset the state
        this.liveWasPlayingBeforeNews = false;
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

gap: 0px;

flex-wrap: wrap;

height: auto;


}

.live-header-copy {

flex: 1 1 100%;

min-width: 0;
position: relative;
padding: 25px 18px 65px;
box-sizing: border-box;
border-radius: 10px;
background:
  linear-gradient(rgba(0, 0, 0, 0.58), rgba(0, 0, 0, 0.58)),
  url("~@/assets/m2m_tv_usa_zoom_.gif") center 15% / cover no-repeat;

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

position: absolute;

right: 18px;

bottom: 14px;

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

*-webkit-user-select*: none;

user-select: none;

*-webkit-tap-highlight-color*: transparent;

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

  .live-header-copy {

flex: 1 1 100%;

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

*-webkit-user-select*: none;

*-webkit-tap-highlight-color*: transparent;

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








/* ==================================================
   NEWS & VIDEOS
   ================================================== */
.news-video-section { 
    width: 100%; 
    max-width: 1200px; 
    margin: 45px auto 0; 
}
.news-section-header { 
    display: flex; align-items: flex-end; gap: 20px; margin-bottom: 22px; 
}
.news-section-label { display: block; margin-bottom: 5px; color: #14dcff; font-size: 12px; font-weight: 800; letter-spacing: 1.8px; text-transform: uppercase; }
.news-section-header h2 { margin: 0; color: #fff; font-size: clamp(28px, 4vw, 42px); line-height: 1; font-weight: 850; }
.news-section-line { flex: 1; height: 2px; margin-bottom: 5px; background: linear-gradient(90deg, rgba(20,220,255,.8), rgba(20,220,255,0)); }
.news-layout {
  display: grid;
  grid-template-columns: minmax(0, 2fr) minmax(0, 1fr);
  align-items: start;
  gap: 24px;
  padding: 16px;
  border-radius: 8px;
  background: #fff;
}
.video-area {
  min-width: 0;
  padding: 16px;
  border-radius: 8px;
  background: #fff;
}
.news-video-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 16px 12px;
}
.top-stories {
  min-width: 0;
  overflow: hidden;
  border: 1px solid rgba(20,220,255,.2);
  border-radius: 8px;
  background: #fff;
}
.top-stories-header {
  padding: 14px 16px;
  background: #d32f2f;
  color: #fff;
  font-size: 18px;
  font-weight: 800;
}
.top-stories-list { padding: 4px 16px; }
.top-story {
  display: flex;
  width: 100%;
  gap: 12px;
  align-items: flex-start;
  padding: 13px 0;
  border: 0;
  border-bottom: 1px solid #e5e7eb;
  background: #fff;
  color: #111;
  cursor: pointer;
  text-align: left;
}
.top-story:last-child { border-bottom: 0; }
.top-story:hover,
.top-story:focus-visible,
.top-story.is-selected { background: #d9f0ff; }
.top-story-number {
  flex: 0 0 25px;
  color: #d32f2f;
  font-size: 18px;
  font-weight: 800;
  line-height: 1.2;
  text-align: center;
}
.top-story-title { font-size: 14px; line-height: 1.4; transition: color .2s ease; }
.news-video-card { 
    min-width: 0; 
    overflow: hidden; 
    cursor: pointer; 
    background: #fff; 
    color: #111; 
    border: 1px solid rgba(255,255,255,.12); border-radius: 
    8px; box-shadow: 0 8px 24px rgba(0,0,0,.32); 
    transition: transform .2s ease, box-shadow .2s ease; 
}
.news-video-card:hover { 
    transform: translateY(-5px); 
    box-shadow: 0 14px 32px rgba(0,0,0,.48); 
}
.news-thumbnail { 
    position: relative; width: 100%; 
    aspect-ratio: 16/9; 
    overflow: hidden; 
    background: linear-gradient(135deg,#10252c,#020405); 
}
.news-thumbnail img { 
    display: block; width: 100%; 
    height: 100%; object-fit: 
    cover; transition: transform .25s ease, opacity .2s ease; 
}
.news-video-card:hover .news-thumbnail img { transform: scale(1.04); }
.thumbnail-overlay { position: absolute; inset: 0; display: flex; align-items: center; justify-content: center; background: rgba(0,0,0,.08); }
.news-video-card:hover .thumbnail-overlay { background: rgba(0,0,0,.24); }
.youtube-play { 
  display: flex; 
  align-items: 
  center; justify-content: 
  center; width: 25px; 
  height: 25px; 
  padding-left: 3px; 
  border-radius: 50%; 
  background: rgba(220,0,0,.94); 
  color: #fff; 
  font-size: 15px; 
  box-shadow: 0 5px 18px rgba(0,0,0,.35); 
}
.video-badge {
   position: absolute; 
   left: 10px; 
   bottom: 10px; 
   padding: 5px 8px; 
   border-radius: 3px; 
   background: rgba(0,0,0,.82); 
   color: #fff; 
   font-size: 10px; 
   font-weight: 800; 
   letter-spacing: 1px; 
   display: none;
  }
.news-card-content {
  padding: 10px 10px 11px;
}

.news-card-content h3 {
  display: -webkit-box;
  min-height: 48px;
  margin: 0 0 10px;
  overflow: hidden;
  color: #101010;
  font-size: 14px;
  line-height: 1.25;
  font-weight: 750;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
}
.news-card-category { margin-bottom: 7px; color: #087ca1; font-size: 11px; font-weight: 800; letter-spacing: 1px; }


.news-loading,.news-error { padding: 35px 20px; border: 1px solid rgba(20,220,255,.2); border-radius: 8px; background: rgba(0,0,0,.35); color: #fff; text-align: center; }
.news-error { color: #ffb5b5; }
.youtube-modal { position: fixed; inset: 0; z-index: 1000000; display: flex; align-items: center; justify-content: center; padding: 25px; background: rgba(0,0,0,.84); backdrop-filter: blur(5px); }
.youtube-modal-content { position: relative; width: min(100%,980px); max-height: 95vh; overflow: auto; border-radius: 10px; background: #111; box-shadow: 0 20px 70px rgba(0,0,0,.65); }
.youtube-player-wrapper { position: relative; width: 100%; aspect-ratio: 16/9; background: #000; }
.youtube-player-wrapper iframe { display: block; width: 100%; height: 100%; border: 0; }
.youtube-close { position: absolute; top: 10px; right: 10px; z-index: 5; width: 40px; height: 40px; border: 0; border-radius: 50%; background: rgba(0,0,0,.78); color: #fff; cursor: pointer; font-size: 28px; line-height: 1; }
.youtube-close:hover { background: rgba(220,0,0,.9); }
.youtube-modal-title { padding: 18px 20px 22px; }
.modal-category { margin-bottom: 7px; color: #14dcff; font-size: 11px; font-weight: 800; letter-spacing: 1px; }
.youtube-modal-title h3 { margin: 0 0 14px; color: #fff; font-size: clamp(19px,3vw,27px); line-height: 1.3; }
.youtube-direct-link { color: #14dcff; font-size: 14px; font-weight: 700; text-decoration: none; }
.youtube-direct-link:hover { text-decoration: underline; }
.youtube-modal-open { overflow: hidden; }
@media (max-width:1050px) {
  .news-video-grid {
    grid-template-columns: repeat(3, minmax(0, 1fr));
    gap: 16px 12px;
  }
}
@media (max-width:760px) { .news-video-section { margin-top: 35px; } .news-layout { grid-template-columns: 1fr; gap: 24px; } .news-video-grid { grid-template-columns: repeat(2,minmax(0,1fr)); gap: 16px; } .news-card-content { padding: 12px; } .news-card-content h3 { min-height: 50px; font-size: 15px; } .watch-button { padding: 8px 9px; font-size: 12px; } }
@media (max-width:480px) { .news-section-header h2 { font-size: 28px; } .news-section-line { display: none; } .news-video-grid { grid-template-columns: 1fr; gap: 18px; } .news-card-content h3 { min-height: auto; font-size: 17px; } .youtube-modal { padding: 10px; } .youtube-modal-content { width: 100%; border-radius: 7px; } }

.location-map {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 40px 20px;
    text-align: center;
  }

  .location-map h2 {
    margin: 0 0 10px;
    font-size: 32px;
  }

  .address {
    margin: 0 0 25px;
    font-size: 18px;
    line-height: 1.6;
  }

  .map-container {
    width: 100%;
    overflow: hidden;
    border-radius: 16px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.12);
  }

  .map-container iframe {
    display: block;
    width: 100%;
    min-height: 450px;
  }

  @media (max-width: 600px) {
    .location-map {
      padding: 25px 15px;
    }

    .location-map h2 {
      font-size: 26px;
    }

    .address {
      font-size: 16px;
    }

    .map-container iframe {
      min-height: 350px;
    }
  }
</style>