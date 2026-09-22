<template>
  
    <!-- <div>
        <ProfileOne :profile="userProfile" />
    </div> -->
   
    <div class="sizeDown">
    </div> 
    <div class="dashboard">

    

    <!-- Main Content -->
    <div class="content">

      <div v-if="seasons.length === 0">

  <h2>Loading latest news...</h2>

</div>

<div v-else>

  <div
    v-for="season in seasons"
    :key="season.id"
    class="season"
  >

    <h2>{{ season.name }}</h2>

    <ul class="episodes">

      <li
        v-for="episode in season.episodes"
        :key="episode.id"
        @click="playEpisode(episode)"
      >
        {{ episode.title }}
      </li>

    </ul>

  </div>

  <!-- YouTube Player -->

  <div v-if="selectedVideo" class="player">

    <iframe
      width="700"
      height="400"
      :src="youtubeUrl"
      frameborder="0"
      allowfullscreen
    ></iframe>

  </div>

</div>
</div>

  </div>
</template>

<script>
import ProfileOne from '../components/ProfileOne.vue';

export default {
  name: 'LatestNews',
  components: { ProfileOne },

  data() {
    return {
      seasons: [],
      selectedSeason: null,
      selectedVideo: null
    };
  },

  computed: {
    youtubeUrl() {
      return this.selectedVideo
        ? `https://www.youtube.com/embed/${this.selectedVideo}`
        : '';
    }
  },

  
  methods: {
    async fetchSeasons() {
        try {

            // Production
            const response = await fetch(
            'https://api.abrahamparker.com/api/seasons'
            );

            if (!response.ok) {
            throw new Error('Failed to fetch seasons');
            }

            const data = await response.json();

            // Keep only the last 2 episodes from each season
            this.seasons = data.map(season => ({
            ...season,
            episodes: season.episodes.slice(-2)
            }));

        } catch (error) {
            console.error('Error fetching seasons:', error);
        }
    },

    selectSeason(season) {
      this.selectedSeason = season;
      this.selectedVideo = null;
    },

    playEpisode(episode) {
      this.selectedVideo = episode.youtubeId;
    }
  },

  mounted() {
    this.fetchSeasons();
  }
};
</script>




<style scoped>
.dashboard {
  display: flex;
  height: 100vh;
  font-family: Arial, sans-serif;
  padding-top: 175px;
}

.sidebar {
  width: 250px;
  background: #2c3e50;
  color: white;
  padding: 20px;
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar li {
  padding: 10px;
  cursor: pointer;
}

.sidebar li:hover {
  background: #34495e;
}

.content {
  flex: 1;
  padding: 30px;
}

.episodes {
  list-style: none;
  padding: 0;
}

.episodes li {
  padding: 8px;
  cursor: pointer;
  border-bottom: 1px solid #ccc;
}

.episodes li:hover {
  background: #f2f2f2;
}

.player {
  margin-top: 20px;
}


</style>
