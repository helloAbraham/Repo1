
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
          ></iframe>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
import ProfileOne from '../components/ProfileOne.vue';
    export default {
        name:'Dashboard',

        components: {
            ProfileOne,
        },
        data() {
            return {
              imageURL: '@/assets/currentImage/BD_Map.png',
                imageSrc: require('../assets/Saiful_Imam.jpg'), // Import your image
                userProfile: {
                    name: 'Saifulla Khan',
                    avatar: 'https://via.placeholder.com/100',
                    bio: 'A software developer',
                    details: {
                    Email: 'john@example.com',
                    Location: 'New York'
                    // Additional profile details
                    }
                },

                seasons: [
        {
          id: 1,
          name: "Season 1",
          episodes: [
            { id: 1, title: "Episode 1", youtubeId: "dQw4w9WgXcQ" },
            { id: 2, title: "Episode 2", youtubeId: "9bZkp7q19f0" }
          ]
        },
        {
          id: 2,
          name: "Season 2",
          episodes: [
            { id: 1, title: "Episode 1", youtubeId: "3tmd-ClpJxA" },
            { id: 2, title: "Episode 2", youtubeId: "L_jWHffIx5E" }
          ]
        }
      ],
      selectedSeason: null,
      selectedVideo: null
    };
            },

        computed: {
          youtubeUrl() {
            return `https://www.youtube.com/embed/${this.selectedVideo}`;
          }
        },

      methods: {
        selectSeason(season) {
          this.selectedSeason = season;
          this.selectedVideo = null;
        },

        playEpisode(episode) {
          this.selectedVideo = episode.youtubeId;
        }
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
