<template>
   
    <div class="resume">
        
      <header>
        <!-- <h1>{{ name }}</h1>
        <p>{{ title }}</p> -->
        <TwoColumnLayout />
       
      </header>
      </div>
  </template>
  
  <script>
  import TwoColumnLayout from "../components/TwoColumnLayout.vue";
  export default {
    name: 'HomePage',
    components: {
        TwoColumnLayout,
    },
    data() {
      return {
        name: 'Abraham Farhad Parker',
        title: 'Software Engineer',
        email: 'john.doe@example.com',
        phone: '(123) 456-7890',
        linkedin: 'https://www.linkedin.com/in/johndoe/',
        experience: [
          {
            title: 'Senior Developer',
            company: 'Tech Solutions Inc.',
            dateRange: 'Jan 2020 - Present',
            description: 'Developing and maintaining web applications using modern technologies.'
          },
          {
            title: 'Junior Developer',
            company: 'WebWorks Ltd.',
            dateRange: 'Jun 2018 - Dec 2019',
            description: 'Assisted in building web applications and debugging issues.'
          }
        ],
        education: [
          {
            degree: 'Bachelor of Science in Computer Science',
            institution: 'State University',
            dateRange: 'Sep 2014 - May 2018'
          }
        ],
        skills: ['JavaScript', 'Vue.js', 'HTML/CSS', 'Node.js', 'Git']
      };
    }
  };
  </script>
  
  <style scoped>
  .resume {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  header {
    text-align: center;
    margin-bottom: 20px;
  }
  
  header h1 {
    margin: 0;
  }
  
  .contact-info ul,
  .experience ul,
  .education ul,
  .skills ul {
    list-style-type: none;
    padding: 0;
  }
  
  .contact-info li,
  .experience li,
  .education li,
  .skills li {
    margin-bottom: 10px;
  }
  
  a {
    color: #1e90ff;
    text-decoration: none;
  }
  </style>
  