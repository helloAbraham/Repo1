<template>
    <div>
      <h1>Parent Component</h1>
     
      <!-- Passing data as a prop to ChildComponent -->
      <ChildComponent v-on="$listeners" v-bind="$attrs" :message="parentMessage" />
    </div>
  </template>
  
  <script>
  import ChildComponent from './ChildComponent.vue';
  
  export default {
    name: 'ParentComponent',
    components: {
      ChildComponent
    },
    data() {
      return {
        parentMessage: 'Hello from Parent Component!' // This data will be passed to the child
      };
    }
  }
  </script>
  