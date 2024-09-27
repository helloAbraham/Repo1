<template>
    <h1>This page you can see how one data get edit and render.</h1>
    <div>
      <p>Original Value: {{ abc.value }}</p>
      <p>Copied Value: {{ abcCopy.value }}</p>
      <button @click="updateAbcCopy">Update abcCopy</button>
    </div>
  </template>
  
  <script>
  export default {
    name:"ExampleDataUpdate",
    data() {
      return {
        abc: { value: 'Original Data ready for edit' }, // Original data object
        abcCopy: {}, // Copy of the original data object
      };
    },
    methods: {
      updateAbcCopy() {
        // Copy the contents of abc into abcCopy
        this.abcCopy = { ...this.abc };
        this.abcCopy.value = 'Updated Data from Original Data ready for edit!'; // Modify the copied object
      }
    }
  };
  </script>
  