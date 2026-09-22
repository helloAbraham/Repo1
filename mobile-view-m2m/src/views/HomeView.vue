<template>
  <div class="home">
 
   <section class="hero">
  <h1 class="title">m2M TV USA</h1>

  <p class="tagline">
     Real News. Raw Opinions. Stories Without Filters.
  </p>

  <a
    v-if="latestEpisodeLink"
    :href="latestEpisodeLink"
    target="_blank"
    rel="noopener"
    class="primary-btn"
  >
    Watch Latest Episode
  </a>
</section>



    <section class="innerBoxSize">
      <PhotoSlideShow />
      <br>
    </section>
    <!-- Glow animated line in horizontal-->
    <div class="glow-line"></div>

    <!-- About Section -->
    <section class="about">
      <h2>About the News Media</h2>
      <p>
        <strong>m2M TV USA</strong> is a television news and media platform focused on real news, 
        diverse perspectives, community stories, cultural insights, 
        and untold stories from everyday people and special guests. 
        We bring viewers informative coverage, meaningful conversations, 
        and stories that connect communities across the USA and around the world.
      </p>
    </section>

    <!-- Featured Episodes -->
    <section class="episodes">
      <h2>Featured Episodes</h2>

      <div class="episode-list">
        <div
          class="episode-card"
          v-for="episode in episodes"
          :key="episode.id"
        >
          <h3>{{ episode.title }}</h3>
          <p>{{ episode.description }}</p>
          <span class="date">{{ episode.date }}</span>
        </div>
      </div>

    </section>

    <div>
  <CardCarousel />


  </div>
      <div class="glow-line"></div>
    <!-- Call to Action -->
    <section class="cta">
      <h2>Be a Part of the Conversation</h2>
      <p>
        Want to share your story or join the discussion?
      </p>
      <br/>

      <button class="secondary-btn" @click="contactUs">
         Contact Us
      </button>

    </section>
  </div>
</template>

<script>
import CardCarousel from '@/components/CardCarousel.vue';
import PhotoSlideShow from '@/components/PhotoSlideShow.vue';

import axios from 'axios';
export default {
  name: "HomePage",
  data() {
    return {
      latestEpisodeLink: "",
     // headline: "",
     // latestEpisodeLink: "https://www.youtube.com/@abrahamparker8684/videos",
      // Comes from database / API
      //latestEpisodeLink: "https://api.abrahamparker.com/api/content/1",
       youtubeVideoId: "", // dynamic video ID from API
        youtubeEmbedUrl: "", // full iframe URL from API or YouTube
      episodes: [
        {
          id: 1,
          title: "Life Abroad: The Untold Truth",
          description: "A deep conversation about migrant life and identity.",
          //date: "Feb 14, 2026"
        },
        {
          id: 2,
          title: "Success Without Shortcuts",
          description: "Stories of patience, failure, and persistence.",
          //date: "Feb 22, 2026"
        },
        {
          id: 3,
          title: "Culture, Change & Conflict",
          description: "How modern life is reshaping traditions.",
          //date: "Feb 28, 2026"
        }
      ]
    };
  },
  components: {
    PhotoSlideShow,
    CardCarousel
  },

    mounted() {

     // Fetch latest episode link from API
     //fetch("http://localhost:8082/api/content/1")
    fetch("https://api.abrahamparker.com/api/content/1")
      .then(response => response.json())
      .then(data => {
        this.latestEpisodeLink = data.paragraph1;
      })
      .catch(error => {
        console.error("API error:", error);

        // fallback if API fails
        this.latestEpisodeLink =
          "https://www.youtube.com/@abrahamparker8684/videos";
      });
      //Now I will fetch from .txt file from first line
  //     fetch("https://www.abrahamparker.com/latestepisode.txt")
  // .then(response => response.text())   // read as text (not JSON)
  // .then(text => {
  //   const firstLine = text.split('\n')[0].trim(); // get first line
  //   this.latestEpisodeLink = firstLine;
  // })
  // .catch(error => {
  //   console.error("File load error:", error);

  //   // fallback if file fails
  //   this.latestEpisodeLink =
  //     "https://www.youtube.com/@abrahamparker8684/videos";
  // });

  },
      
  methods: {
    watchLatest() {
      // later you can route to /episodes or a video page
      alert("Redirecting to the latest episode!");
    },

    contactUs() {
      this.$router.push({
        name: "ContactUsNew",
      });
    },
  },

  
};
</script>

<style scoped>
.home {
  font-family: "Poppins", sans-serif;
  background-color: #0f172a; /* deep navy */
  color: #e5e7eb;
  padding-top: 175px;
}

/* HERO */
.hero {
  background: radial-gradient(circle at top, #0dd743, #1e1b4b);
  text-align: center;
  padding: 15px 10px;
  border-top-right-radius: 60px;
  border-bottom-left-radius: 60px;
  border-bottom-left-radius: 60px;
  border-top-right-radius: 60px;
}

.title {
  font-size: 3.5rem;
  font-weight: 1000;
  letter-spacing: 2px;
  color: #fa1515; /* gold */
    /* White glow / shadow effect */
  text-shadow:
    5px 2px 4px rgba(245, 240, 244, 0.4),
    0 6px 20px rgba(239, 247, 250, 0.25);
}




.tagline {
  font-size: 1.25rem;
  margin: 20px 0 35px;
  color: #e0e7ff;
}

/* BUTTONS */
.primary-btn {
  background: #facc15;
  color: #1e1b4b;
  padding: 14px 35px;
  border-radius: 30px;
  font-weight: 600;
  transition: all 0.3s ease;
  text-decoration: none;   /* removes underline */
  display: inline-block;
  cursor: pointer;
}

.primary-btn:hover {
 background: #ff9800;
  color: #000;
}

.secondary-btn {
  background: linear-gradient(135deg, #d70d97, #fc06ec);
  color: #fff;
  padding: 14px 35px;
  border-radius: 30px;
  font-weight: 600;
 text-decoration: none;   /* removes underline */
}

/* SECTIONS */
section {
  padding: 50px 20px;
  max-width: 1100px;
  margin: auto;
}

.about,
.cta {
  text-align: center;
}

.about h2,
.episodes h2,
.cta h2 {
  font-size: 2rem;
  margin-bottom: 20px;
  color: #facc15;
}

.about p,
.cta p {
  max-width: 750px;
  margin: auto;
  font-size: 1.05rem;
  color: #c7d2fe;
}

/* EPISODES */
.episode-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 25px;
  margin-top: 40px;
}

.episode-card {
  background: linear-gradient(180deg, #1e293b, #020617);
  border-radius: 16px;
  padding: 25px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.4);
  transition: all 0.3s ease;
}

.episode-card:hover {
  transform: translateY(-6px) scale(1.02);
}

.episode-card h3 {
  color: #a5b4fc;
  margin-bottom: 12px;
}

.episode-card p {
  color: #d1d5db;
  font-size: 0.95rem;
}

.date {
  display: inline-block;
  margin-top: 15px;
  padding: 6px 14px;
  background-color: #7c3aed;
  color: #fff;
  border-radius: 20px;
  font-size: 0.8rem;
}

/* CTA */
.cta {
  background: linear-gradient(135deg, #020617, #1e1b4b);
  border-radius: 40px;
  margin: 80px auto;
  padding: 80px 30px;
}

.innerBoxSize {
  width: 425px;
  max-width: 425px;
  min-width: 350px;
  margin-top: -40px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: -100px;

}

.glow-line {
  height: 3px;
  width: 100%;
  margin: 50px auto;
  margin-bottom: -25px;
  background: #ec16b7;
  box-shadow: 0 0 10px #7c3aed,
              0 0 20px #7c3aed;
  border-radius: 50px;
}

.youtube-wrapper {
  width: 240px;       /* 2.5 inch approx */
  aspect-ratio: 16 / 9;
  margin: 20px auto;  /* center on page */
}

.youtube-wrapper iframe {
  width: 100%;
  height: 100%;
  border-radius: 8px;
}

</style>
