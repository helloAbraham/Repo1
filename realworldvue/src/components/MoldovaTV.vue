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

  name: "MoldovaTV",

  data() {

    return {

      journalists: [

        {
          id: 101,
          name: "TVR Moldova",
          role: "Moldova Television",
          bio: "Watch TVR Moldova live.",
          image: "https://www.abrahamparker.com/MyUploads/tvr_logo.png"
        },

        {
          id: 102,
         
          name: "M1 TV",
          role: "Moldova TV",
          bio: "Watch M1 TV live.",
          image: "https://www.abrahamparker.com/MyUploads/m1_logo.png"
        },
        
        {

            id: 103,
          name: "M2 TV",
          role: "Moldova TV",
          bio: "Watch M2 TV live.",
          image: "https://www.abrahamparker.com/MyUploads/m2_logo.png"
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