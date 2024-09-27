<template>
    <div id="app">
      <div class="container">
        <header>
          <h1>My Responsive Page</h1>
        </header>
        <main>
          <p>Content goes here. This page is centered and responsive.</p>
        </main>
        <footer>
          <p>Footer content</p>
        </footer>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ResponsivePage',
  };
  </script>
  
  <style scoped>
  /* Base styles */
  body {
    margin: 0;
    font-family: Arial, sans-serif;
  }
  
  #app {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f0f0f0;
  }
  
  .container {
    display: grid;
    grid-template-rows: auto 1fr auto;
    width: 100%;
    max-width: 1200px; /* Standard width */
    margin: 0 auto; /* Center the container */
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  /* Header, main, and footer styling */
  header, footer {
    text-align: center;
    padding: 10px;
    background-color: #333;
    color: #fff;
  }
  
  main {
    padding: 20px;
  }
  
  /* Responsive styles */
  @media (max-width: 768px) {
    .container {
      padding: 10px;
    }
  
    header, footer {
      padding: 5px;
    }
  
    main {
      padding: 10px;
    }
  }
  </style>
  