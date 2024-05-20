<template>
    <div>
        <h1>You hvae been logged out. Redirecting to login ... </h1>
    </div>
</template>

<script>
    export default {
        name: 'LogoutPage',
        data() {
            return {

            }
        },
        created() {
            this.logout();
        },

        methods: {
            logout() {
                //clear user authentication data, such as tokens, 
                localStorage.removeItem('authToken');
                localStorage.removeItem('username');

                //After that Redirect to login page after user click logout
                this.$router.push('/login');
            }
        }
    }
</script>
