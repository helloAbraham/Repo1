<template>
    <div>
      <p v-if="isValid">Mounted value is valid: {{ mountedValue }}</p>
      <p v-else>Mounted value is not valid: {{ mountedValue }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ValidationComponent',
    data() {
      return {
        mountedValue: 'abc'
      };
    },
    mounted() {
      // Perform validation on the mounted value
      this.validateMountedValue();
    },
    methods: {
      validateMountedValue() {
        // Example validation logic
        if (this.mountedValue === '') {
          this.isValid = true;
        } else {
          this.isValid = false;
        }
      }
    }
  };
  </script>
  