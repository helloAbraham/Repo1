<!-- Update data which is mounted in the textfield then update the value after that -->
<template>
    <h1>This page you can see how one data get edit and render.</h1>
    <div>
        <!-- Input field to edit the data -->
        <h2>Here is the textfield data you can update </h2>
        <input v-model="textFieldData" @input="updateData" placeholder="Enter some text" />
        <!-- Display the update data -->
         <p>Updated Data {{ textFieldData }}</p>
         <button @click="saveData">Save</button>
    </div>
    <div>
      <p>Original Value: {{ abc.value }}</p>
      <p>Copied Value: {{ abcCopy.value }}</p>
      <button @click="updateAbcCopy">Update abcCopy</button>
    </div>
  </template>
  
  <script>
  export default {
    name:"ExampleDataUpdateRender",
    data() {
      return {
        abc: { value: 'Original Data ready for edit' }, // Original data object
        abcCopy: {}, // Copy of the original data object
        textFieldData: 'This is the old data',
        textFieldDataUpdate: {},
      };
    },
    async created() {
        await this.fetchData();
    },
    methods: {
      updateAbcCopy() {
        // Copy the contents of abc into abcCopy
        this.abcCopy = { ...this.abc };
        this.abcCopy.value = 'Updated Data from Original Data ready for edit!'; // Modify the copied object
      },

      fetchData() {
        // Simulate an API call or other data retrieval
        // In a real application, this would likely be an asynchronous operation (e.g., using axios)
        this.textFieldData = 'This is the fetched data';
        
        },
      saveData() {
    
        this.textFieldData='',
        console.log('Data updated: ', this.textFieldData);
      }
    }
  };
  </script>
  