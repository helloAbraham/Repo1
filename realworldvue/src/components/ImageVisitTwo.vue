<template>
  <div>
    <h1>My Image Gallery </h1>
    <div v-if="loading">Loading images...</div>
          <div v-for="(row, rowIndex) in paginatedItems" :key="rowIndex">
                <div class="image-gallery">
                <img :src="row.url" :alt="'row ' + rowIndex" class="image" /> 
               
                <a :href="row.url" target="_blank" rel="noopener noreferrer" />
                 
              </div>
          
              <!-- Render the URL as a clickable anchor tag -->
             
            </div>
      <!-- Pagination Controls -->
      <div class="pagination">
        <BaseButtonStyle @click="prevPage" :disabled="currentPage === 1"><i class='fas fa-angle-double-left'></i> &nbsp; Previous</BaseButtonStyle>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <BaseButtonStyle @click="nextPage" :disabled="currentPage === totalPages"> Next &nbsp; <i class='fas fa-angle-double-right'></i></BaseButtonStyle>
      </div>
    </div>
  </template>
  
  <script>
import BaseButtonStyle from './BaseButtonStyle.vue';


  export default {
    name:'ImageVisitTwo',

    components: {
        BaseButtonStyle,
    },
    data() {
      return {
       
        tableData: { 
          content: [], 
          totalPages: 0, 
          currentPage: 0, 
          totalElements: 0 
        },
        currentPage: 1,
        itemsPerPage: 10,
        loading: true,
      };
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
      //https://api.abrahamparker.com/api/images/listAll?page=${page}&size=${this.itemsPerPage}`
      async fetchData(page = 0) {
        try {
          const response = await fetch(`http://localhost:8082/api/images/listAll?page=${page}&size=${this.itemsPerPage}`); // Replace with your actual endpoint
          const data = await response.json();
          this.tableData = data;  // Update the tableData with paginated response
          this.currentPage = page + 1; // Update the current page (API is 0-indexed, Vue is 1-indexed)
        } catch (error) {
          console.error('Error fetching data:', error);
        } finally {
        this.loading = false;
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
  
    mounted() {
      this.fetchData(0);  // Fetch data when the component is mounted (page 0 in API)
    }
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
  .image-gallery {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
  }
  .image {
    width: 50%;
    height: 50%;
    object-fit: cover;
    border-radius: 8px;
  }
  </style>
  