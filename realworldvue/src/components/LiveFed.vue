<template>
  <div class="live-page">

    <h1 class="glow-title">
      🔴 {{ channelName || "Live IPTV Broadcasting" }}
    </h1>

    <div class="video-container">
      <video
        ref="videoPlayer"
        controls
        autoplay
        playsinline
      ></video>
    </div>

    <div class="status-text">

      <p v-if="loading" class="loading">
        ⏳ Connecting to stream...
      </p>

      <p v-else-if="online" class="online">
        🟢 Stream Online
      </p>

      <p v-else class="offline">
        🔴 Stream Offline
      </p>

    </div>

  </div>
</template>

<script>
import Hls from "hls.js";

export default {
  name: "LiveFed",

  data() {
    return {
      channelName: "",
      streamUrl: "",
      hls: null,
      loading: true,
      online: false,
      error: false,

      channels: {
        1: {
          name: "ATN Bangla",
          stream: "https://tvsen5.aynaott.com/atnbangla/index.m3u8"
        },

        2: {
          name: "Boishaki TV",
          stream: "https://boishakhi.sonarbanglatv.com/boishakhi/boishakhitv/index.m3u8"
        },

        3: {
          name: "Star Sports HD",
          stream: "https://tvsen5.aynaott.com/cXPB2LKkErN9/index.m3u8"
        },

        4: {
          name: "Deshi TV",
          stream: "https://deshitv.deshitv24.net/live/myStream/playlist.m3u8"
        },
        5: {
          name: "M2M TV",
          stream: "https://www.abrahamparker.com/hls/stream.m3u8"
        },

        6: {
          name: "RTV",
          stream: "https://tvsen5.aynaott.com/RtvHD/index.m3u8"
        },
        7: {
          name: "Ruposhi Bangla",
          stream: "https://mumt05.tangotv.in/87NeALx2RUPASIBANGLA/index.m3u8"
        },
        8: {
          name: "Colors TV",
          stream: "https://da86m1sqpm3o0.cloudfront.net/28072023/smil:colorsme.smil/playlist.m3u8"
        },
        9: {
          name: "Bangla Vision",
          stream: "https://tvsen5.aynaott.com/banglavision/index.m3u8"
        },
        10: {
          name: "T sports",
          stream: "https://tvsen5.aynaott.com/TnMn5kZz8aLm/index.m3u8" 
        },
        101: {
          name: "TVR Moldova",
          stream: "https://tvr-moldova.lg.mncdn.com/tvrmoldova/smil:tvrmoldova.smil/chunklist.m3u8"
        },

        102: {
            
          name: "M1 TV",
          stream: "https://v0.trm.md/static/streaming-playlists/hls/9b79338b-1870-4cd7-91d4-0f6ce5cac7ca/master.m3u8"
        },

        103: {
          name: "M2 TV",
          stream: "https://v0.trm.md/static/streaming-playlists/hls/d5fafab0-9c37-4746-9e7a-b2d6c0427015/master.m3u8"
        }
      }
    };
  },

  mounted() {
    const id = this.$route.params.id;

    const channel = this.channels[id];

    if (!channel) {
      this.loading = false;
      this.error = true;
      return;
    }

    this.channelName = channel.name;
    this.streamUrl = channel.stream;

    this.playStream();
  },

  beforeUnmount() {
    if (this.hls) {
      this.hls.destroy();
      this.hls = null;
    }
  },

  methods: {
    playStream() {
      const video = this.$refs.videoPlayer;

      if (!video) {
        console.error("Video element was not found.");
        return;
      }

      /*
       * Chrome / Firefox / Edge
       */
      if (Hls.isSupported()) {
        this.hls = new Hls();

        this.hls.loadSource(this.streamUrl);

        this.hls.attachMedia(video);

        this.hls.on(
          Hls.Events.MANIFEST_PARSED,
          () => {
            this.loading = false;
            this.online = true;
            this.error = false;

            video.play().catch(() => {
              console.log("Autoplay was blocked by the browser.");
            });
          }
        );

        this.hls.on(
          Hls.Events.ERROR,
          (event, data) => {
            console.error("HLS error:", data);

            if (data.fatal) {
              this.loading = false;
              this.online = false;
              this.error = true;
            }
          }
        );

        return;
      }

      /*
       * Safari
       */
      if (
        video.canPlayType(
          "application/vnd.apple.mpegurl"
        )
      ) {
        video.src = this.streamUrl;

        video.addEventListener(
          "loadedmetadata",
          () => {
            this.loading = false;
            this.online = true;
            this.error = false;

            video.play().catch(() => {
              console.log("Autoplay was blocked by the browser.");
            });
          }
        );

        video.addEventListener(
          "error",
          () => {
            this.loading = false;
            this.online = false;
            this.error = true;
          }
        );

        return;
      }

      /*
       * Browser does not support HLS
       */
      this.loading = false;
      this.online = false;
      this.error = true;

      console.error(
        "This browser does not support HLS."
      );
    }
  }
};
</script>

<style scoped>
.live-page {
  min-height: 100vh;
  background: radial-gradient(circle, #0f3d0f, #000);
  text-align: center;
  padding: 150px 40px 40px;
  color: white;
}

.glow-title {
  font-size: 48px;
  color: #ff2e2e;

  text-shadow:
    0 0 10px #ff0000,
    0 0 20px #ff0000,
    0 0 30px #ff0000;

  margin-bottom: 30px;
}

.video-container {
  position: relative;
  width: 100%;
  max-width: 1000px;
  aspect-ratio: 16 / 9;

  margin: 100px auto 0;

  background: #000;

  border-radius: 12px;

  overflow: hidden;

  box-shadow:
    0 0 20px #00ff00,
    0 0 40px #00ff00;
}

.video-container video {
  width: 100%;
  height: 100%;
  display: block;
  background: #000;
  object-fit: contain;
}

.status-text {
  font-size: 20px;
  margin-top: 30px;
}

.online {
  color: #00ff00;

  text-shadow:
    0 0 10px #00ff00,
    0 0 20px #00ff00;
}

.loading {
  color: #ffff00;
}

.offline {
  color: #ff3333;

  text-shadow:
    0 0 10px #ff0000;
}

@media (max-width: 768px) {
  .live-page {
    padding: 100px 20px 20px;
  }

  .glow-title {
    font-size: 32px;
  }

  .video-container {
    margin-top: 50px;
  }
}
</style>