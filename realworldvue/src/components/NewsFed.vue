<template>
  <div class="news-feed-container" v-if="content">
    <h1 class="headline">{{ content.headline }}</h1>

    <div class="paragraphs">
      <p class="paragraph">{{ content.paragraph1 }}</p>
      <p class="paragraph">{{ content.paragraph2 }}</p>
    </div>
  </div>

  <div v-else class="loading">
    <p>Loading content...</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NewsFeed",
  data() {
    return {
      content: null
    };
  },
  mounted() {
    this.loadContent();
  },
  methods: {
    async loadContent() {
      try {
        //For local testing, replace the URL with your actual API endpoint
        //const res = await axios.get("http://localhost:8081/api/content/1");
        //For production, use the deployed API endpoint
        const res = await axios.get("https://api.abrahamparker.com/api/content/1");
        this.content = res.data;
      } catch (error) {
        console.error("Error loading content:", error);
      }
    }
  }
};
</script>

<style scoped>
.news-feed-container {
  max-width: 700px;
  margin: 40px auto;
  padding: 20px;
  font-family: "Helvetica Neue", Arial, sans-serif;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.headline {
  font-size: 1.8em;
  color: #333;
  margin-bottom: 15px;
  text-align: center;
  border-bottom: 2px solid #eee;
  padding-bottom: 10px;
}

.paragraphs {
  margin-top: 10px;
}

.paragraph {
  font-size: 1.1em;
  color: #444;
  line-height: 1.6;
  margin-bottom: 15px;
}

.loading {
  text-align: center;
  margin-top: 50px;
  font-size: 1.2em;
  color: #888;
}
</style>
