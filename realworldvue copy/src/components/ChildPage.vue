<template>
    <!-- Child.vue-->
    <div>
        <button @click="emitEvent">Click Me!</button>
    </div>
</template>

<script>
    export default {
        name:'ChildPage',

        methods: {
            emitEvent() {
                //emiting a custom event named 'custome-event with data'
                this.$emit('custom-event', 'Hello from child');
            }
        }
    }
</script>