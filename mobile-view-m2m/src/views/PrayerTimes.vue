<template>
    <div class="sizeDown">
        <MarqueeStyle/> <br/>
          <EventList/>
    </div>
</template>

<script>
import EventList from '../views/EventList.vue';
import MarqueeStyle from '@/components/MarqueeStyle.vue';
    export default {
        name: 'PrayerTimes',
        components: {
            EventList,
            MarqueeStyle,
        },
        data() {
            return {

            }
        },

    }
</script>

<style scoped>
.sizeDown {
    padding-top: 125px;
}
</style>