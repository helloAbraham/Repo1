<template>
    <section class="headerSize">
    
    </section>
      
    <div class="contact-us">
        <div >
          <h1>Contact Us</h1>
          
         <div class="email-container">
            <a
              href="https://mail.google.com/mail/?view=cm&fs=1&to=admin@m2musa.tv"
              target="_blank"
              rel="noopener noreferrer"
              class="email-button"
            >
              admin@m2musa.tv
            </a>
          <a
            href="tel:+17184504150"
            class="email-button"
            >
            718-450-4150
            </a>
            <a
              href="https://mail.google.com/mail/?view=cm&fs=1&to=nm2musatv@gmail.com"
              target="_blank"
              rel="noopener noreferrer"
              class="email-button"
            >
              m2musatv@gmail.com
            </a>
          </div>
        </div>

         <!-- Call to Action -->
    <section class="cta">
      <h2>Be a Part of the Conversation</h2>
      <p>
        Want to share your story or join the discussion?
       
      </p> 
    
    </section>   
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
            <BaseButtonStyle
            type="submit"
            style="display: block; margin: 0px auto 20px;"
            >
            <i class="fa fa-paper-plane"></i>
            &nbsp; Send
            </BaseButtonStyle>

      </form>
      <p v-if="responseMessage" :class="responseClass">{{ responseMessage }}</p>
    </div>
        <div class="layout">

    </div>
  </template>
  
  <script>
    import axios from 'axios';
    import BaseButtonStyle from '@/components/BaseButtonStyle.vue';
  export default {

    name: 'ContactUsNew',
    components: {
        BaseButtonStyle,
    },
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
              //localtest
              //http://localhost:8082/api/images/contact

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
          this.form.phoneNumber="";
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
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    padding-bottom: -100px;
    margin-top:10px;
    text-align: center;
    
    
  }
  
  .contact-form {
    display: flex;
    flex-direction: column;
    text-align:left;
  }
  
  .form-group {
    margin-bottom: .5em;
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
    padding-top: 10px;
    padding-bottom: 10px;
    margin-bottom: 0;
  }

  .cta {
    padding: 15px 20px; margin-bottom: 0;
  }

.sizeDown {
  margin-top: 0;
}

.layout {
  position: relative;
  min-height: 100vh;
}

/* Example of styling the icon */
i {
  color: #232929; /* Change the color of icons */
}

  /* Submit button styling */
:deep(.btn) {
  flex: none !important;
  padding: 10px 24px !important;
  font-size: 16px !important;
  margin-top: 5px;
  margin-bottom: 20px;
}


  .Success {
    color: green;
    margin-top: 1em;
  }

  .error {
    color: red;
    margin-top: 1em;
  }
  .headerSize {
  padding-top: 35px;
  
}

 .contact-us {
    max-width: 600px;
    margin: 0 auto;
    padding-top: 35px;
    
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
  padding-top: 85px;
  
}
.sizeDown {
  margin-top: 625px;
}

.layout {
  position: relative;
  min-height: 100vh;
}
.home {
  font-family: "Poppins", sans-serif;
  background-color: #0f172a; /* deep navy */
  color: #e5e7eb;
  padding-top: 175px;
}

/* HERO */
.hero {
  background: radial-gradient(circle at top, #0dd743, #1e1b4b);
  text-align: center;
  padding: 15px 10px;
  border-top-right-radius: 60px;
  border-bottom-left-radius: 60px;
  border-bottom-left-radius: 60px;
  border-top-right-radius: 60px;
}

.title {
  font-size: 3.6rem;
  font-weight: 800;
  letter-spacing: 3px;
  color: #ffffff;

  /* White glow / shadow effect */
  text-shadow:
    5px 2px 4px rgba(227, 17, 196, 0.4),
    0 6px 20px rgba(239, 247, 250, 0.25);
}

.tagline {
  font-size: 1.25rem;
  margin: 20px 0 35px;
  color: #e0e7ff;
}

/* BUTTONS */
.primary-btn {
  background: #facc15;
  color: #1e1b4b;
  padding: 14px 35px;
  border-radius: 30px;
  font-weight: 600;
  transition: all 0.3s ease;
}

.primary-btn:hover {
 background: #ff9800;
  color: #000;
}

.secondary-btn {
  background: linear-gradient(135deg, #d70d97, #fc06ec);
  color: #fff;
  padding: 14px 35px;
  border-radius: 30px;
  font-weight: 600;
  text-decoration: none;   /* removes underline */

}

/* SECTIONS */
section {
  padding: 10px 20px;
  max-width: 1100px;
  margin: auto;
}

.about,
.cta {
  text-align: center;
}

.about h2,
.episodes h2,
.cta h2 {
  font-size: 2rem;
  margin-bottom: 20px;
  color: #facc15;
}

.about p,
.cta p {
  max-width: 750px;
  margin: auto;
  font-size: 1.05rem;
  color: #c7d2fe;
}

/* EPISODES */
.episode-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 25px;
  margin-top: 40px;
}

.episode-card {
  background: linear-gradient(180deg, #1e293b, #020617);
  border-radius: 16px;
  padding: 25px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.4);
  transition: all 0.3s ease;
}

.episode-card:hover {
  transform: translateY(-6px) scale(1.02);
}

.episode-card h3 {
  color: #a5b4fc;
  margin-bottom: 12px;
}

.episode-card p {
  color: #d1d5db;
  font-size: 0.95rem;
}

.date {
  display: inline-block;
  margin-top: 15px;
  padding: 6px 14px;
  background-color: #7c3aed;
  color: #fff;
  border-radius: 20px;
  font-size: 0.8rem;
}

/* CTA */
.cta {
  background: linear-gradient(135deg, #020617, #1e1b4b);
  border-radius: 40px;
  margin: 10px auto;
  padding: 10px 30px;
}

.email-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  min-height: 48px;
  padding: 0 22px;
  border-radius: 10px;
  background: #e42b3b;
  color: #fff;
  font-weight: 750;
  text-decoration: none;
}

.email-button:hover {
  background: #c8202f;
}

.email-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
}
</style>
  