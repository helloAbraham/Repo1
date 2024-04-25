<template>
  <div class="app">
    <Navigation />
    <router-view/>
    <FooterPage/>
  </div>
</template>

<script>
import Navigation from './components/Navigation.vue';
import FooterPage from './components/FooterPage.vue';
export default{
  name: "App",
  components: {
    Navigation,
    FooterPage,

  }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Raleway:100,300,400|Roboto+Condensed:300,700&display=swap");
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  font-family: "Raleway", sans-serif;
  font-weight: 400;
}

.app {
  min-height: 100vh;
  position: relative;
  background-color: #f1f1f1;
}
.container {
  padding: 0 20px;
  max-width: 1140px;
  margin: 0 auto;
}
</style>
