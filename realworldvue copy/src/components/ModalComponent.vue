<template>
    <div v-if="visible" class="modal-overlay">
      <div class="modal-content">
        <header class="modal-header">
          <slot name="header">
            <h3>Default Header</h3>
          </slot>
          <button class="close-button" @click="close">&times;</button>
          <!-- <img :src="imageUrl" alt="Image" /> -->
        </header>
        <main class="modal-body">
          
          <slot>
            Default Body Content
          </slot>
        </main>
        <footer class="modal-footer">
          <slot name="footer">
            <button @click="close">Close</button>
          </slot>
        </footer>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      visible: {
        type: Boolean,
        default: false,
      },
      // imageUrl: {
      // type: String,
      // required: true
      // },
    },
    methods: {
      close() {
        this.$emit('close');
      },
    },
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background: white;
    padding: 20px;
    border-radius: 5px;
    width: 500px;
    max-width: 100%;
  }
  
  .close-button {
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
    margin-left: 100px;
    
  }
  
  .modal-header {
    background-color: rgb(142, 226, 190);
    display: flex;
    justify-content: space-between;
    justify-content: center
  
  }
  .modal-footer {
    background-color: bisque;
    display: flex;
    justify-content: space-between;
    justify-content: center
  }

  .modal-image {
  max-width: 200px;
  max-height: 200px;
}
  </style>
  