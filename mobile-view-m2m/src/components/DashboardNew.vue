<template>
  <div class="news-page">

    <!-- =========================================
         PAGE HEADER
    ========================================== -->

    <header class="news-header">

      <div class="brand">
        <img
          src="@/assets/tv_logo.jpg"
          alt="m2M TV USA"
          class="brand-logo"
        />

        <div>
          <h1>m2M TV USA</h1>
          <p>News • Stories • Television</p>
        </div>
      </div>

      <nav class="news-nav">
        <button @click="scrollToSection('latest')">
          Latest
        </button>

        <button @click="scrollToSection('new-york')">
          New York
        </button>

        <button @click="scrollToSection('bangladesh')">
          Bangladesh
        </button>

        <button @click="scrollToSection('videos')">
          Videos
        </button>
      </nav>

    </header>


    <!-- =========================================
         LIVE TV
    ========================================== -->

    <section class="live-banner">

      <div class="live-title">
        <span class="live-dot"></span>
        LIVE
      </div>

      <div>
        <strong>m2M TV USA</strong>
        <span> — Live Television</span>
      </div>

    </section>


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

          <button class="watch-button">
            ▶ Watch Video
          </button>

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
         LATEST VIDEOS
    ========================================== -->

    <section
      id="videos"
      class="news-section"
    >

      <div class="section-heading">

        <h2>Latest News & Videos</h2>

        <span></span>

      </div>


      <div class="video-grid">

        <article
          v-for="episode in allEpisodes"
          :key="episode.id"
          class="video-card"
          @click="playEpisode(episode)"
        >

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


          <div class="card-content">

            <span class="video-category">
              m2M TV USA
            </span>

            <h3>
              {{ episode.title }}
            </h3>

            <p v-if="episode.description">
              {{ episode.description }}
            </p>

          </div>

        </article>

      </div>

    </section>


    <!-- =========================================
         SEASON SECTIONS
    ========================================== -->

    <section
      v-for="season in seasons"
      :key="season.id"
      :id="sectionId(season)"
      class="news-section season-section"
    >

      <div class="section-heading">

        <h2>
          {{ season.name }}
        </h2>

        <span></span>

      </div>


      <div class="video-grid">

        <article
          v-for="episode in season.episodes"
          :key="episode.id"
          class="video-card"
          @click="playEpisode(episode)"
        >

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

          <div class="card-content">

            <span class="video-category">
              {{ season.name }}
            </span>

            <h3>
              {{ episode.title }}
            </h3>

          </div>

        </article>

      </div>

    </section>


    <!-- =========================================
         NO CONTENT
    ========================================== -->

    <div
      v-if="!loading && allEpisodes.length === 0"
      class="no-content"
    >
      No videos are available right now.
    </div>


    <!-- =========================================
         FOOTER
    ========================================== -->

    <footer class="news-footer">

      <img
        src="@/assets/tv_logo.jpg"
        alt="m2M TV USA"
      />

      <p>
        © {{ currentYear }} m2M TV USA.
        News, stories and television programming
        from New York City, Bangladesh and around the world.
      </p>

    </footer>

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
     */

    allEpisodes() {

      const episodes = [];

      this.seasons.forEach(season => {

        if (
          season.episodes &&
          Array.isArray(season.episodes)
        ) {

          season.episodes.forEach(episode => {

            episodes.push({
              ...episode,

              seasonName: season.name

            });

          });

        }

      });

      return episodes;

    },


    /*
     * First video becomes the featured story.
     */

    featuredVideo() {

      return this.allEpisodes.length
        ? this.allEpisodes[0]
        : null;

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


    currentYear() {

      return new Date().getFullYear();

    }

  },


  methods: {

    /*
     * Get YouTube thumbnail automatically.
     */

    youtubeThumbnail(episode) {

      if (!episode || !episode.youtubeId) {

        return "";

      }

      return `https://i.ytimg.com/vi/${episode.youtubeId}/hqdefault.jpg`;

    },


    /*
     * Open YouTube video.
     */

    playEpisode(episode) {

      if (!episode || !episode.youtubeId) {

        return;

      }

      this.selectedVideo = episode;

      this.$nextTick(() => {

        const player =
          document.querySelector(".video-player-section");

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

      } finally {

        this.loading = false;

      }

    },


    /*
     * Convert season name to a section ID.
     */

    sectionId(season) {

      return String(season.name || "")
        .toLowerCase()
        .replace(/[^a-z0-9]+/g, "-")
        .replace(/^-|-$/g, "");

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

* {
  box-sizing: border-box;
}

.news-page {

  min-height: 100vh;

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

  border-bottom: 1px solid #dddddd;

  box-shadow:
    0 2px 8px rgba(0,0,0,.08);

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

  transition:
    transform .35s ease;

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

  color: white;

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

  font-size: clamp(25px, 3vw, 42px);

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

  color: white;

  font-weight: 800;

  cursor: pointer;

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
   SECTIONS
========================================= */

.news-section {

  max-width: 1300px;

  margin: 45px auto;

  padding: 0 20px;

}


.section-heading {

  display: flex;

  align-items: center;

  gap: 15px;

  margin-bottom: 20px;

}


.section-heading h2 {

  margin: 0;

  font-size: 27px;

  font-weight: 900;

}


.section-heading span {

  flex: 1;

  height: 3px;

  background: #111111;

}


/* =========================================
   VIDEO GRID
========================================= */

.video-grid {

  display: grid;

  grid-template-columns:
    repeat(4, minmax(0, 1fr));

  gap: 20px;

}


.video-card {

  background: #ffffff;

  cursor: pointer;

  transition:
    transform .2s ease,
    box-shadow .2s ease;

}


.video-card:hover {

  transform: translateY(-3px);

  box-shadow:
    0 7px 25px rgba(0,0,0,.12);

}


.thumbnail {

  position: relative;

  aspect-ratio: 16 / 9;

  overflow: hidden;

  background: #eeeeee;

}


.thumbnail img {

  width: 100%;

  height: 100%;

  object-fit: cover;

  display: block;

}


.thumbnail-play {

  position: absolute;

  left: 12px;

  bottom: 12px;

  width: 42px;

  height: 42px;

  border-radius: 50%;

  display: flex;

  align-items: center;

  justify-content: center;

  background: rgba(215,25,32,.95);

  color: #ffffff;

  font-size: 17px;

}


.card-content {

  padding: 10px 2px;

}


.video-category {

  display: block;

  margin-bottom: 5px;

  color: #d71920;

  font-size: 11px;

  font-weight: 900;

  text-transform: uppercase;

}


.card-content h3 {

  margin: 0;

  font-size: 17px;

  line-height: 1.25;

}


.card-content p {

  margin: 7px 0 0;

  color: #777777;

  font-size: 13px;

  line-height: 1.4;

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


/* =========================================
   MOBILE
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

    justify-content: flex-start;

  }


  .featured-card {

    grid-template-columns: 1fr;

  }


  .featured-content {

    padding: 22px;

  }


  .video-grid {

    grid-template-columns:
      repeat(2, minmax(0, 1fr));

    gap: 15px;

  }

}


@media (max-width: 600px) {

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


  .section-heading h2 {

    font-size: 22px;

  }


  .video-grid {

    grid-template-columns: 1fr;

    gap: 22px;

  }


  .card-content h3 {

    font-size: 18px;

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

}


@media (max-width: 380px) {

  .news-nav button {

    padding: 8px 9px;

    font-size: 12px;

  }

}

</style>

