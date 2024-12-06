<template>
    <div class="contact-us">
      <form @submit.prevent="handleSubmit" class="contact-form">
        <div class="form-group">
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="form.name" required />
        </div>
        <div class="form-group">
          <label for="phoneNumber">Phone:</label>
          <input type="text" id="phoneNumber" v-model="form.phoneNumber" required />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="form.email" required />
        </div>
        <div class="form-group">
          <label for="message">Message:</label>
          <textarea id="message" v-model="form.message" rows="4" required></textarea>
        </div>
        <button type="submit">Send</button>
      </form>
      <p v-if="responseMessage" :class="responseClass">{{ responseMessage }}</p>
    </div>
        <div class="layout">

        </div>
  </template>
  
  <script>
    import axios from 'axios';
  export default {
    name: 'ContactUs',
    data() {
      return {
        form: {
          name: '',
          phoneNumber:'',
          email: '',
          message: '',
        },
        submissionStatus: '',
        responseMessage: '',
        responseClass: '',
      };
    },
    methods: {
        
        async handleSubmit() {
            try {
        // Define the endpoint for th;e POST request
       // const endpoint = 'https://api.abrahamparker.com/api/images/contact';

        // Use the `fetch` API to send the form data as a POST request
        const response = await axios.post('https://api.abrahamparker.com/api/images/contact', this.form, {
            headers: {
            "Content-Type": "application/json"
          },
        });
        
        //Handle success response 
        this.responseMessage = response.data; //Assume the backend returns a success message
        this.responseClass = "Success";

          // Clear the form data
          this.form.name = "";
          this.form.phone="";
          this.form.email = "";
          this.form.message = "";
      } catch (error) {
        // Handle any errors that occurred during the fetch request
        console.error("There was an error submitting the form: ", error);
        this.responseMessage = "Failed to submit the form. Please try again.";
        this.responseClass = "error";
      }
    }

    }
  };
  </script>
  
  <style scoped>
  .contact-us {
    max-width: 600px;
    margin: 0 auto;
    padding-top: 50px;
    
  }
  
  .contact-form {
    display: flex;
    flex-direction: column;
  }
  
  .form-group {
    margin-bottom: 1em;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 0.5em;
  }
  
  .form-group input,
  .form-group textarea {
    width: 100%;
    padding: 0.5em;
    box-sizing: border-box;
  }
  
  button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 0.5em 1em;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .status {
    margin-top: 1em;
    color: green;
  }

  .headerSize {
  padding-top: 175px;
}
.sizeDown {
  margin-top: 625px;
}

.layout {
  position: relative;
  min-height: 100vh;
}
  </style>
  