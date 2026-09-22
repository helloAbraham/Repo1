<template>
  <div class="container">
    <h1 class="title">Correspondents</h1>

    <div class="card-grid">
      <div
        class="card"
        v-for="journalist in journalists"
        :key="journalist.id"
      >
        <img
          :src="journalist.image"
          :alt="journalist.name"
          class="avatar"
        />

        <h2>{{ journalist.name }}</h2>
        <p class="role">{{ journalist.role }}</p>

        <p class="bio">
          {{ journalist.bio }}
        </p>

        <button class="btn">View Profile</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "JournalistListPage",
  data() {
    return {
      journalists: [
        {
          id: 1,
          name: "Maruf Hassan",
          role: "Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://via.placeholder.com/120"
        },
        {
          id: 2,
          name: "Khalekul Islam",
          role: "Rangpur Division Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://via.placeholder.com/120"
        },
        {
          id: 3,
          name: "Rawfur Rahim",
          role: "Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://via.placeholder.com/120"
        },
        {
          id: 4,
          name: "AHM TORIQUL ISLAM",
          role: "Business Journalist",
          bio: "Focuses on finance, stock markets, and economic trends.",
          image: "https://via.placeholder.com/120"
        },
        {
          id: 5,
          name: "Olivia Davis",
          role: "Entertainment Journalist",
          bio: "Covers movies, TV shows, celebrities, and award events.",
          image: "https://via.placeholder.com/120"
        },
        {
          id: 6,
          name: "Daniel Martinez",
          role: "Investigative Journalist",
          bio: "Investigates corruption, public interest stories, and social issues.",
          image: "https://via.placeholder.com/120"
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
  color: #333;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
}

.card {
  background: #fff;
  border-radius: 12px;
  padding: 24px;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0,0,0,.1);
  transition: transform .2s ease, box-shadow .2s ease;
}

.card:hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 20px rgba(0,0,0,.15);
}

.avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-bottom: 16px;
  object-fit: cover;
}

.role {
  color: #007bff;
  font-weight: 600;
  margin: 8px 0;
}

.bio {
  color: #666;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 20px;
}

.btn {
  background: #007bff;
  color: white;
  border: none;
  padding: 10px 18px;
  border-radius: 6px;
  cursor: pointer;
  transition: background .2s;
}

.btn:hover {
  background: #0056b3;
}
</style>
