<template>
  <div class="about">
    <h1 style="text-align: center;">Abraham Parker</h1>
    <h2>A site for alarm to better the world</h2>
    <ChildComponent></ChildComponent>
  </div>
</template>

<script>
import ChildComponent from '@/components/ChildComponent.vue';

  export default {
    name:'AboutView',

    components: {
      ChildComponent
    }

  }
</script>
