<template>
  <div class="live-page">

    <!-- Fixed Logo / Title -->
    <h1 class="glow-title">
      🔴 Live IPTV
    </h1>

    <div class="video-container">

      <iframe
        :src="streamUrl"
        frameborder="0"
        allow="autoplay; fullscreen"
        allowfullscreen>
      </iframe>

      <!-- Breaking News Overlay -->
      <div class="breaking-news">
        <span class="breaking-label">BREAKING NEWS</span>
        <span class="breaking-text">
          Latest news and updates are coming...
        </span>
      </div>

    </div>

    <div class="status-text">
      <p class="online">
        🟢 Stream Online
      </p>
    </div>

  </div>
</template>


<script>
export default {
  name: "LiveFed",

  data() {
    return {
      streamUrl: "https://www.abrahamparker.com/hls/stream.m3u8"
    };
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

  /* Keep content from going underneath fixed title */
 
  margin-top: 150px;
}


/* =========================
   FIXED TOP-LEFT LOGO
   ========================= */

.glow-title {
  position: fixed;
  top: 20px;
  left: 25px;

  z-index: 9999;

  margin: 0;

  font-size: 48px;
  color: #ff2e2e;

  text-shadow:
    0 0 10px #ff0000,
    0 0 20px #ff0000,
    0 0 30px #ff0000;

  pointer-events: none;
}


/* =========================
   VIDEO
   ========================= */

.video-container {
  position: relative;

  width: 100%;
  max-width: 1000px;

  /* 16:9 */
  aspect-ratio: 16 / 9;

  margin: 100px auto 0;

  box-shadow:
    0 0 20px #00ff00,
    0 0 40px #00ff00;

  border-radius: 12px;

  overflow: hidden;

  background: #000;
}


/* iframe */

.video-container iframe {
  position: absolute;

  width: 100%;
  height: 100%;

  left: 0;
  top: 0;

  border: none;
}


/* =========================
   BREAKING NEWS OVER VIDEO
   ========================= */

.breaking-news {
  position: absolute;

  left: 0;
  bottom: 0;

  width: 100%;

  height: 48px;

  display: flex;
  align-items: center;

  background: rgba(0, 0, 0, 0.88);

  z-index: 10;

  overflow: hidden;

  text-align: left;
}


/* Red Breaking News Label */

.breaking-label {
  flex-shrink: 0;

  height: 100%;

  display: flex;
  align-items: center;

  padding: 0 18px;

  background: #e60000;

  color: white;

  font-size: 16px;
  font-weight: bold;

  text-shadow:
    0 0 5px #fff,
    0 0 10px #ff0000;
}


/* Scrolling News Text */

.breaking-text {
  white-space: nowrap;

  padding-left: 20px;

  font-size: 17px;
  font-weight: 600;

  color: white;

  animation: news-scroll 15s linear infinite;
}


/* News Animation */

@keyframes news-scroll {
  from {
    transform: translateX(100%);
  }

  to {
    transform: translateX(-100%);
  }
}


/* =========================
   STATUS
   ========================= */

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


/* =========================
   MOBILE
   ========================= */

@media (max-width: 768px) {

  .live-page {
    padding: 20px;
    padding-top: 90px;
  }


  /* Logo stays fixed top-left */

  .glow-title {
    position: fixed;

    top: 12px;
    left: 15px;

    font-size: 28px;

    z-index: 9999;
  }


  /* Video stays 16:9 */

  .video-container {
    width: 100%;

    aspect-ratio: 16 / 9;

    margin-top: 40px;

    border-radius: 8px;

    box-shadow:
      0 0 12px #00ff00,
      0 0 25px #00ff00;
  }


  /* Smaller breaking news */

  .breaking-news {
    height: 36px;
  }


  .breaking-label {
    padding: 0 10px;

    font-size: 11px;
  }


  .breaking-text {
    padding-left: 12px;

    font-size: 12px;
  }


  .status-text {
    font-size: 16px;
    margin-top: 20px;
  }

}

</style>
