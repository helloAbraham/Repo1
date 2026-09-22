<template>
  <div class="live-page">

    <h1 class="glow-title">
      🔴 Live IPTV Broadcasting
    </h1>

    <div class="video-container">
      <iframe
        :src="streamUrl"
        frameborder="0"
        allow="autoplay; fullscreen"
        allowfullscreen>
      </iframe>
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
     streamUrl: "https://www.abrahamparker.com/hls/stream.m3u8",
     
      //streamUrl: "http://tvsen5.aynascope.net/atnbangla/index.m3u8"
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

  /* 16:9 Ratio */
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


/* iframe fills video container */
.video-container iframe {

  position: absolute;

  width: 100%;

  height: 100%;

  left: 0;

  top: 0;

  border: none;

}


/* Offline / Status Container */
.offline-container {

  margin-top: 100px;

  font-size: 20px;

  color: #00ff00;

  text-shadow:
    0 0 10px #00ff00,
    0 0 20px #00ff00;
}


/* Stream Status */
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


/* Mobile Responsive */
@media (max-width: 768px) {

  .live-page {

    padding: 20px;

    padding-top: 100px;

  }


  .glow-title {

    font-size: 32px;

  }


  .video-container {

    margin-top: 50px;

  }

}
</style>