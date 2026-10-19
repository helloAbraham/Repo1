<template>
  <div class="news-page">

    <!-- =========================================
         PAGE HEADER
    ========================================== -->


    <!-- =========================================
         FEATURED VIDEO
    ========================================== -->
    <section
      v-if="featuredVideo"
      id="latest"
      class="featured-section"
    >

      <div
        class="featured-card"
        @click="playEpisode(featuredVideo)"
      >

        <div class="featured-image">

          <img
            :src="youtubeThumbnail(featuredVideo)"
            :alt="featuredVideo.title"
          />

          <div class="play-overlay">
            <span>▶</span>
          </div>

        </div>

        <div class="featured-content">

          <div class="story-label">
            FEATURED VIDEO
          </div>

          <h2>
            {{ featuredVideo.title }}
          </h2>

          <p v-if="featuredVideo.description">
            {{ featuredVideo.description }}
          </p>

         

        </div>

      </div>

    </section>


    <!-- =========================================
         SELECTED VIDEO PLAYER
    ========================================== -->
    <section
      v-if="selectedVideo"
      class="video-player-section"
    >

      <div class="player-header">

        <div>

          <span class="playing-label">
            NOW PLAYING
          </span>

          <h2>
            {{ selectedVideo.title }}
          </h2>

        </div>

        <button
          class="close-player"
          type="button"
          aria-label="Close video player"
          @click="selectedVideo = null"
        >
          ✕
        </button>

      </div>


      <div class="youtube-player">

        <iframe
          :src="youtubeUrl"
          title="m2M TV USA Video"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen
        ></iframe>

      </div>

    </section>


    <!-- =========================================
         NEWS & VIDEOS
    ========================================== -->
    <section
      id="videos"
      class="news-section"
    >

      <!-- SECTION TITLE -->
      <div class="section-heading">

        <div>
          <span class="section-label">
            m2M TV USA
          </span>

          <h2>
            News &amp; Videos
          </h2>
        </div>

        <span class="section-line"></span>

      </div>


      <!-- LOADING -->
      <div
        v-if="loading"
        class="loading-message"
      >
        Loading news and videos...
      </div>


      <!-- CONTENT -->
      <div
        v-else-if="allEpisodes.length"
        class="news-layout"
      >

        <!-- =====================================
             LEFT — VIDEO DATABASE
        ====================================== -->
        <div class="video-area">

          <div class="video-grid">

            <article
              v-for="episode in allEpisodes"
              :key="episode.id"
              class="video-card"
              @click="playEpisode(episode)"
            >

              <!-- THUMBNAIL -->
              <div class="thumbnail">

                <img
                  :src="youtubeThumbnail(episode)"
                  :alt="episode.title"
                  loading="lazy"
                />

                <div class="thumbnail-play">
                  ▶
                </div>

              </div>


              <!-- CARD CONTENT -->
              <div class="card-content">

                <h3>
                  {{ episode.title }}
                </h3>

                <button
                  class="card-watch-button"
                  type="button"
                  @click.stop="playEpisode(episode)"
                >
                  ▶ Watch
                </button>

              </div>

            </article>

          </div>

        </div>


        <!-- =====================================
             RIGHT — TOP STORIES
        ====================================== -->
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
              @click="playEpisode(story)"
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
        class="no-content"
      >
        No videos are available right now.
      </div>

    </section>


  </div>
</template>


<script>
export default {
  name: "DashboardNew",

  data() {
    return {
      seasons: [],
      selectedVideo: null,
      loading: true
    };
  },


  computed: {

    /*
     * Put all episodes from all seasons
     * into one news feed.
     *
     * Reverse the API order so the newest
     * database entries appear first.
     */
    allEpisodes() {
      const episodes = [];

      this.seasons.forEach((season) => {
        if (
          season.episodes &&
          Array.isArray(season.episodes)
        ) {
          season.episodes.forEach((episode) => {
            episodes.push({
              ...episode,
              seasonName: season.name
            });
          });
        }
      });

      return episodes.slice().reverse();
    },


    /*
     * First/newest video becomes the featured story.
     */
    featuredVideo() {
      return this.allEpisodes.length
        ? this.allEpisodes[0]
        : null;
    },


    /*
     * Top Stories uses the newest 8
     * database YouTube videos.
     */
    topStories() {
      return this.allEpisodes.slice(0, 8);
    },


    /*
     * YouTube player URL.
     */
    youtubeUrl() {
      if (!this.selectedVideo) {
        return "";
      }

      return `https://www.youtube.com/embed/${this.selectedVideo.youtubeId}?autoplay=1&rel=0`;
    },


    /*
     * Current year for footer.
     */
    currentYear() {
      return new Date().getFullYear();
    }

  },


  methods: {

    /*
     * Convert different YouTube URL formats
     * into a normal YouTube video ID.
     */
    normalizeYoutubeId(value) {
      if (!value) {
        return "";
      }

      const input = String(value).trim();

      /*
       * Already a normal YouTube ID.
       */
      if (
        !input.includes("/") &&
        !input.includes("?") &&
        !input.includes("=") &&
        input.length <= 20
      ) {
        return input;
      }

      try {
        let url = input;

        /*
         * Add protocol when necessary.
         */
        if (
          !url.startsWith("http://") &&
          !url.startsWith("https://")
        ) {
          url = `https://${url}`;
        }

        const parsed = new URL(url);

        /*
         * youtube.com/watch?v=VIDEO_ID
         */
        const queryId = parsed.searchParams.get("v");

        if (queryId) {
          return queryId;
        }

        /*
         * youtu.be/VIDEO_ID
         */
        if (
          parsed.hostname.includes("youtu.be")
        ) {
          return parsed.pathname
            .replace("/", "")
            .split("?")[0];
        }

        /*
         * youtube.com/shorts/VIDEO_ID
         */
        if (
          parsed.pathname.startsWith("/shorts/")
        ) {
          return parsed.pathname
            .split("/shorts/")[1]
            .split("/")[0];
        }

        /*
         * youtube.com/embed/VIDEO_ID
         */
        if (
          parsed.pathname.startsWith("/embed/")
        ) {
          return parsed.pathname
            .split("/embed/")[1]
            .split("/")[0];
        }

      } catch (error) {
        console.warn(
          "Could not parse YouTube URL:",
          value
        );
      }

      /*
       * Last fallback.
       */
      return input;
    },


    /*
     * Get YouTube thumbnail automatically.
     */
    youtubeThumbnail(episode) {
      if (!episode || !episode.youtubeId) {
        return "";
      }

      const youtubeId = this.normalizeYoutubeId(
        episode.youtubeId
      );

      if (!youtubeId) {
        return "";
      }

      return `https://i.ytimg.com/vi/${youtubeId}/hqdefault.jpg`;
    },


    /*
     * Open YouTube video.
     */
    playEpisode(episode) {
      if (!episode || !episode.youtubeId) {
        return;
      }

      const youtubeId = this.normalizeYoutubeId(
        episode.youtubeId
      );

      if (!youtubeId) {
        return;
      }

      this.selectedVideo = {
        ...episode,
        youtubeId
      };

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
     * Load your existing seasons API.
     */
    async fetchSeasons() {
      this.loading = true;

      try {
        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons"
        );

        if (!response.ok) {
          throw new Error(
            "Failed to fetch seasons"
          );
        }

        this.seasons = await response.json();

      } catch (error) {

        console.error(
          "Error fetching seasons:",
          error
        );

        this.seasons = [];

      } finally {
        this.loading = false;
      }
    },


    /*
     * Navigation.
     */
    scrollToSection(id) {
      const element =
        document.getElementById(id);

      if (element) {
        element.scrollIntoView({
          behavior: "smooth",
          block: "start"
        });
      }
    }

  },


  mounted() {
    this.fetchSeasons();
  }

};
</script>


<style scoped>

/* =========================================
   GLOBAL
========================================= */

* {
  box-sizing: border-box;
}

.news-page {
  min-height: 100vh;
  padding-top: 175px;
  background: #ffffff;
  color: #111111;

  font-family:
    Arial,
    Helvetica,
    sans-serif;
}


/* =========================================
   HEADER
========================================= */

.news-header {
  position: sticky;
  top: 0;
  z-index: 1000;

  width: 100%;
  min-height: 76px;

  padding: 10px 5%;

  display: flex;
  align-items: center;
  justify-content: space-between;

  gap: 20px;

  background: #ffffff;

  border-bottom:
    1px solid #dddddd;

  box-shadow:
    0 2px 8px rgba(0, 0, 0, 0.08);
}


.brand {
  display: flex;
  align-items: center;
  gap: 12px;
}


.brand-logo {
  width: 55px;
  height: 55px;

  object-fit: contain;
}


.brand h1 {
  margin: 0;

  font-size: 25px;
  font-weight: 900;
}


.brand p {
  margin: 2px 0 0;

  color: #777777;

  font-size: 12px;
}


/* =========================================
   NAVIGATION
========================================= */

.news-nav {
  display: flex;
  gap: 4px;

  overflow-x: auto;
}


.news-nav button {
  border: none;
  background: transparent;

  padding: 10px 13px;

  font-weight: 700;

  cursor: pointer;

  white-space: nowrap;
}


.news-nav button:hover {
  color: #d71920;
}


/* =========================================
   LIVE BAR
========================================= */

.live-banner {
  width: 100%;

  padding: 13px 5%;

  display: flex;
  align-items: center;

  gap: 10px;

  background: #111111;

  color: #ffffff;
}


.live-title {
  display: flex;
  align-items: center;

  gap: 7px;

  color: #ff2020;

  font-weight: 900;
}


.live-dot {
  width: 9px;
  height: 9px;

  border-radius: 50%;

  background: #ff2020;

  box-shadow:
    0 0 8px #ff2020;
}


/* =========================================
   FEATURED
========================================= */

.featured-section {
  max-width: 1300px;

  margin: 30px auto;

  padding: 0 20px;
}


.featured-card {
  display: grid;

  grid-template-columns:
    minmax(0, 1.7fr)
    minmax(300px, 1fr);

  background: #111111;

  color: #ffffff;

  cursor: pointer;
}


.featured-image {
  position: relative;

  aspect-ratio: 16 / 9;

  overflow: hidden;
}


.featured-image img {
  width: 100%;
  height: 100%;

  object-fit: cover;

  display: block;

  transition:
    transform 0.35s ease;
}


.featured-card:hover
.featured-image img {
  transform: scale(1.04);
}


.play-overlay {
  position: absolute;

  left: 20px;
  bottom: 20px;

  width: 58px;
  height: 58px;

  border-radius: 50%;

  display: flex;
  align-items: center;
  justify-content: center;

  background: #d71920;

  color: #ffffff;

  font-size: 24px;
}


.featured-content {
  padding: 35px;

  display: flex;

  flex-direction: column;

  justify-content: center;
}


.story-label {
  color: #ff3030;

  font-size: 12px;

  font-weight: 900;

  letter-spacing: 1px;

  margin-bottom: 12px;
}


.featured-content h2 {
  margin: 0 0 15px;

  font-size:
    clamp(25px, 3vw, 42px);

  line-height: 1.08;
}


.featured-content p {
  color: #cccccc;

  line-height: 1.6;
}


.watch-button {
  width: fit-content;

  margin-top: 15px;

  padding: 12px 20px;

  border: none;

  background: #d71920;

  color: #ffffff;

  font-weight: 800;

  cursor: pointer;
}


.watch-button:hover {
  background: #b9141a;
}


/* =========================================
   PLAYER
========================================= */

.video-player-section {
  max-width: 1100px;

  margin: 30px auto;

  padding: 0 20px;
}


.player-header {
  display: flex;

  justify-content: space-between;

  align-items: flex-start;

  margin-bottom: 12px;
}


.playing-label {
  color: #d71920;

  font-size: 12px;

  font-weight: 900;

  letter-spacing: 1px;
}


.player-header h2 {
  margin: 5px 0 0;

  font-size: 24px;
}


.close-player {
  width: 40px;
  height: 40px;

  border: none;

  border-radius: 50%;

  background: #eeeeee;

  cursor: pointer;

  font-size: 16px;
}


.close-player:hover {
  background: #dddddd;
}


.youtube-player {
  position: relative;

  width: 100%;

  aspect-ratio: 16 / 9;

  background: #000000;
}


.youtube-player iframe {
  width: 100%;
  height: 100%;

  border: 0;
}


/* =========================================
   NEWS SECTION
========================================= */

.news-section {
  max-width: 1400px;

  margin: 45px auto;

  padding: 0 20px;
}


.section-heading {
  display: flex;

  align-items: center;

  gap: 18px;

  margin-bottom: 22px;
}


.section-heading > div {
  flex-shrink: 0;
}


.section-label {
  display: block;

  margin-bottom: 3px;

  color: #d71920;

  font-size: 10px;

  font-weight: 900;

  letter-spacing: 1.2px;

  text-transform: uppercase;
}


.section-heading h2 {
  margin: 0;

  font-size: 30px;

  font-weight: 900;
}


.section-line {
  flex: 1;

  height: 3px;

  background: #111111;
}


/* =========================================
   MAIN NEWS LAYOUT
   LEFT = 2/3
   RIGHT = 1/3
========================================= */

.news-layout {
  display: grid;

  grid-template-columns:
    minmax(0, 2fr)
    minmax(300px, 1fr);

  gap: 22px;

  align-items: start;
}


.video-area {
  min-width: 0;
}


/* =========================================
   SMALLER VIDEO GRID
========================================= */

.video-grid {
  display: grid;

  grid-template-columns:
    repeat(4, minmax(0, 1fr));

  gap: 14px;
}


.video-card {
  min-width: 0;

  overflow: hidden;

  background: #ffffff;

  border:
    1px solid #e1e1e1;

  border-radius: 5px;

  cursor: pointer;

  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease,
    border-color 0.2s ease;
}


.video-card:hover {
  transform: translateY(-3px);

  border-color: #cccccc;

  box-shadow:
    0 7px 20px rgba(0, 0, 0, 0.12);
}


/* =========================================
   VIDEO THUMBNAIL
========================================= */

.thumbnail {
  position: relative;

  width: 100%;

  aspect-ratio: 16 / 9;

  overflow: hidden;

  background: #eeeeee;
}


.thumbnail img {
  width: 100%;
  height: 100%;

  object-fit: cover;

  display: block;

  transition:
    transform 0.3s ease;
}


.video-card:hover
.thumbnail img {
  transform: scale(1.04);
}


.thumbnail-play {
  position: absolute;

  left: 9px;
  bottom: 9px;

  width: 34px;
  height: 34px;

  border-radius: 50%;

  display: flex;
  align-items: center;
  justify-content: center;

  background:
    rgba(215, 25, 32, 0.95);

  color: #ffffff;

  font-size: 13px;

  box-shadow:
    0 2px 8px rgba(0, 0, 0, 0.25);
}


/* =========================================
   VIDEO CARD CONTENT
========================================= */

.card-content {
  padding: 9px 10px 10px;
}


.card-content h3 {
  margin: 0;

  font-size: 14px;

  line-height: 1.28;

  font-weight: 800;

  color: #111111;

  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 3;

  overflow: hidden;

  min-height: 54px;
}


/* =========================================
   SMALL WATCH BUTTON
========================================= */

.card-watch-button {
  margin-top: 9px;

  padding: 6px 9px;

  border: none;

  border-radius: 3px;

  background: #111111;

  color: #ffffff;

  font-size: 10px;

  font-weight: 800;

  cursor: pointer;
}


.card-watch-button:hover {
  background: #d71920;
}


/* =========================================
   TOP STORIES
========================================= */

.top-stories {
  overflow: hidden;

  border-radius: 5px;

  background: #f1f3f5;

  box-shadow:
    0 5px 20px rgba(0, 0, 0, 0.12);
}


.top-stories-header {
  padding: 18px 20px;

  background: #061a38;

  color: #ffffff;

  font-size: 27px;

  font-weight: 900;

  line-height: 1.1;

  border-bottom:
    1px solid #0d2a52;
}


.top-stories-list {
  width: 100%;
}


.top-story {
  width: 100%;

  min-height: 78px;

  padding: 15px 17px;

  display: grid;

  grid-template-columns: 28px minmax(0, 1fr);

  align-items: start;

  gap: 10px;

  border: none;

  border-bottom:
    1px solid #d2d6da;

  background: #f3f4f5;

  color: #111111;

  text-align: left;

  cursor: pointer;

  transition:
    background 0.2s ease,
    padding-left 0.2s ease;
}


.top-story:last-child {
  border-bottom: none;
}


.top-story:hover {
  background: #ffffff;

  padding-left: 20px;
}


.top-story-number {
  width: 25px;
  height: 25px;

  display: flex;

  align-items: center;

  justify-content: center;

  border-radius: 50%;

  background: #1b55a5;

  color: #ffffff;

  font-size: 11px;

  font-weight: 900;
}


.top-story-title {
  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 3;

  overflow: hidden;

  color: #151515;

  font-size: 14px;

  font-weight: 750;

  line-height: 1.35;
}


/* =========================================
   LOADING
========================================= */

.loading-message {
  min-height: 180px;

  display: flex;

  align-items: center;

  justify-content: center;

  color: #777777;

  font-size: 15px;
}


/* =========================================
   NO CONTENT
========================================= */

.no-content {
  padding: 50px 20px;

  text-align: center;

  color: #777777;

  font-size: 16px;
}


/* =========================================
   FOOTER
========================================= */

.news-footer {
  margin-top: 60px;

  padding: 40px 20px;

  display: flex;

  align-items: center;

  justify-content: center;

  gap: 15px;

  background: #111111;

  color: #bbbbbb;

  text-align: center;
}


.news-footer img {
  width: 55px;
  height: 55px;

  object-fit: contain;
}


.news-footer p {
  max-width: 700px;

  margin: 0;

  line-height: 1.5;
}


/* =========================================
   TABLET
========================================= */

@media (max-width: 1100px) {

  .news-layout {
    grid-template-columns:
      minmax(0, 1.7fr)
      minmax(270px, 1fr);

    gap: 18px;
  }


  .video-grid {
    grid-template-columns:
      repeat(3, minmax(0, 1fr));
  }


  .top-stories-header {
    font-size: 24px;
  }


  .top-story {
    padding: 13px 14px;
  }


  .top-story-title {
    font-size: 13px;
  }

}


/* =========================================
   TABLET / SMALL LAPTOP
========================================= */

@media (max-width: 900px) {

  .news-header {
    padding: 10px 15px;

    flex-direction: column;

    align-items: stretch;
  }


  .brand {
    justify-content: center;
  }


  .news-nav {
    justify-content: center;
  }


  .featured-card {
    grid-template-columns: 1fr;
  }


  .featured-content {
    padding: 22px;
  }


  /*
   * Keep videos smaller but give
   * the Top Stories panel full width
   * underneath.
   */
  .news-layout {
    grid-template-columns: 1fr;
  }


  .top-stories {
    order: 2;
  }


  .video-grid {
    grid-template-columns:
      repeat(3, minmax(0, 1fr));

    gap: 12px;
  }

}


/* =========================================
   MOBILE
========================================= */

@media (max-width: 650px) {

  .live-banner {
    padding: 11px 15px;

    font-size: 14px;
  }


  .featured-section {
    margin: 18px auto;

    padding: 0 10px;
  }


  .news-section {
    margin: 35px auto;

    padding: 0 10px;
  }


  .section-heading {
    gap: 10px;
  }


  .section-heading h2 {
    font-size: 23px;
  }


  .section-line {
    height: 2px;
  }


  .video-grid {
    grid-template-columns:
      repeat(2, minmax(0, 1fr));

    gap: 12px;
  }


  .card-content {
    padding: 8px;
  }


  .card-content h3 {
    font-size: 13px;

    min-height: 50px;
  }


  .card-watch-button {
    width: 100%;

    padding: 7px 5px;

    font-size: 10px;
  }


  .thumbnail-play {
    width: 30px;
    height: 30px;

    left: 7px;
    bottom: 7px;

    font-size: 11px;
  }


  .featured-content h2 {
    font-size: 27px;
  }


  .player-header h2 {
    font-size: 19px;
  }


  .brand h1 {
    font-size: 22px;
  }


  .top-stories-header {
    padding: 16px;

    font-size: 23px;
  }


  .top-story {
    min-height: 72px;

    padding: 13px 14px;

    grid-template-columns:
      26px minmax(0, 1fr);
  }


  .top-story-number {
    width: 24px;
    height: 24px;
  }


  .top-story-title {
    font-size: 14px;
  }


  .news-footer {
    flex-direction: column;
  }

}


/* =========================================
   SMALL PHONE
========================================= */

@media (max-width: 430px) {

  .news-nav {
    justify-content: flex-start;
  }


  .news-nav button {
    padding: 8px 9px;

    font-size: 12px;
  }


  .video-grid {
    grid-template-columns: 1fr;

    gap: 15px;
  }


  .card-content h3 {
    font-size: 15px;

    min-height: auto;

    -webkit-line-clamp: 3;
  }


  .thumbnail-play {
    width: 34px;
    height: 34px;

    font-size: 13px;
  }


  .card-watch-button {
    width: auto;

    padding: 7px 10px;
  }

  /* ==================================================
   NEWS & VIDEOS
   ================================================== */

.news-video-section {
  width: 100%;
  max-width: 1200px;
  margin: 35px auto 50px;
}


/* SECTION HEADER */

.news-section-header {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 22px;
}

.news-section-header > div:first-child {
  flex-shrink: 0;
}

.news-section-label {
  display: block;
  font-size: 13px;
  font-weight: 700;
  letter-spacing: 2px;
  color: #000;
  margin-bottom: 4px;
}

.news-section-header h2 {
  margin: 0;
  font-size: 30px;
  font-weight: 800;
  color: #000;
}

.news-section-line {
  flex: 1;
  height: 2px;
  background: #000;
}


/* ==================================================
   TWO-COLUMN LAYOUT
   LEFT = NEWS VIDEOS
   RIGHT = TOP STORIES
   ================================================== */

.news-layout {
  display: grid;
  grid-template-columns: minmax(0, 1fr) 330px;
  gap: 25px;
  align-items: start;
}


/* ==================================================
   LEFT VIDEO AREA
   ================================================== */

.video-area {
  min-width: 0;
}

.news-video-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 18px;
}


/* VIDEO CARD */

.news-video-card {
  background: #fff;
  border: 1px solid #000;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease;
}

.news-video-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.18);
}


/* THUMBNAIL */

.news-thumbnail {
  position: relative;
  width: 100%;
  aspect-ratio: 16 / 9;
  background: #000;
  overflow: hidden;
}

.news-thumbnail img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.thumbnail-overlay {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.08);
}

.youtube-play {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: rgba(255, 0, 0, 0.92);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  padding-left: 3px;
}

.video-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  padding: 4px 8px;
  background: #000;
  color: #fff;
  font-size: 10px;
  font-weight: 800;
  letter-spacing: 1px;
  border-radius: 3px;
}


/* CARD CONTENT */

.news-card-content {
  padding: 12px 14px 15px;
}

.news-card-category {
  font-size: 10px;
  font-weight: 800;
  letter-spacing: 1.5px;
  color: #d00000;
  margin-bottom: 5px;
}

.news-card-content h3 {
  margin: 0;
  color: #000;
  font-size: 16px;
  line-height: 1.35;
  font-weight: 700;
}


/* ==================================================
   RIGHT — TOP STORIES
   ================================================== */

.top-stories {
  width: 100%;
  background: #fff;
  border: 1px solid #000;
  border-radius: 8px;
  overflow: hidden;
  position: sticky;
  top: 190px;
}

.top-stories-header {
  background: #000;
  color: #fff;
  padding: 15px 18px;
  font-size: 22px;
  font-weight: 800;
  border-bottom: 3px solid #d00000;
}

.top-stories-list {
  display: flex;
  flex-direction: column;
}

.top-story {
  width: 100%;
  min-height: 70px;
  padding: 12px 14px;
  display: grid;
  grid-template-columns: 32px minmax(0, 1fr);
  gap: 12px;
  align-items: center;
  background: #fff;
  color: #000;
  border: 0;
  border-bottom: 1px solid #d0d0d0;
  text-align: left;
  cursor: pointer;
  transition: background 0.2s ease;
}

.top-story:last-child {
  border-bottom: 0;
}

.top-story:hover {
  background: #f2f2f2;
}

.top-story-number {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: #000;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  font-weight: 800;
}

.top-story-title {
  color: #000;
  font-size: 14px;
  line-height: 1.35;
  font-weight: 700;
}


/* ==================================================
   LOADING / ERROR
   ================================================== */

.news-loading,
.news-error {
  padding: 30px;
  text-align: center;
  color: #000;
  border: 1px solid #000;
  border-radius: 8px;
}


/* ==================================================
   TABLET
   ================================================== */

@media (max-width: 1000px) {

  .news-layout {
    grid-template-columns: minmax(0, 1fr) 280px;
    gap: 18px;
  }

  .news-video-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
    gap: 14px;
  }

  .top-stories-header {
    font-size: 20px;
  }

  .top-story-title {
    font-size: 13px;
  }
}


/* ==================================================
   MOBILE
   ================================================== */

@media (max-width: 768px) {

  .news-video-section {
    margin: 30px auto 40px;
    padding: 0 10px;
  }

  .news-section-header {
    gap: 10px;
  }

  .news-section-header h2 {
    font-size: 24px;
  }

  /*
   * On phones the Top Stories moves below
   * the News & Videos thumbnails.
   */

  .news-layout {
    grid-template-columns: 1fr;
    gap: 25px;
  }

  .video-grid,
  .news-video-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
    gap: 12px;
  }

  .top-stories {
    position: static;
    width: 100%;
  }

  .top-stories-header {
    font-size: 21px;
  }
}


/* ==================================================
   SMALL PHONE
   ================================================== */

@media (max-width: 480px) {

  .news-video-grid {
    grid-template-columns: 1fr;
  }

  .news-section-header h2 {
    font-size: 22px;
  }

  .news-card-content h3 {
    font-size: 14px;
  }

  .top-story {
    min-height: 64px;
  }

  .top-story-title {
    font-size: 13px;
  }
}
}

</style>