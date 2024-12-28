<template>
    <div>
      <button>
        <AppIcon name="home" size="2x" />
        Go to Home
      </button>
  
      <AppIcon name="user" size="3x" spin />
    </div>
  </template>
  
  <script>
  import AppIcon from './components/AppIcon.vue';
  
  export default {
    components: {
      AppIcon
    }
  };
  </script>
  
<style lang="css" scoped>
/* Example of styling the icon */
i {
  color: #007bff; /* Change the color of icons */
}

</style>