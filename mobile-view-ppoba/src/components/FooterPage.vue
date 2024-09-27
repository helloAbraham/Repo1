<template>
    <footer>
      <!-- Your footer content goes here -->
      <p>Copyright&copy; {{ currentYear }} Patriot People of Bangladesh in America, NY</p>
    </footer>
  </template>
  
  <script>
  export default {
    name:'FooterPage',
    data() {
        return {
            currentYear: new Date().getYear()
        };
    },

    mounted() {
        setInterval(() => {
            this.currentYear = new Date().getFullYear()
        }, 1000);
    }
  }
  </script>
  
  <style scoped>
  /* Add styles for your footer here */
  footer {
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px 0;
    width: 100%;
    margin-top: 960px;
    height: 3 rem; 

  
  }

  
.layout {
  position: relative;
  min-height: 100vh;
}

</style>

  