<template>
  <div class="gap-from-top">
    <h1>This is an about page</h1>
  </div>
  <div>
    <InputComponent v-model="name"/>
  </div>
</template>


<script>
import InputComponent from '@/components/InputComponent.vue';
export default{
  name:'AboutView',

  components:{
    InputComponent,
  },
  data(){
    return{
      name: '',
    }
  }
}
</script>

<style scoped>
.gap-from-top{
  padding-top: 75px;
}

</style>