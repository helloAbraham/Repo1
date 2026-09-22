<template>
  <div class="home">
    <!-- Hero Section -->
    <section class="hero">
      <h1 class="title">ProbasAlapon</h1>
      <p class="tagline">
        Real Conversations. Raw Opinions. Unfiltered Stories.
      </p>
      <button class="primary-btn" @click="watchLatest">
        Watch Latest Episode
      </button>
    </section>

    <!-- About Section -->
    <section class="about">
      <h2>About the Show</h2>
      <p>
        <strong>ProbasAlapon</strong> is a talk show model focused on honest
        discussions, cultural insights, helping community, and untold stories from everyday people
        and special guests.
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

    <!-- Call to Action -->
    <section class="cta">
      <h2>Be a Part of the Conversation</h2>
      <p>
        Want to share your story or join the discussion?
      </p>
      <button class="secondary-btn" @click="contactUs">
        Contact Us
      </button>
    </section>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      episodes: [
        {
          id: 1,
          title: "Life Abroad: The Untold Truth",
          description: "A deep conversation about migrant life and identity.",
          date: "Jan 10, 2026"
        },
        {
          id: 2,
          title: "Success Without Shortcuts",
          description: "Stories of patience, failure, and persistence.",
          date: "Jan 22, 2026"
        },
        {
          id: 3,
          title: "Culture, Change & Conflict",
          description: "How modern life is reshaping traditions.",
          date: "Feb 02, 2026"
        }
      ]
    };
  },
  methods: {
    watchLatest() {
      // later you can route to /episodes or a video page
      alert("Redirecting to the latest episode!");
    },
    contactUs() {
      // later you can route to /contact
      alert("Opening contact page!");
    }
  }
};
</script>

<style scoped>
.home {
  font-family: Arial, sans-serif;
  color: #222;
}

/* Hero */
.hero {
  background: linear-gradient(135deg, #111, #333);
  color: #fff;
  text-align: center;
  padding: 80px 20px;
}

.title {
  font-size: 3rem;
  margin-bottom: 10px;
}

.tagline {
  font-size: 1.2rem;
  margin-bottom: 25px;
}

/* Buttons */
.primary-btn,
.secondary-btn {
  padding: 12px 25px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
}

.primary-btn {
  background: #ff9800;
  color: #000;
}

.secondary-btn {
  background: #111;
  color: #fff;
}

/* Sections */
section {
  padding: 60px 20px;
  max-width: 1000px;
  margin: auto;
}

.about p,
.cta p {
  max-width: 700px;
}

/* Episodes */
.episode-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  margin-top: 30px;
}

.episode-card {
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 6px;
}

.episode-card h3 {
  margin-bottom: 10px;
}

.date {
  font-size: 0.85rem;
  color: #777;
}
</style>
