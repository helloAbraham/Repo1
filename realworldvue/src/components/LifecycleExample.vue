<template>
    <div>
      <p>Current Lifecycle Hook: {{ currentHook }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: 'LifecycleExample',
    data() {
      return {
        currentHook: ''
      };
    },
    beforeCreate() {
      this.currentHook = 'beforeCreate';
      console.log('Component is about to be created');
    },
    created() {
      this.currentHook = 'created';
      console.log('Component has been created');
    },
    beforeMount() {
      this.currentHook = 'beforeMount';
      console.log('Component is about to be mounted');
    },
    mounted() {
      this.currentHook = 'mounted';
      console.log('Component has been mounted');
    },
    beforeUpdate() {
      this.currentHook = 'beforeUpdate';
      console.log('Component is about to be updated');
    },
    updated() {
      this.currentHook = 'updated';
      console.log('Component has been updated');
    },
    beforeUnmount() {
      this.currentHook = 'beforeUnmount';
      console.log('Component is about to be unmounted');
    },
    unmounted() {
      this.currentHook = 'unmounted';
      console.log('Component has been unmounted');
    },
    errorCaptured(err, vm, info) {
      console.error('Error captured in component:', err);
      console.error('Error info:', info);
    }
  };
  </script>
  