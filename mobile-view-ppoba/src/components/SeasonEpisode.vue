<template>
    <section class="header_margin">
  <div class="season-form">
    <h2>Create Season</h2>
    
    <!-- Season Name -->
    <div>
      <label>Season Name:</label>
      <input v-model="season.name" type="text" placeholder="Season Name" />
    </div>

    <h3>Episodes</h3>
    
    <div v-for="(episode, index) in season.episodes" :key="index" class="episode-item">
      <label>Title:</label>
      <input v-model="episode.title" type="text" placeholder="Episode Title" />

      <label>YouTube ID:</label>
      <input v-model="episode.youtubeId" type="text" placeholder="YouTube ID" />

      <button @click="removeEpisode(index)">Remove</button>
    </div>

    <button @click="addEpisode">Add Episode</button>
    
    <br /><br />
    <button @click="submitSeason">Submit Season</button>

    <!-- <pre>{{ season }}</pre> -->
  </div>
  </section>
</template>

<script>
export default {
  name: "SeasonEpisode",
  data() {
    return {
      season: {
        id: null,
        name: "",
        episodes: [
          { id: null, title: "", youtubeId: "" }
        ]
      }
    };
  },
  methods: {
    addEpisode() {
      this.season.episodes.push({ id: null, title: "", youtubeId: "" });
    },
    removeEpisode(index) {
      this.season.episodes.splice(index, 1);
    },
    async submitSeason() {
      try {
        // Example POST request to your Spring Boot API
        //For local Testing http://localhost:8082/api/seasons
        const response = await fetch("https://api.abrahamparker.com/api/seasons", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(this.season)
        });

        const result = await response.json();
        alert("Season saved successfully!");
        console.log(result);
      } catch (error) {
        console.error("Error saving season:", error);
        alert("Failed to save season.");
      }
    }
  }
};
</script>

<style scoped>
.season-form {
  max-width: 600px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
  
}

.episode-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.header_margin{
    padding-top: 175px;
}

</style>
