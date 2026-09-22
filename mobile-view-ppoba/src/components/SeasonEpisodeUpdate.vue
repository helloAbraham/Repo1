<template>
  <section class="header_margin">
    <div class="season-form">

      <h2>Manage Seasons</h2>

      <!-- Select Existing Season -->
      <div>
        <label>Select Season:</label>
        <select v-model="selectedSeasonId" @change="loadSeason">
          <option value="">-- Create New --</option>
          <option v-for="s in seasons" :key="s.id" :value="s.id">
            {{ s.name }}
          </option>
        </select>
      </div>

      <hr />

      <!-- Season Name -->
      <div>
        <label>Season Name:</label>
        <input v-model="season.name" type="text" placeholder="Season Name" />
      </div>

      <h3>Episodes</h3>

      <div
        v-for="(episode, index) in season.episodes"
        :key="index"
        class="episode-item"
      >
        <label>Title:</label>
        <input v-model="episode.title" type="text" />

        <label>YouTube ID:</label>
        <input v-model="episode.youtubeId" type="text" />

        <button @click="removeEpisode(index)">Remove</button>
      </div>

      <button @click="addEpisode">Add Episode</button>

      <br /><br />

      <!-- Buttons -->
      <button v-if="!season.id" @click="createSeason">
        Create Season
      </button>

      <button v-if="season.id" @click="updateSeason">
        Update Season
      </button>

      <button
        v-if="season.id"
        @click="deleteSeason"
        style="background:red;color:white"
      >
        Delete Season
      </button>

    </div>
  </section>
</template>

<script>
export default {
  name: "SeasonEpisodeUpdate",

  data() {
    return {
      seasons: [],
      selectedSeasonId: "",
      season: {
        id: null,
        name: "",
        episodes: []
      }
    };
  },

  mounted() {
    this.fetchSeasons();
  },

  methods: {

    /* ======================
       FETCH ALL SEASONS
    ====================== */
    async fetchSeasons() {
      const res = await fetch(
        "https://api.abrahamparker.com/api/seasons"
        );
      this.seasons = await res.json();
    },

    /* ======================
       LOAD SEASON FOR EDIT
    ====================== */
    loadSeason() {
      if (!this.selectedSeasonId) {
        this.resetForm();
        return;
      }

      const found = this.seasons.find(
        s => s.id == this.selectedSeasonId
      );

      if (found) {
        this.season = JSON.parse(JSON.stringify(found)); // deep copy
      }
    },

    /* ======================
       CREATE
    ====================== */
    async createSeason() {
      try {
        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons",
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(this.season)
          }
        );

        if (!response.ok) {
          const errorData = await response.json();
          console.error("Create failed:", errorData);
          alert(`Error: ${response.status} - ${errorData.message || response.statusText}`);
          return;
        }

        await response.json();
        alert("Season Created!");
        this.resetForm();
        this.fetchSeasons();
      } catch (error) {
        console.error("Failed to create season:", error);
        alert("Error creating season: " + error.message);
      }
    },

    /* ======================
       UPDATE
       http://localhost:8082/api/seasons/${this.season.id}
    ====================== */
    async updateSeason() {
      try {
        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons/" + this.season.id,
          {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(this.season)
          }
        );

        if (!response.ok) {
          const errorData = await response.json();
          console.error("Update failed:", errorData);
          alert(`Error: ${response.status} - ${errorData.message || response.statusText}`);
          return;
        }

        await response.json();
        alert("Season Updated!");
        this.fetchSeasons();
      } catch (error) {
        console.error("Failed to update season:", error);
        alert("Error updating season: " + error.message);
      }
    },

    /* ======================
       DELETE
       for local testing http://localhost:8082/api/seasons/${this.season.id}
    ====================== */
    async deleteSeason() {
      if (!confirm("Are you sure you want to delete this season?"))
        return;

      try {
        const response = await fetch(
          "https://api.abrahamparker.com/api/seasons/" + this.season.id,
          {
            method: "DELETE"
          }
        );

        if (!response.ok) {
          const errorData = await response.json();
          console.error("Delete failed:", errorData);
          alert(`Error: ${response.status} - ${errorData.message || response.statusText}`);
          return;
        }

        alert("Season Deleted!");
        this.resetForm();
        this.fetchSeasons();
      } catch (error) {
        console.error("Failed to delete season:", error);
        alert("Error deleting season: " + error.message);
      }
    },

    /* ======================
       HELPERS
    ====================== */
    addEpisode() {
      this.season.episodes.push({
        id: null,
        title: "",
        youtubeId: ""
      });
    },

    removeEpisode(index) {
      this.season.episodes.splice(index, 1);
    },

    resetForm() {
      this.selectedSeasonId = "";
      this.season = {
        id: null,
        name: "",
        episodes: []
      };
    }
  }
};
</script>

<style scoped>
.season-form {
  max-width: 600px;
  margin: auto;
}

.episode-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}

.header_margin {
  padding-top: 175px;
}
</style>
