<template>
    <BaseButtonStyle @click="showImage = !showImage">Click Me </BaseButtonStyle>
   
      <img v-if="showImage" :src="image" alt="Photo from Assets" />
   

</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';

    export default {
        name:'PictureImg',
        components: {
            BaseButtonStyle,
        },
        data() {
            return {
                showImage: false,
                image: require('@/assets/myPhoto.jpeg'),
            }
        },

 

        
    }
</script>

<style scoped>

</style>