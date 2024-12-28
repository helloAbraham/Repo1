<template>
    <div class="container">
      <div class="left-card">
        <img class="photoSize" src="../assets/myPhoto.jpeg" alt="ResumePhoto" />
        <!-- Left column content goes here -->
        <h3>Abraham Parker</h3>
        <p>Software Engineer<br>United States Postal Service<br>St. Louis, MO 63180 <br>abraham.parker16@gmail.com<br>admin@abrahamparker.com<br>(718) 450-4150</p>
        <p>LinkedIn: linkedin.com/in/abraham-parker-55b186130/</p>
        <!-- <form method="get" action="AbrahamResumeFed_Updated_24.docx">
            <BaseButtonStyle>Download Resume as .docx</BaseButtonStyle>
        </form> -->
        
        <div>
             <a :href="fileUrl" download="AbrahamResumeFed_Updated_24.docx">
            
                <BaseButtonStyle><i class="fas fa-file-word"></i> Download Resume as .docx</BaseButtonStyle>
            </a>
        </div>

        <div>
             <a :href="fileUrlPdf" download="AbrahamResumeFed_Updated_24.pdf">
            
                <BaseButtonStyle><i class="fas fa-file-pdf"></i> Download Resume as .pdf</BaseButtonStyle>
            </a>
        </div>
           
        <div>
            <a href="pageUrl_1">
                <BaseButtonStyle>Linux Command Practice</BaseButtonStyle>
            </a>
        </div>
       
      </div>
      <div >
        <div class="card">
          <h3>Work Experience</h3>
          <p>Dedicated and innovative Software Developer with 5 years of experience in designing, developing, and maintaining robust applications. Proficient in various programming languages and frameworks, with a strong focus on delivering high-quality software solutions that meet business needs. Experienced in agile methodologies and collaborative team environments. Offering technical expertise in programming analysis, application analysis, and design. Excellent team player with in-depth knowledge of development tools and languages. Energetic computer science specialist skilled in leadership, with a strong foundation in coding, math, logic, and cross-platform. Seeking to leverage solid skills in collaboration, communication, and development as a programmer.</p>
          
        </div>
        <br>
        <div class="card">
          <h3 style="text-decoration: underline; text-decoration-color: #21ff21;">Skills Highlights</h3>
            <div>
                <table>
                <!-- Table Header -->
                <thead>
                    <tr>
                    <th v-for="header in headers" :key="header">{{ header }}</th>
                    </tr>
                </thead>
                <!-- Table Body -->
                    <tbody>
                        <tr v-for="row in tableData" :key="row.id">
                        <td v-for="(value, key) in row" :key="key">{{ value }}</td>
                        </tr>
                    </tbody>
                </table>
        
            </div>
          
        </div>
        <br>
        <div class="card">
          <p style="text-decoration: underline; text-decoration-color: blue; text-shadow:0 0 0.5px blue;"><strong> Software Engineer | Full-Stack | @ USPS </strong></p>
            <div>
                <p>September 2022 - Present</p>
                <p>Full-Stack Developer, Java Developer in Backend and front-end working in Vue.Js</p>
            </div>
          
        </div>
        <br>
        <div class="card">
          <p style="text-decoration: underline; text-decoration-color: #21ff21; text-shadow:0 0 2px #21ff21;"><strong> Software Engineer | Java | React@ Infosys </strong></p>
            <div>
                <p>March 2021 - September 2022</p>
                <p>Full-Stack Developer, Java Developer in Backend and front-end working in React.Js</p>
            </div>
          
        </div>
        <br>
        <div class="card">
          <p style="text-decoration: underline; text-decoration-color: #21ff21; text-shadow:0 0 2px #21ff21;"><strong> Web Developer / dhs.edu.bd
        </strong></p>
            <div>
                <p>Mar 2016 - Dec 2020</p>
                <p>Web develper. Design web form for student and teacher communicate. Result published, Friendly user interface easy to read</p>
            </div>
          
        </div>
        <br>
        <div class="card">
          <p style="text-decoration: underline; text-decoration-color: #21ff21; text-shadow:0 0 2px #21ff21;"><strong>Education and Certification
        </strong></p>
            <div>
                
            </div>
            <div class="card">
                <p><strong> BMCC, NYC, NY </strong></p>
                <p>2010 - 2013</p>
                <p>Associate Degree in Applied Science</p>
            </div>
            <br>
            <div class="card">
                <p><strong> QUEENS COLLEGE, NYC, NY </strong></p>
                <p>2014 - 2019</p>
                <p>Bachelor Degree in Computer Science</p>
            </div>
            <br>
            <div class="card">
                <p><strong> Webster University, St. Louis, MO </strong></p>
                <p>2024 - Continue...</p>
                <p>Cybersecurity Operations (MS)</p>
            </div>
        </div>
        
        <br>
      </div>
      
      <br>
     
    </div> 
    <footer>
        <p>Copyright&copy; {{ currentYear }} Abraham Parker, NY</p>
    </footer>
  </template>
  
  <script>
  import BaseButtonStyle from '@/components/BaseButtonStyle.vue';
  import FooterPage from '@/components/FitraPage.vue';
  export default {
    name: 'TwoColumnLayout',
    components: {
        BaseButtonStyle,
        FooterPage,
    },
    data() {
        return {
            fileUrl: 'https://www.abrahamparker.com/AbrahamResumeFed_Updated_24.docx',
            fileUrlPdf: 'https://www.abrahamparker.com/AbrahamResumeFed_Updated_24.pdf',
            pageUrl_1: 'https://www.abrahamparker.com/linuxCommand/lcc.html',

            headers: ['Group', 'Details..'],
            tableData: [
                { id:'J2EE',  name:'Apache NetBeans, Servlets, JSP, JDBC, EJB, JNDI, JMS, RMI,JSF.' },
                { id:'Web Tech..', name:'HTML 5, CSS, JAVASCRIPT, jQuery, JSON, AJAX, React JS, Vue.js'},
                { id:'Framework', name:'Client-Server, MVC, J2EE, Springboot, Spring, Hibernate, Swing' },
                { id:'Databases', name:'My SQL, Mongo DB, Oracle 11g. TSQL.'},
                { id:'Cloud Services', name:'Aws, Microsoft Azure, Google cloud.' },
                { id:'Query Languages', name:'SQL, PL/SQL,TSQL'},
                { id:'IDE & Tools', name:'intellij IDEA, Eclipse, Maven, Notepad++, WebStorm, Sublime Text 3, Brackets 1.8, Microsoft Visual Studio Code, GIT, SVN,'},
                { id:'J2EE', name:'Apache Tomcat 5.5/6.0, WebLogic Application Server, IBM Web Sphere' },
                { id:'Methodologies', name:'UML, Waterfall, Scrum, Agile, Design Patterns, Testing'},
            ],
            currentYear: new Date().getYear(),
            screenWidth: window.innerWidth,
        }
    },
    mounted() {
        setInterval(() => {
            this.currentYear = new Date().getFullYear()
        }, 1000);
        window.addEventListener('resize', this.handleResize);
    },

    beforeDestroy() {
        //Clean up the event listner
        window.removeEventListener('resize', this.handleResize);
    },

    methods: {
        handleResize() {
            this.screenWidth = window.innerWidth;
        }
    },

    computed: {
        isMobile(){
            return this.screenWidth <= 768;
        }
    }
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
  
    justify-content: center;
    align-items: left;
    min-height: 100vh;
    background-color: #f0f0f0;
  }
 /* Responsive styles */
@media (max-width: 768px) {
  .container {
    
    flex-direction: column;
    padding: 10px;
  }

  header, footer {
    padding: 5px;
  }

  main {
    padding: 10px;
  }
}
/* Header, main, and footer styling */
header, footer {
  text-align: center;
  padding: 1px;
  background-color: #333;
  color: #fff;
}

main {
  padding: 20px;
}

/* Responsive styles */
@media (max-width: 768px) {
  .container {
    flex-direction: column;
    padding: 5px;
  }

  header, footer {
    padding: 5px;
  }

  main {
    padding: 10px;
  }
}

  .left-column {
    display: block;
    /* margin-top: 18px; */
    width: 16in; /* 6 inches wide */
    padding: 20px;
    box-sizing: border-box;
    margin-left: 0px;
    background-color: rgb(115, 222, 234);
    text-align: left;
    border: 1px solid #ddd;
    border-radius: 12px;
  }

  .left-card {
    background-color: rgb(115, 222, 234);
    display: block;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  .photoSize {
    /* display: flex;
    width: 100%;
    height: 145px; */
  }
  
  .right-column {
    
    width: 36in; /* 18 inches wide */
    margin-top: 0px;
    padding: 20px;
    box-sizing: border-box;
    margin-right: 0px;
  }
  
  .card {
    display: block;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    background-color: #fcfcd0;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .card h3 {
    display: block;
    margin-top: 0;
  }
  
  .card p {
    display: block;
    margin-bottom: 16px;
    text-align: left;
  }

  table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
th {
  background-color: #f4f4f4;
}
  </style>
  