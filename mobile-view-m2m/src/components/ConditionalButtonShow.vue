<template>
    <div class="topPadding">
        <button v-if="!loggedIn" @click="login">Login</button>
        <button v-else @click="logout">Logout</button>
    </div>
</template>

<script>
    export default {
        name:'ConditionalButtonShow',

        data() {
            return {
                loggedIn: false,
            }
        },

        methods: {
            login() {
                //logic
                this.loggedIn = true;
            },

            logout() {
                //logic
                this.loggedIn = false;
            }
        }
    }
</script>

<style scoped>
    .topPadding {
    padding-top: 200px;
}
</style>