<template>
  <div class="container">
    <h1 class="title">Correspondents</h1>

    <div class="card-grid">
      <div
        class="card"
        v-for="journalist in journalists"
        :key="journalist.id"
        role="link"
        tabindex="0"
        @click="openJournalist(journalist)"
        @keydown.enter.self="openJournalist(journalist)"
        @keydown.space.prevent.self="openJournalist(journalist)"
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
          @click.stop="toggleStatus(journalist)"
          @keydown.stop
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
          @click.stop
        >
          View Profile
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { journalists } from "@/data/journalists";

export default {
  name: "JournalistListPage",
  methods: {
    openJournalist(journalist) {
      this.$router.push({
        name: "JournalistIndividual",
        params: { id: journalist.id }
      });
    },
    toggleStatus(journalist) {
      journalist.active = !journalist.active;
    }
  },
  data() {
    return {
      journalists
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
  cursor: pointer;
  background: #fff;
  border-radius: 12px;
  padding: 12px;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0,0,0,.1);
  transition: transform .2s ease, box-shadow .2s ease;
  min-width: 0;
}

.card:focus-visible {
  outline: 3px solid rgba(13, 110, 253, .5);
  outline-offset: 3px;
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
