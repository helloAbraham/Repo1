<template>
  <div class="latest-news-page">

    <!-- =========================================
         LATEST NEWS & VIDEOS
    ========================================== -->
    <section class="latest-news-section">

      <div class="section-heading">
        <h2>Latest News & Videos</h2>
        <span></span>
      </div>

      <!-- Loading -->
      <div v-if="loading" class="loading-message">
        Loading latest news...
      </div>

      <!-- Latest 6 News -->
      <div
        v-else-if="latestNews.length"
        class="video-grid"
      >

        <article
          v-for="episode in latestNews"
          :key="episode.id"
          class="video-card"
          @click="playEpisode(episode)"
        >

          <!-- YouTube Thumbnail -->
          <div class="thumbnail">

            <img
              :src="youtubeThumbnail(episode)"
              :alt="episode.title"
              loading="lazy"
              @error="handleThumbnailError"
            />

            <!-- Play Button -->
            <div class="thumbnail-play">
              ▶
            </div>

          </div>

          <!-- News Title -->
          <div class="card-content">

            <h3>
              {{ episode.title }}
            </h3>

          </div>

        </article>

      </div>

      <!-- No News -->
      <div
        v-else
        class="no-content"
      >
        No latest news is available right now.
      </div>

    </section>


    <!-- =========================================
         SELECTED YOUTUBE VIDEO PLAYER
    ========================================== -->
    <section
      v-if="selectedVideo"
      class="video-player-section"
    >

      <div class="player-header">

        <h2>
          {{ selectedVideo.title }}
        </h2>

        <button
          type="button"
          class="close-player"
          aria-label="Close video"
          @click="selectedVideo = null"
        >
          ✕
        </button>

      </div>


      <!-- Responsive YouTube Player -->
      <div class="youtube-player">

        <iframe
          :src="youtubeUrl"
          title="m2M TV USA Latest News"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen
        ></iframe>

      </div>

    </section>

  </div>
</template>


<script>
export default {

  name: "LatestNews",

  data() {
    return {

      /*
       * API seasons are still loaded in the background.
       * We do NOT display seasons on the page.
       */
      seasons: [],

      /*
       * Currently selected YouTube video
       */
      selectedVideo: null,

      /*
       * Loading state
       */
      loading: true

    };
  },


  computed: {

    /*
     * ============================================
     * GET ONLY THE LAST SIX NEWS VIDEOS
     * ============================================
     *
     * All episodes from all seasons are combined
     * into one list.
     *
     * Seasons/categories are NOT displayed.
     *
     * The last six returned by the API are selected
     * and reversed so newest appears first.
     */
    latestNews() {

      const episodes = [];

      this.seasons.forEach((season) => {

        if (
          !season ||
          !Array.isArray(season.episodes)
        ) {
          return;
        }

        season.episodes.forEach((episode) => {

          if (episode) {
            episodes.push(episode);
          }

        });

      });

      /*
       * Last 6 videos
       * Newest first
       */
      return episodes
        .slice(-6)
        .reverse();

    },


    /*
     * ============================================
     * YOUTUBE PLAYER URL
     * ============================================
     */
    youtubeUrl() {

      if (!this.selectedVideo) {
        return "";
      }

      const videoId =
        this.getYoutubeVideoId(
          this.selectedVideo.youtubeId
        );

      if (!videoId) {
        return "";
      }

      return (
        `https://www.youtube.com/embed/${videoId}` +
        `?autoplay=1&rel=0`
      );

    }

  },


  methods: {

    /*
     * ============================================
     * FETCH NEWS FROM API
     * ============================================
     */
    async fetchLatestNews() {

      this.loading = true;

      try {

        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons?t=" +
          Date.now(),
          {
            cache: "no-store"
          }
        );

        if (!response.ok) {

          throw new Error(
            `Failed to fetch latest news: HTTP ${response.status}`
          );

        }

        const data = await response.json();

        if (!Array.isArray(data)) {

          throw new Error(
            "Invalid news data received."
          );

        }

        this.seasons = data;

      } catch (error) {

        console.error(
          "Error fetching latest news:",
          error
        );

        this.seasons = [];

      } finally {

        this.loading = false;

      }

    },


    /*
     * ============================================
     * EXTRACT YOUTUBE VIDEO ID
     * ============================================
     *
     * Supports:
     *
     * gClEBlB5MsY
     *
     * gClEBlB5MsY?si=xxxx
     *
     * https://youtube.com/watch?v=gClEBlB5MsY
     *
     * https://youtu.be/gClEBlB5MsY
     *
     * https://youtube.com/shorts/gClEBlB5MsY
     *
     * https://youtube.com/embed/gClEBlB5MsY
     */
    getYoutubeVideoId(value) {

      if (!value) {
        return "";
      }

      const text =
        String(value).trim();


      /*
       * Full YouTube URL
       */
      const urlMatch = text.match(
        /(?:youtube\.com\/(?:watch\?v=|shorts\/|embed\/)|youtu\.be\/)([^?&/]+)/i
      );


      if (
        urlMatch &&
        urlMatch[1]
      ) {

        return urlMatch[1];

      }


      /*
       * Plain ID with query parameters
       */
      return text
        .split("?")[0]
        .split("&")[0]
        .trim();

    },


    /*
     * ============================================
     * CREATE YOUTUBE THUMBNAIL
     * ============================================
     */
    youtubeThumbnail(episode) {

      if (
        !episode ||
        !episode.youtubeId
      ) {

        return "";

      }

      const videoId =
        this.getYoutubeVideoId(
          episode.youtubeId
        );

      if (!videoId) {
        return "";
      }


      /*
       * YouTube thumbnail
       */
      return (
        `https://i.ytimg.com/vi/` +
        `${videoId}/hqdefault.jpg`
      );

    },


    /*
     * ============================================
     * PLAY SELECTED VIDEO
     * ============================================
     */
    playEpisode(episode) {

      if (
        !episode ||
        !episode.youtubeId
      ) {

        return;

      }

      this.selectedVideo = episode;


      /*
       * Scroll to player after selecting video
       */
      this.$nextTick(() => {

        const player =
          document.querySelector(
            ".video-player-section"
          );

        if (player) {

          player.scrollIntoView({
            behavior: "smooth",
            block: "start"
          });

        }

      });

    },


    /*
     * ============================================
     * THUMBNAIL ERROR
     * ============================================
     */
    handleThumbnailError(event) {

      event.target.style.visibility =
        "hidden";

    }

  },


  /*
   * ============================================
   * LOAD NEWS WHEN PAGE OPENS
   * ============================================
   */
  mounted() {

    this.fetchLatestNews();

  }

};
</script>


<style scoped>

/* =============================================
   MAIN PAGE
============================================= */

.latest-news-page {

  width: 100%;
  padding-top: 175px;
  min-height: 100vh;

  background: #ffffff;

  color: #111111;

  font-family:
    Arial,
    Helvetica,
    sans-serif;

  box-sizing: border-box;

}


/* =============================================
   LATEST NEWS SECTION
============================================= */

.latest-news-section {

  width: 100%;

  max-width: 1300px;

  margin: 0 auto;

  padding: 35px 20px 40px;

  box-sizing: border-box;

}


/* =============================================
   SECTION HEADING
============================================= */

.section-heading {

  display: flex;

  align-items: center;

  gap: 18px;

  margin-bottom: 25px;

}


.section-heading h2 {

  margin: 0;

  font-size: 30px;

  font-weight: 700;

  color: #111111;

  white-space: nowrap;

}


.section-heading span {

  height: 3px;

  flex: 1;

  background: #d71920;

  border-radius: 3px;

}


/* =============================================
   VIDEO GRID
============================================= */

.video-grid {

  display: grid;

  grid-template-columns:
    repeat(3, minmax(0, 1fr));

  gap: 24px;

}


/* =============================================
   VIDEO CARD
============================================= */

.video-card {

  background: #ffffff;

  border: 1px solid #e2e2e2;

  border-radius: 8px;

  overflow: hidden;

  cursor: pointer;

  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease;

}


.video-card:hover {

  transform: translateY(-4px);

  box-shadow:
    0 8px 25px
    rgba(0, 0, 0, 0.12);

}


/* =============================================
   THUMBNAIL
============================================= */

.thumbnail {

  position: relative;

  width: 100%;

  aspect-ratio: 16 / 9;

  background: #eeeeee;

  overflow: hidden;

}


.thumbnail img {

  display: block;

  width: 100%;

  height: 100%;

  object-fit: cover;

}


/* =============================================
   PLAY BUTTON
============================================= */

.thumbnail-play {

  position: absolute;

  left: 50%;

  top: 50%;

  transform:
    translate(-50%, -50%);

  width: 58px;

  height: 58px;

  border-radius: 50%;

  display: flex;

  align-items: center;

  justify-content: center;

  background: rgba(215, 25, 32, 0.95);

  color: #ffffff;

  font-size: 24px;

  padding-left: 3px;

  box-sizing: border-box;

  box-shadow:
    0 3px 12px
    rgba(0, 0, 0, 0.3);

}


/* =============================================
   CARD CONTENT
============================================= */

.card-content {

  padding: 16px 17px 18px;

}


.card-content h3 {

  margin: 0;

  font-size: 18px;

  line-height: 1.35;

  font-weight: 700;

  color: #111111;

}


/* =============================================
   VIDEO PLAYER SECTION
============================================= */

.video-player-section {

  width: 100%;

  max-width: 1100px;

  margin: 0 auto;

  padding: 0 20px 50px;

  box-sizing: border-box;

}


/* =============================================
   PLAYER HEADER
============================================= */

.player-header {

  display: flex;

  align-items: center;

  justify-content: space-between;

  gap: 20px;

  margin-bottom: 15px;

}


.player-header h2 {

  margin: 0;

  font-size: 24px;

  line-height: 1.35;

  font-weight: 700;

  color: #111111;

}


/* =============================================
   CLOSE BUTTON
============================================= */

.close-player {

  flex-shrink: 0;

  width: 40px;

  height: 40px;

  border: none;

  border-radius: 50%;

  background: #111111;

  color: #ffffff;

  font-size: 18px;

  cursor: pointer;

  display: flex;

  align-items: center;

  justify-content: center;

  transition:
    background 0.2s ease;

}


.close-player:hover {

  background: #d71920;

}


/* =============================================
   YOUTUBE PLAYER
============================================= */

.youtube-player {

  position: relative;

  width: 100%;

  aspect-ratio: 16 / 9;

  background: #000000;

  border-radius: 8px;

  overflow: hidden;

  box-shadow:
    0 8px 30px
    rgba(0, 0, 0, 0.18);

}


.youtube-player iframe {

  position: absolute;

  inset: 0;

  width: 100%;

  height: 100%;

  border: 0;

}


/* =============================================
   LOADING
============================================= */

.loading-message {

  padding: 50px 20px;

  text-align: center;

  color: #666666;

  font-size: 18px;

}


/* =============================================
   NO CONTENT
============================================= */

.no-content {

  padding: 50px 20px;

  text-align: center;

  color: #666666;

  font-size: 18px;

  border: 1px solid #eeeeee;

  border-radius: 8px;

}


/* =============================================
   TABLET
============================================= */

@media (max-width: 900px) {

  .video-grid {

    grid-template-columns:
      repeat(2, minmax(0, 1fr));

    gap: 20px;

  }

  .section-heading h2 {

    font-size: 27px;

  }

}


/* =============================================
   MOBILE
============================================= */

@media (max-width: 600px) {

  .latest-news-section {

    padding:
      25px 15px 35px;

  }


  .section-heading {

    gap: 12px;

    margin-bottom: 20px;

  }


  .section-heading h2 {

    font-size: 23px;

  }


  .section-heading span {

    height: 2px;

  }


  .video-grid {

    grid-template-columns: 1fr;

    gap: 18px;

  }


  .card-content {

    padding:
      14px 15px 16px;

  }


  .card-content h3 {

    font-size: 17px;

  }


  .thumbnail-play {

    width: 52px;

    height: 52px;

    font-size: 21px;

  }


  .video-player-section {

    padding:
      0 15px 40px;

  }


  .player-header {

    align-items: flex-start;

  }


  .player-header h2 {

    font-size: 20px;

  }


  .close-player {

    width: 36px;

    height: 36px;

    font-size: 16px;

  }

}


/* =============================================
   VERY SMALL PHONE
============================================= */

@media (max-width: 400px) {

  .section-heading h2 {

    font-size: 21px;

  }


  .card-content h3 {

    font-size: 16px;

  }

}

</style>