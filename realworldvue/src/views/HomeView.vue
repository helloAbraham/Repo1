<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <EventCard msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import EventCard from "@/components/EventCard.vue";

export default {
  name: "HomeView",
  components: {
    EventCard,
  },
};
</script>
