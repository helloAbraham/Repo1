<template>
    <div class="sizeDown">
        <h1>Opps!</h1>
        <h3>The page you're looking for is not here...</h3>
        <router-link :to="{name: 'Home'}">Back to home page</router-link>
    </div>
</template>

<script>
    export default {
        name: 'NotFound',
    }
</script>

<style scoped>
.sizeDown {
    padding-top: 150px;
   text-align: center;
    padding-bottom: 350px;
}
</style>