<template>
  <main class="journalist-page">
    <router-link class="back-link" :to="{ name: 'JournalistList' }">
      &larr; Back to reporters
    </router-link>

    <article v-if="journalist" class="profile-card">
      <img class="avatar" :src="journalist.image" :alt="journalist.name" />
      <div class="profile-content">
        <h1>{{ journalist.name }}</h1>
        <p class="role">{{ journalist.role }}</p>
        <p class="bio">{{ journalist.bio }}</p>
        <p class="status" :class="{ active: journalist.active }">
          {{ journalist.active ? "Active" : "Non-Active" }}
        </p>
        <div class="profile-actions">
          <a
            class="profile-link"
            :href="journalist.profileUrl"
            target="_blank"
            rel="noopener noreferrer"
          >
            View Profile
          </a>
          <router-link
            v-if="journalist.businessCardRoute"
            class="profile-link business-card-link"
            :to="{ name: journalist.businessCardRoute }"
          >
            Business Card
          </router-link>
        </div>
      </div>
    </article>

    <section v-else class="not-found">
      <h1>Reporter not found</h1>
      <p>This reporter may have been removed.</p>
    </section>
  </main>
</template>

<script>
import { journalists } from "@/data/journalists";

export default {
  name: "JournalistIndividual",
  props: {
    id: {
      type: String,
      required: true
    }
  },
  computed: {
    journalist() {
      return journalists.find((reporter) => reporter.id === Number(this.id));
    }
  }
};
</script>

<style scoped>
.journalist-page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 180px 20px 80px;
}

.back-link {
  display: inline-block;
  margin-bottom: 24px;
  color: #007bff;
  text-decoration: none;
}

.profile-card {
  display: flex;
  align-items: flex-start;
  gap: 32px;
  padding: 32px;
  border-radius: 12px;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, .1);
}

.avatar {
  width: 240px;
  max-width: 40%;
  object-fit: cover;
}

.profile-content h1 {
  color: #333;
}

.role {
  margin: 12px 0;
  color: #007bff;
  font-weight: 600;
}

.bio {
  margin: 20px 0;
  color: #666;
  line-height: 1.6;
}

.status {
  margin-bottom: 20px;
  color: #dc3545;
}

.status.active {
  color: #04a057;
}

.profile-link {
  display: inline-block;
  padding: 10px 18px;
  border-radius: 6px;
  background: #007bff;
  color: #fff;
  text-decoration: none;
}

.profile-link:hover {
  background: #0056b3;
}

.profile-actions {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.business-card-link {
  background: #ea04c0;
}

.business-card-link:hover {
  background: #0646e7;
}

.not-found {
  padding: 40px;
  border-radius: 12px;
  background: #fff;
  text-align: center;
}

@media (max-width: 600px) {
  .journalist-page {
    padding-top: 150px;
  }

  .profile-card {
    flex-direction: column;
    padding: 24px;
  }

  .avatar {
    width: 100%;
    max-width: 100%;
  }
}
</style>