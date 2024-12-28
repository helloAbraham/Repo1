<template>
    <div>
      <h1>My Image Gallery</h1>
      <div v-if="loading">Loading images... &nbsp;<i class="fas fa-spinner fa-pulse"></i></div>
      <div v-else>
        <div class="image-gallery">

            <!-- v-for="(row, rowIndex) in paginatedItems" :key="rowIndex"> -->
          <div v-for="image in paginatedItems" :key="imageIndex" class="image-item">
             <!-- Wrap the image in a clickable link -->
          <a :href="image.url" target="_blank" rel="noopener noreferrer">
            <img :src="image.url" :alt="'Image ' + imageIndex" class="image" />
          </a>

          </div>
        </div>
        <!-- Pagination Controller -->
         <div class="pagination">
            <BaseButtonStyle @click="prevPage" :disabled="currentPage === 1"><i class='fas fa-angle-double-left'></i> &nbsp; Previous</BaseButtonStyle>
            <span>Page {{  currentPage }} of {{  totalPages }}</span>
            <BaseButtonStyle @click="nextPage" :disabled="currentPage === totalPages">Next &nbsp; <i class='fas fa-angle-double-right'></i></BaseButtonStyle>
         </div>
      </div>
    </div>
  </template>
  
  <script>
//   import axios from 'axios';
import BaseButtonStyle from './BaseButtonStyle.vue';

  export default {
    name: 'MyImageGallery',
    components: {
        BaseButtonStyle,
    },
    data() {
      return {
        images: {
            content:[],
            totalPages: 0, 
            currentPage: 0, 
            totalElements: 0
        },
        currentPage: 1,
        itemsPerPage: 12,
      //  totalPages:0,
        loading: true,
        // images: [],
        // loading: true,  // For loading state
      };
    },
    computed: {
      // Calculate the total number of pages based on the backend's response
      totalPages() {
        return this.images.totalPages;
      },
  
      // Get the paginated items based on the current page
      paginatedItems() {
        return this.images.content;  // Directly use content for pagination
      }
    },
    methods: {
      async fetchImages(page = 0) {
        //For production 
        //https://api.abrahamparker.com/api/images/findAll
         //https://api.abrahamparker.com/api/images/findAllPagination
        //For local test 'http://localhost:8082/api/images/findAllPagination'
            try {
               
            const response = await fetch(`https://api.abrahamparker.com/api/images/findAllPagination?page=${page}&size=${this.itemsPerPage}`);
            const data = await response.json();
          this.images= data;  // Update the images with paginated response
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
          this.fetchImages(this.currentPage); // Load the next page
        }
      },
  
      // Go to the previous page
      prevPage() {
        if (this.currentPage > 1) {
          this.fetchImages(this.currentPage - 2); // Load the previous page
        }
      }
    },
  
    mounted() {
      this.fetchImages(0);  // Fetch data when the component is mounted (page 0 in API)
    }

        // changePage(newPage) {
        //     if(newPage >= 0 && newPage < this.totalPages){
        //         this.page = new Page;
        //         this.fetchImages(); //Fetch new page data
        //         }
        //     }
        //}
    };
  </script>
  
  <style scoped>
  .image-gallery {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
  }
  
  .image-item {
    width: 100px;
    height: 100px;
  }
  
  .image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 8px;
  }
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

  /* Example of styling the icon */
  i {
    color: #232929; /* Change the color of icons */
  }
  </style>
  