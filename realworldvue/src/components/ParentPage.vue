<template>
    <div>
        <p> {{ message }} </p>
        <ChildPage @custom-event = "handleCustomEvent" />
    </div>
</template>

<script>
import ChildPage from './ChildPage.vue';

    export default {
        name:'ParentPage',

        components: {
            ChildPage,
        },
        data() {
            return {
                massege: '',
            }
        },
        methods: {
            handleCustomEvent(data) {
                //Handling the custome event emitted by the child component
                this.message = data;
            }
        }
    }
</script>

<style scoped>

</style>