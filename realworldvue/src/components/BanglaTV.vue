<template>
  <div class="container">
    <h1 class="title">Featured Journalists</h1>

    <div class="card-grid">

      <router-link
        v-for="journalist in journalists"
        :key="journalist.id"
        :to="{
          name: 'LiveFed',
          params: {
            id: journalist.id
          }
        }"
        class="card-link"
      >

        <div class="card">

          <img
            :src="journalist.image"
            :alt="journalist.name"
            class="avatar"
          />

          <h2>{{ journalist.name }}</h2>

          <p class="role">
            {{ journalist.role }}
          </p>

          <p class="bio">
            {{ journalist.bio }}
          </p>

          <span class="watch-btn">
            ▶ Watch Live
          </span>

        </div>

      </router-link>

    </div>
  </div>
</template>


<script>

export default {

  name: "BanglaTV",

  data() {

    return {

      journalists: [

        {
          id: 1,
          name: "ATN Bangla",
          role: "Bangladesh Television",
          bio: "Watch ATN Bangla live.",
          image: "https://www.abrahamparker.com/MyUploads/atn_logo.png"
        },

        {
          id: 2,
          name: "Boishaki TV",
          role: "Bangladesh Television",
          bio: "Watch Boishaki TV live.",
          image: "https://www.abrahamparker.com/MyUploads/boishaki_logo.png"
        },

        {
          id: 3,
          name: "Star Sports HD",
          role: "Sports Channel",
          bio: "Watch Star Sports HD live.",
          image: "https://www.abrahamparker.com/MyUploads/star_sports_logo.png"
        },

        {
          id: 4,
          name: "Deshi TV",
          role: "Bangladesh Television",
          bio: "Watch Deshi TV live.",
          image: "https://www.abrahamparker.com/MyUploads/deshi_tv_logo.png"
        },

        {
          id: 5,
          name: "M2M TV",
          role: "Entertainment, News, and Lifestyle",
          bio: "Coming soon a new channel bias free - IPTV.",
          image: "https://www.abrahamparker.com/MyUploads/m2m_tv_usa_logo_1.png"
        },

        {
          id: 6,
          name: "RTV",
          role: "Bangladesh Television",
          bio: "Watch RTV live.",
          image: "https://www.abrahamparker.com/MyUploads/rtv_logo.png"
        },

        {
          id: 7,
          name: "Ruposhi Bangla",
          role: "Bangladesh Television",
          bio: "Watch Ruposhi Bangla live.",
          image: "https://www.abrahamparker.com/MyUploads/ruposhi_bangla_logo.png"
        },

        {
          id: 8,
          name: "Colors TV",
          role: "Hindi Channel",
          bio: "Watch Colors TV live.",
          image: "https://www.abrahamparker.com/MyUploads/colors_tv_logo.png"
        },
        
        {
          id: 9,
          name: "Bangla Vision",
          role: "Bangladesh Television",
          bio: "Watch Bangla Vision live.",
          image: "https://www.abrahamparker.com/MyUploads/bangla_vision_logo.png"
        },
        
        {
          id: 10,
          name: "T sports",
          role: "Sports Channel",
          bio: "Watch T sports live.",
          image: "https://www.abrahamparker.com/MyUploads/t_sports_logo.png"
        }

      ]

    };

  }

};

</script>


<style scoped>

.container {
  max-width: 1200px;
  margin: 40px auto;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 30px;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(
    auto-fit,
    minmax(280px, 1fr)
  );

  gap: 24px;
}

.card-link {
  text-decoration: none;
  color: inherit;
}

.card {
  background: #fff;
  border-radius: 12px;
  padding: 24px;
  text-align: center;

  box-shadow:
    0 4px 12px rgba(0, 0, 0, .1);

  transition:
    all .25s ease;

  cursor: pointer;
}

.card:hover {
  transform: translateY(-6px);

  box-shadow:
    0 10px 20px rgba(0, 0, 0, .18);
}

.avatar {
  width: 120px;
  height: 120px;

  border-radius: 50%;

  object-fit: cover;

  margin-bottom: 16px;
}

.role {
  color: #1976d2;
  font-weight: bold;
}

.bio {
  color: #666;
  margin: 15px 0;
}

.watch-btn {
  display: inline-block;

  background: #e53935;

  color: white;

  padding: 10px 18px;

  border-radius: 6px;

  font-weight: 600;
}

</style>