<template>
  <div class="container">
    <h1>YouTube to MP3 Converter</h1>

    <form @submit.prevent="convertToMp3">
      <input
        v-model="youtubeUrl"
        type="text"
        placeholder="Enter YouTube URL"
        required
      />
      <button type="submit" :disabled="loading">
        {{ loading ? "Converting..." : "Convert to MP3" }}
      </button>
    </form>

    <p v-if="status">{{ status }}</p>

    <a
      v-if="downloadUrl"
      :href="downloadUrl"
      download="audio.mp3"
      class="download-btn"
    >
      Download MP3
    </a>
  </div>
</template>

<script>
export default {
name: "DownloadFromYouTube",
  data() {
    return {
      youtubeUrl: "",
      status: "",
      loading: false,
      downloadUrl: null,
    };
  },
  methods: {
    async convertToMp3() {
      if (!this.youtubeUrl) return;
      this.loading = true;
      this.status = "Converting...";

      try {
        const response = await fetch("http://localhost:3000/convert", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ url: this.youtubeUrl }),
        });

        if (!response.ok) throw new Error("Conversion failed");

        const blob = await response.blob();
        this.downloadUrl = URL.createObjectURL(blob);
        this.status = "Conversion complete!";
      } catch (err) {
        this.status = "Error: " + err.message;
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background: #121212;
  color: #eee;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}
input {
  width: 400px;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: none;
  margin-right: 10px;
}
button {
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  border: none;
  background: #ff0000;
  color: white;
  cursor: pointer;
}
button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
.download-btn {
  margin-top: 20px;
  background: #28a745;
  padding: 10px 20px;
  border-radius: 5px;
  color: white;
  text-decoration: none;
}
</style>
