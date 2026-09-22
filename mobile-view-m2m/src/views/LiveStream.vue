<template>
  <div class="live-page">
    <h1 class="glow-title">🔴 Probas Alapon Live</h1>

    <div v-if="loading" class="status-text">
      Checking live status...
    </div>

    <div v-else-if="isLive" class="video-container">
      <iframe
        :src="youtubeEmbedUrl"
        frameborder="0"
        allow="autoplay; encrypted-media"
        allowfullscreen
      ></iframe>
    </div>

    <div v-else class="offline-container">
      <h2>We Are Currently Offline</h2>
      <p>Stay tuned for the next live discussion.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LiveStream",
  data() {
    return {
      apiKey: "YOUR_YOUTUBE_API_KEY",
      channelId: "YOUR_CHANNEL_ID",
      videoId: null,
      isLive: false,
      loading: true
    };
  },
  computed: {
    youtubeEmbedUrl() {
      return `https://www.youtube.com/embed/${this.videoId}?autoplay=1`;
    }
  },
  methods: {
    async checkLiveStatus() {
      try {
        const response = await axios.get(
          //"https://www.googleapis.com/youtube/v3/search",
           "https://www.abrahamparker.com/hls/stream.m3u8",
          {
            params: {
              part: "snippet",
              channelId: this.channelId,
              eventType: "live",
              type: "video",
              key: this.apiKey
            }
          }
        );

        if (response.data.items.length > 0) {
          this.videoId = response.data.items[0].id.videoId;
          this.isLive = true;
        } else {
          this.isLive = false;
        }
      } catch (error) {
        console.error("Error checking live status:", error);
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.checkLiveStatus();
  }
};
</script>

<style scoped>
.live-page {
  min-height: 100vh;
  background: radial-gradient(circle, #0f3d0f, #000);
  text-align: center;
  padding: 40px;
  color: white;
  padding-top: 150px;
  
  
}

/* Neon Glow Title */
.glow-title {
  font-size: 48px;
  color: #ff2e2e;
  text-shadow:
    0 0 10px #ff0000,
    0 0 20px #ff0000,
    0 0 30px #ff0000;
  margin-bottom: 30px;
}

/* Video Responsive */
.video-container {
  position: relative;
  padding-bottom: 56.25%;
  height: 0;
  max-width: 1000px;
  margin: 0 auto;
  box-shadow:
    0 0 20px #00ff00,
    0 0 40px #00ff00;
  border-radius: 12px;
  overflow: hidden;
  margin-top: 100px;
}

.video-container iframe {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  
}

.offline-container {
  margin-top: 50px;
  font-size: 20px;
  color: #00ff00;
  text-shadow:
    0 0 10px #00ff00,
    0 0 20px #00ff00;
    margin-top: 100px;
}

.status-text {
  font-size: 20px;
  margin-top: 30px;
}
</style>