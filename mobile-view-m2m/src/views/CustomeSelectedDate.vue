<template>
    <div>
      <input type="date" v-model="selectedDate">
    </div>
  </template>
  
  <script>
  export default {
    name: 'CustomeSelectedDate',
    data() {
      return {
        selectedDate: ''
      };
    },
    mounted() {
      // Set the initial value to December of the current year
      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = 11; // December is 11 (0-indexed) in JavaScript Date object
      const day = 3; // Start from 1st of December
      this.selectedDate = `${year}-${this.formatNumber(month + 1)}-${this.formatNumber(day)}`;
    },
    methods: {
      formatNumber(num) {
        // Helper method to format single digit numbers with leading zero
        return num < 10 ? '0' + num : num;
      }
    }
  };
  </script>
  