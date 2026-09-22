<template>
    <div>
        <h1>Opps!</h1>
        <h3>The page you're looking for is not here...</h3>
        <BaseButtonStyle>
            <router-link :to="{name: 'HomePage'}">Back to home page</router-link>
        
        <!-- <router-link :to="{name: 'EventList'}">Back to home page</router-link> -->
        </BaseButtonStyle>
    </div>
</template>

<script>
import BaseButtonStyle from '@/components/BaseButtonStyle.vue';

    export default {
        name: 'NotFound',
        components: {
            BaseButtonStyle,
        }
    }
</script>