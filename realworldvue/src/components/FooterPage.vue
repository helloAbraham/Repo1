<template>
    <footer>
      <!-- Your footer content goes here -->
      <p>Copyright&copy; {{ currentYear }} Abraham Parker, NY</p>
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
    padding: 20px 0;
    width: 100%;
    height: 3.5rem; 

  
  }
  </style>
  