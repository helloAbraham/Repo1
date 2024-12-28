<template>
    <i :class="iconClass"></i>
  </template>
  
  <script>
  export default {
    name: 'AppIcon',
    props: {
      name: {
        type: String,
        required: true
      },
      size: {
        type: String,
        default: 'lg' // Default size (can be 'sm', 'md', 'lg', '2x', '3x', etc.)
      },
      spin: {
        type: Boolean,
        default: false // Whether the icon should spin (useful for loading states)
      }
    },
    computed: {
      iconClass() {
        // Construct the Font Awesome icon class dynamically
        const baseClass = 'fas'; // 'fas' for solid icons, can be adjusted for other icon types (e.g., 'far' for regular)
        return [
          baseClass,
          `fa-${this.name}`,
          this.size ? `fa-${this.size}` : '',
          this.spin ? 'fa-spin' : ''
        ].join(' ');
      }
    }
  };
  </script>
  
  <style scoped>
  /* You can add specific styles for the AppIcon component here if needed */
  </style>
  