<template>
  
    <!-- <div>
        <ProfileOne :profile="userProfile" />
    </div> -->
   
    <div class="sizeDown">
    </div> 
    <div class="dashboard">

    <!-- Sidebar -->
    <div class="sidebar">
      <h2>Seasons</h2>
      <ul>
        <li
          v-for="season in seasons"
          :key="season.id"
          @click="selectSeason(season)"
        >
          {{ season.name }}
        </li>
      </ul>
    </div>

    <!-- Main Content -->
    <div class="content">

      <div v-if="!selectedSeason">
        <h2>Please Select a Season to watch</h2>
      </div>

      <div v-else>
        <h2>{{ selectedSeason.name }}</h2>

        <!-- Episodes List -->
        <ul class="episodes">
          <li
            v-for="episode in selectedSeason.episodes"
            :key="episode.id"
            @click="playEpisode(episode)"
          >
            {{ episode.title }}
          </li>
        </ul>

        <!-- YouTube Player -->
        <div v-if="selectedVideo" class="player">
          <iframe
            width="700"
            height="400"
            :src="youtubeUrl"
            frameborder="0"
            allowfullscreen
          >
          </iframe>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
import ProfileOne from '../components/ProfileOne.vue';

export default {
  name: 'Dashboard',
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
        //for local testing, use this URL: http://localhost:8082/api/seasons
        //const response = await fetch('http://localhost:8082/api/seasons');
        //for production, use this URL: https://api.abrahamparker.com/api/seasons
        const response = await fetch('https://api.abrahamparker.com/api/seasons');
        if (!response.ok) throw new Error('Failed to fetch seasons');
        this.seasons = await response.json();
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
