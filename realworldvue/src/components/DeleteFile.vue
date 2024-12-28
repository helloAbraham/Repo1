<template>
    <div class="delete-file">
      <h2>Delete File</h2>
      <BaseInput 
        v-model="url" 
        type="text" 
        placeholder="Enter the image URL" 
        class="url-input"
      />
      <BaseButtonStyle @click="deleteFile" :disabled="loading">
        Delete File
      </BaseButtonStyle>
  
      <!-- Success/Error Message -->
      <p v-if="message" :class="messageType">{{ message }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
import BaseButtonStyle from './BaseButtonStyle.vue';
import BaseInput from './BaseInput.vue';
  
  export default {
    name:'DeleteFile',
    components: {
        BaseButtonStyle,
        BaseInput,
    },

    data() {
      return {
        url: '', // URL input by user
        message: '', // Message to show user (success or error)
        messageType: '', // Message type for styling (success/error)
        loading: false, // Flag to show loading state
      };
    },
    methods: {
      // Method to delete the file by URL
      async deleteFile() {
        if (!this.url) {
          this.setMessage('Please provide a URL', 'error');
          return;
        }
  
        this.loading = true;
        try {
          const response = await axios.delete('https://api.abrahamparker.com/api/images/delete', {
            params: { url: this.url },
          });
  
          this.setMessage(response.data, 'success');
          this.url = '';
        } catch (error) {
          console.error(error);
          this.setMessage('Error occurred while deleting the file', 'error');
        } finally {
          this.loading = false;
        }
      },
  
      // Helper method to set success/error messages
      setMessage(msg, type) {
        this.message = msg;
        this.messageType = type;
      },
    },
  };
  </script>
  
  <style scoped>
  .delete-file {
    max-width: 400px;
    margin: 0 auto;
    text-align: center;
  }
  
  .url-input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    font-size: 16px;
  }
  
  button {
    padding: 10px 20px;
    font-size: 16px;
    background-color: #4caf50;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  button:disabled {
    background-color: #ccc;
  }
  
  .success {
    color: green;
  }
  
  .error {
    color: red;
  }

  /* Example container for text with Apple-like tray shadow effect */
.apple-shadow-text {
    font-family: 'Arial', sans-serif; /* Choose your preferred font */
    font-size: 48px; /* Large font size for visibility */
    color: #fff; /* White text color (you can customize this) */
    text-align: center;
    padding: 20px;
    border-radius: 10px; /* Rounded corners for a more modern look */
    
    /* Old Apple Icon Tray Shadow Effect */
    text-shadow: 
        0 1px 2px rgba(0, 0, 0, 0.5),   /* Soft shadow at the bottom */
        0 2px 4px rgba(0, 0, 0, 0.4),   /* Slightly larger shadow */
        0 4px 6px rgba(0, 0, 0, 0.3);   /* More spread out shadow */
    
    background-color: #2c3e50; /* Dark background color (you can change it) */
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* Subtle outer shadow for 3D effect */
}

/* Optional hover effect */
.apple-shadow-text:hover {
    transform: translateY(-25px); /* Slight upward movement on hover */
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3); /* Enhanced shadow on hover */
}

  </style>
  