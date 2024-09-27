<template>
    <div class="grid-container">
      <header class="grid-header">Abraham Farhad Parker</header>
      <aside class="grid-sidebar">Sidebar</aside>
      <!-- <main class="grid-main">Main content</main> -->
       <CreateAlertSelectVersion />
      <footer class="grid-footer">Footer</footer>
    </div>
  </template>
  
  <script>
  import CreateAlertSelectVersion from "@/components/CreateAlertSelectVersion.vue";
  export default {
    name: 'GridLayout',
    components: {
      CreateAlertSelectVersion,
    }
  };
  </script>
  
  <style scoped>
  /* Grid Container */
  .grid-container {
    display: grid;
    grid-template-rows: auto 1fr auto;
    grid-template-columns: 1fr 3fr;
    gap: 10px;
    min-height: 100vh;
  }
  
  /* Grid Items */
  .grid-header {
    font-size: 25px;
    grid-column: 1 / -1;
    background-color: #87f0bf;
    color: rgb(216, 44, 99);
    text-align: center;
    padding: 10px;
  }
  
  .grid-sidebar {
    background-color: #ddd;
    padding: 10px;
  }
  
  .grid-main {
    background-color: #fff;
    padding: 10px;
  }
  
  .grid-footer {
    grid-column: 1 / -1;
    background-color: #333;
    color: white;
    text-align: center;
    padding: 10px;
  }
  
  /* Responsive Styles */
  @media (max-width: 768px) {
    .grid-container {
      grid-template-columns: 1fr;
    }
    
    .grid-sidebar {
      display: none;
    }
  }
  
  @media (max-width: 480px) {
    .grid-header, .grid-footer {
      padding: 8px;
    }
  
    .grid-main {
      padding: 8px;
    }
  }
  </style>
  