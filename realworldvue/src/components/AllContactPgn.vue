<template>
    <h1>All Contacts</h1>
    <div v-if="loading">Loading images... &nbsp;<i class="fas fa-spinner fa-pulse"></i></div>
    <div v-else>
      <table border="1" cellpadding="10">
        <!-- Table Header -->
        <thead>
          <tr>
            <!-- Render the 5 columns -->
            <th>Id</th>
            <th>Name</th>
            <th>Phone</th>  
            <th>Email</th>
            <th>Message</th>
          </tr>
        </thead>
  
        <tbody>
          <!-- Table Rows below -->
          <tr v-for="(row, rowIndex) in paginatedItems" :key="rowIndex">
            <td>
                {{ row.id }}
            </td>
            <td>
                {{ row.name }}
            </td>
            <td>
                {{ row.phoneNumber }}
            </td>
            <td>
                {{ row.email }}
            </td>
            <td>
                {{ row.message }}
            </td>
          </tr>
        </tbody>
      </table>
  
      <!-- Pagination Controls -->
      <div class="pagination">
        <BaseButtonStyle @click="prevPage" :disabled="currentPage === 1"><i class='fas fa-angle-double-left'></i> &nbsp;Previous</BaseButtonStyle>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <BaseButtonStyle @click="nextPage" :disabled="currentPage === totalPages">Next &nbsp; <i class='fas fa-angle-double-right'></i></BaseButtonStyle>
      </div>
    </div>
  </template>
  
  <script>
import BaseButtonStyle from './BaseButtonStyle.vue';


  export default {
    name:'AllContactPgn',

    components: {
        BaseButtonStyle,
    },
    data() {
      return {
        headers: ['Name', 'Phone', 'Email', 'Message'],  // Only 'URL' header
        tableData: { 
          content: {}, 
          totalPages: 0, 
          currentPage: 0, 
          totalElements: 0 
        },
        currentPage: 1,
        itemsPerPage: 10
      };
    }, 
    mounted() {
      this.fetchData(0);  // Fetch data when the component is mounted (page 0 in API)
    },
  
    computed: {
      // Calculate the total number of pages based on the backend's response
      totalPages() {
        return this.tableData.totalPages;
      },
  
      // Get the paginated items based on the current page
      paginatedItems() {
        return this.tableData.content;  // Directly use content for pagination
      }
    },
  
    methods: {
      // Fetch data from the backend with pagination
      //for localtest
      //http://localhost:8082/api/images/allContact
      //for productioon
      //https://api.abrahamparker.com/api/images/allContact
      async fetchData(page = 0) {
        try {
          const response = await fetch(`https://api.abrahamparker.com/api/images/allContact?page=${page}&size=${this.itemsPerPage}`); // Replace with your actual endpoint
          const data = await response.json();
          this.tableData = data;  // Update the tableData with paginated response
          this.currentPage = page + 1; // Update the current page (API is 0-indexed, Vue is 1-indexed)
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      },
  
      // Go to the next page
      nextPage() {
        if (this.currentPage < this.totalPages) {
          this.fetchData(this.currentPage); // Load the next page
        }
      },
  
      // Go to the previous page
      prevPage() {
        if (this.currentPage > 1) {
          this.fetchData(this.currentPage - 2); // Load the previous page
        }
      }
    },
  
   
  };
  </script>
  
  <style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }
  
  th {
  background-color: #29d8c9;
  text-align: center;
}
  td {
    padding: 10px;
    text-align: left;
  
  }
  
  a {
    color: black;
    text-decoration: none;
  }
  
  a:hover {
  color: blue;
  text-decoration: underline;
  text-decoration-color: rgb(10, 222, 63);
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
  i {
    color: #232929; /* Change the color of icons */
  }
  </style>
  