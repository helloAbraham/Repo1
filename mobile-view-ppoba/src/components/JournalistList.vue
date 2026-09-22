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

        <button
          type="button"
          class="status"
          :class="{ active: journalist.active }"
          :aria-pressed="journalist.active"
          :aria-label="journalist.active ? 'Active' : 'Non-Active'"
          :title="journalist.active ? 'Active' : 'Non-Active'"
          @click="toggleStatus(journalist)"
        >
          {{ journalist.active ? "Active ✓" : "Non-Active ×" }}
        </button>

        <p class="bio">
          {{ journalist.bio }}
        </p>

        <a
          class="btn"
          :href="journalist.profileUrl || 'https://www.abrahamparker.com/'"
          target="_blank"
          rel="noopener noreferrer"
        >
          View Profile
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "JournalistListPage",
  methods: {
    toggleStatus(journalist) {
      journalist.active = !journalist.active;
    }
  },
  data() {
    return {
      journalists: [
         {
          id: 1,
          name: "Abraham Farhad Parker  ",
          role: "General news and Technology, Cloud Security Journalist",
          bio: "General news and technology journalist with a focus on cloud security.",
          image: "https://www.abrahamparker.com/MyUploads/Abraham_Photo_1.jpg",
          profileUrl: "https://www.abrahamparker.com/",
          active: true
        },
        {
          id: 2,
          name: "Maruf Hassan",
          role: "Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://www.abrahamparker.com/MyUploads/Maruf_Photo_2by2_1.jpg",
          profileUrl: "https://www.facebook.com/rangpur.maruf.1",
          active: true
        },
        {
          id: 3,
          name: "Khalequl Islam",
          role: "Rangpur Division Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://www.abrahamparker.com/MyUploads/Khaeq_2by2_1.jpg",
          profileUrl: "https://www.facebook.com/profile.php?id=61585467959763",
          active: true
        },
        {
          id: 4,
          name: "Rawfur Rahim",
          role: "Journalist",
          bio: "Reports on local events, politics, and community issues in the Rangpur Division.",
          image: "https://www.abrahamparker.com/MyUploads/Pranto_2by2.jpg",
          profileUrl: "https://www.facebook.com/rawfurrahim.pranto",
          active: true
        },
        {
          id: 5,
          name: "Anamul Karim",
          role: "Sports Journalist",
          bio: "Covers sports events, athlete interviews, and game analyses.",
          image: "https://www.abrahamparker.com/MyUploads/Anamul_Photo_1.PNG",
          profileUrl: "https://www.facebook.com/anamul.karim.948",
          active: true
        },
        {
          id: 6,
          name: "Sumon Rahman",
          role: "Stock and Share Market Journalist",
          bio: "Focuses on Share, stock markets, conservation efforts, and investment trends.",
          image: "https://www.abrahamparker.com/MyUploads/Sumon_Rahman_1.jpg",
          profileUrl: "https://www.",
          active: true
        },
      
        {
          id: 7,
          name: "Micheal Abiola",
          role: "Travel Journalist",
          bio: "Explores destinations, travel tips, and cultural experiences.",
          image: "https://www.abrahamparker.com/MyUploads/Mike_Photo_1.JPG",
          profileUrl: "https://www.mcsaccess.com/",
          active: true
        },
          {
          id: 8,
          name: "Momo Islam",
          role: "Women's Health & Beauty Journalist",
          bio: "Reports on medical research, women's make-ups, public health issues, and wellness trends.",
          image: "https://www.abrahamparker.com/MyUploads/Momo_Squaresize.jpg",
          profileUrl: "https://www.facebook.com/arpitaislam.momo",
          active: true
        },
        {
          id: 9,
          name: "Arafat E Kamal",
          role: "Journalist",
          bio: "Rajshahi Head: Reports on local events, politics, and community issues in the Rajshahi Division.",
          image: "https://www.abrahamparker.com/MyUploads/Arafat_Kamal_Photo_1.jpg", 
          profileUrl: "https://www.facebook.com/arafat.ekamal",
          active: true 
        },
        {
          id: 9,
          name: "AHM TORIQUL ISLAM",
          role: "Business Journalist",
          bio: "Focuses on finance, stock markets, and economic trends.",
          image: "https://via.placeholder.com/120",
          profileUrl: "https://www.",
          active: true
        },
        {
          id: 10,
          name: "Olivia Davis",
          role: "Entertainment Journalist",
          bio: "Covers movies, TV shows, celebrities, and award events.",
          image: "https://via.placeholder.com/120",
          profileUrl: "https://www.",
          active: true
        },
        {
          id: 11,
          name: "Daniel Martinez",
          role: "Investigative Journalist",
          bio: "Investigates corruption, public interest stories, and social issues.",
          image: "https://via.placeholder.com/120",
          profileUrl: "https://www.",
          active: true
        },
          {
          id: 12,
          name: "Ferdusur Rony",
          role: "Video/Camera Journalist",
          bio: "Videos and camera work for news coverage.",
          image: "https://www.abrahamparker.com/MyUploads/Ferdusur_Rony_Photo_1.jpg",
          profileUrl: "https://www.",
          active: true
        },
        {
          id: 13,
          name: "Moshfiqul Islam",
          role: "Agricultural Journalist",
          bio: "Focuses on agricultural news, farming techniques, fisheries, and rural development.",
          image: "https://www.abrahamparker.com/MyUploads/Moshfiqul_Islam_1.jpg",
          profileUrl: "https://www.facebook.com/boqul",
          active: true
        },
        {
          id: 14,
          name: "Arafat Rahman",
          role: "Reporter",
          bio: "Reports on local events, Bangladeshi news, and government policies.",
          image: "https://www.abrahamparker.com/MyUploads/Arafat_Rahman_Photo_1.jpg",
          profileUrl: "https://www.facebook.com/arafatTechUSA",
          active: true
        },
      
        
      ]
    };
  }
};
</script>

<style scoped>
.container {
  padding-top: 200px;
  max-width: 1200px;
  margin: 20px auto;
  padding: 40px 20px;
}

.title {
  padding-top: 150px;
  text-align: center;
  margin-bottom: 30px;
  color: #333;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 24px;
}

.card {
  background: #fff;
  border-radius: 12px;
  padding: 12px;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0,0,0,.1);
  transition: transform .2s ease, box-shadow .2s ease;
  min-width: 0;
}

@media (max-width: 1200px) {
  .card-grid {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
}

@media (max-width: 900px) {
  .card-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 560px) {
  .card-grid {
    grid-template-columns: 1fr;
  }
}

.card:hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 20px rgba(0,0,0,.15);
}

.avatar {
  width: 192px;
  height: 192px;

  margin-bottom: 16px;
  object-fit: cover;
}

/* .avatar {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-bottom: 16px;
  object-fit: cover;
} */

.role {
  color: #007bff;
  font-weight: 600;
  margin: 8px 0;
}

.status {
  align-items: center;
  border: 1px solid #dc3545;
  border-radius: 999px;
  background: #fff;
  color: #dc3545;
  cursor: pointer;
  display: inline-flex;
  font-weight: 600;
  height: 32px;
  justify-content: center;
  padding: 0 12px;
  width: auto;
}

.status.active {
  border-color: #194edf;
  color: #04a057;
  background: #e9f7ef;
}

.status:focus-visible {
  outline: 3px solid rgba(13, 110, 253, .35);
  outline-offset: 2px;
}

.bio {
  color: #666;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 20px;
}

.btn {
  display: inline-block;
  background: #007bff;
  color: white;
  border: none;
  padding: 10px 18px;
  border-radius: 6px;
  cursor: pointer;
  text-decoration: none;
  font: inherit;
  transition: background .2s;
}

.btn:hover {
  background: #0056b3;
  color: white;
}
</style>
