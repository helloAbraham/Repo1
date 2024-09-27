<template>
    <div class="contact-us">
      <h1>Contact Us</h1>
      <h2>Phone: (718)-450-4150, (718)795-8628</h2>
      <form @submit.prevent="handleSubmit" class="contact-form">
        <div class="form-group">
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="form.name" required />
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
      <p v-if="submissionStatus" class="status">{{ submissionStatus }}</p>
    </div>
        <div class="layout">

        </div>
  </template>
  
  <script>
  export default {
    name: 'ContactUs',
    data() {
      return {
        form: {
          name: '',
          email: '',
          message: ''
        },
        submissionStatus: ''
      };
    },
    methods: {
        handleSubmit() {
        // Example: send form data to an API endpoint
        //When I will create Springboor project contactus
        fetch('https://example.com/api/contact', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.form)
        })
            .then(response => response.json())
            .then(data => {
                this.submissionStatus = 'Your message has been sent successfully!';
                this.form.name = '';
                this.form.email = '';
                this.form.message = '';
        })
            .catch(error => {
            console.error('Error:', error);
            this.submissionStatus = 'There was an error sending your message.';
        });
    }

    }
  };
  </script>
  
  <style scoped>
  .contact-us {
    max-width: 600px;
    margin: 0 auto;
    padding-top: 175px;
    
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
  