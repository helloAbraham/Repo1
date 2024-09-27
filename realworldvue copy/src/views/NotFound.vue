<template>
    <div>
        <h1>Opps!</h1>
        <h3>The page you're looking for is not here...</h3>
        <router-link :to="{name: 'EventList'}">Back to home page</router-link>
    </div>
</template>

<script>
    export default {
        name: 'NotFound',
    }
</script>