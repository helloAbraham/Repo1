<template>
    <section class="header_margin">
  <div class="season-list">
    <h2>All Seasons</h2>

    <div v-if="loading">Loading...</div>
    <div v-else-if="seasons.length === 0">No seasons found.</div>
    <div v-else>
      <div v-for="season in seasons" :key="season.id" class="season-item">
        <h3>{{ season.name }} (ID: {{ season.id }})</h3>

        <table border="1" cellpadding="5" cellspacing="0">
          <thead>
            <tr>
              <th>Episode ID</th>
              <th>Title</th>
              <th>YouTube ID</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="episode in season.episodes" :key="episode.id">
              <td>{{ episode.id }}</td>
              <td>{{ episode.title }}</td>
              <td>
                <a :href="'https://www.youtube.com/watch?v=' + episode.youtubeId" target="_blank">
                  {{ episode.youtubeId }}
                </a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  </section>
</template>

<script>
export default {
  name: "SeasonList",
  data() {
    return {
      seasons: [],
      loading: true,
    };
  },
  mounted() {
    this.fetchSeasons();
  },
  methods: {
    async fetchSeasons() {
      try {
        //The local test endpoint http://localhost:8082/api/seasons
        const response = await fetch("https://api.abrahamparker.com/api/seasons");
        if (!response.ok) throw new Error("Network response was not ok");
        this.seasons = await response.json();
      } catch (error) {
        console.error("Failed to fetch seasons:", error);
        alert("Failed to fetch seasons. Check console for details.");
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
.season-list {
  max-width: 800px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}

.season-item {
  margin-bottom: 30px;
  padding: 10px;
  border: 1px solid #ccc;
}
.header_margin {
    padding-top: 175px;
}
</style>
