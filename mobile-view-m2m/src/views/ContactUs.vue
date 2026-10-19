<template>
  <!--
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

      -->
      <section class="headerSize">
      <div >
        <h1>Contact Us</h1>
        <h2>Phone: (718)-450-4150 </h2> 
        
      </div>
    </section>
       <!-- Call to Action -->
    <section class="cta">
      <h2>Be a Part of the Conversation</h2>
      <p>
        Want to share your story or join the discussion?
       
      </p> 
    
    </section>            
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
    padding-top: 155px;
    
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
  padding-top: 155px;
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
  padding: 70px 20px;
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
  margin: 80px auto;
  padding: 80px 30px;
}


  </style>
  