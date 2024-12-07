<template>
    <div>
      <h1>Image Table with Pagination</h1>
  
      <table border="1" cellpadding="10">
        
        <thead>
          <tr>
            <th v-for="(header, index) in headers" :key="index">{{ header }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row, index) in tableData.content" :key="index">
            <td v-for="(value, columnIndex) in row" :key="columnIndex">
                <a :href="value" target="_blank">{{ value }}</a>
            </td>
          </tr>
        </tbody>
      </table>
  
      <!-- Pagination Controls -->
      <div class="pagination">
        <BaseButtonStyle @click="changePage('prev')" :disabled="currentPage === 1">Previous</BaseButtonStyle>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <BaseButtonStyle @click="changePage('next')" :disabled="currentPage === totalPages">Next</BaseButtonStyle>
      </div>
    </div>
  </template>
  
  <script>
    import axios from 'axios';
    import BaseButtonStyle from './BaseButtonStyle.vue';
  
  export default {
    name:'ImageList',
    components: {
        BaseButtonStyle
    },
    data() {
      return {
        headers: ['ID','File Name', 'URL', 'Date & Time'], // Example headers
        tableData: { content: [], totalPages: 0, currentPage: 0, totalElements: 0},  // This will hold the paginated data from the backend
        currentPage: 1,
        pageSize: 10,
        totalPages: 0
      };
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      async fetchData() {
            //producton endpoint https://api.abrahamparker.com/api/images/listAll
            //localtest endponint http://localhost:8082/api/images/listAll
        try {
          const response = await axios.get('https://api.abrahamparker.com/api/images/listAll', {
            params: {
              page: this.currentPage - 1, // Spring uses 0-based indexing for pages
              size: this.pageSize
            }
          });
  
          this.tableData = response.data;
          this.totalPages = this.tableData.totalPages; // Set the total number of pages from the backend
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      },
      changePage(direction) {
        if (direction === 'next' && this.currentPage < this.totalPages) {
          this.currentPage++;
        } else if (direction === 'prev' && this.currentPage > 1) {
          this.currentPage--;
        }
        this.fetchData(); // Fetch new page data
      }
    }
  };
  </script>
  
  <style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th, td {
    padding: 10px;
    text-align: left;
  }
  
  th {
    background-color: #f2f2f2;
  }
  
  tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  tr:hover {
    background-color: #f1f1f1;
  }
  
  button {
    margin: 10px;
    padding: 5px 10px;
    cursor: pointer;
  }
  
  button:disabled {
    cursor: not-allowed;
    opacity: 0.5;
  }

/* Pagination styles */
.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pagination button {
  padding: 5px 10px;
  margin: 0 5px;
}

.pagination span {
  margin: 0 10px;
}
  </style>
  