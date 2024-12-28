<template>

    <div class="body">
      <MarqueeStyle />
      <h2 style="margin-top: 0px;">Create an Alert</h2>
      <p style="font-size: 25px; margin-top: 0px; accent-color: purple; "><strong>Enter Time for Alert </strong>&ensp;</p>
      <BaseSelect 
        style=" margin-left:10px;  margin-top:20px; height:35px;  width:75px; border-color:firebrick"
        :options="selectedHours"
        v-model="myTimeHour.hour"
        label="Select Hour " 
      /> 
      &ensp; 
      <BaseSelect 
        style=" margin-left:10px; margin-top:20px; height:35px;  width:75px; border-color: firebrick"
        
        :options="selectedMinutes"
        v-model="myTimeMin.min"
        label="Select Minute "
      />
      <br>
  
      <BaseSelect
      style=" margin-left:10px;  margin-top:20px; width: 435px; height:35px;  border-color:darkmagenta"
        :options="categories"
        v-model="myEvent.category"
        label="Select Your Message for the Alert or type" 
      />
      <div v-if="showCustomeTextbox">
      <BaseInput  v-model="alertMessage"  placeholder="Enter your custom alert message" size="50" background-color="FFFDD0" />
      </div>
      <BaseButtonStyle @click="createAlert">
        <i class="fa fa-paper-plane"></i>
           &nbsp; Create Alert
      </BaseButtonStyle>
  
      <ModalComponent 
      :visible="showModal"
      :imageUrl="modalImageUrl"
       @close="stopSound">
        <h3>{{ modalMessage }}</h3>
        <button @click="stopSound">OK</button>
      </ModalComponent>
    </div>

    <p>Total Time {{ this.myTimeHour.hour }} &ensp; Hrs {{ this.myTimeMin.min }} &ensp; Mins &ensp; Total Minute: {{ this.totalMinutes }}</p>
    <!-- Using the Modal Component -->
     <div>
        <ModalComponent :visible="showModal" @close="showModal = false">
          
      <template #header>
        <h3>Scheduled Time </h3>
      </template>
      <template #default>
        <h3>{{ modalMessage }}</h3>
        <BaseButtonStyle @click="stopSound">STOP</BaseButtonStyle>
        <ul>
        <li v-for="(project, index) in scheduledProjects" :key="index">
          Alarm goes off: {{ project.scheduledTime }}
        </li>
      </ul>
      </template>
      <template #footer>
        <BaseButtonStyle @click="showModal = false">Close</BaseButtonStyle>
      </template>
        </ModalComponent>
     </div>
     

     <!-- For confirm model -->
     <div>
        <ModalComponent :visible="showConfirm" @close="showConfirm = false" :imageSrc="imageUrl">
      <template #header>
        <h3>Alerm Setup Confirm</h3>
      </template>
      <template #default>
        <ul>
          <li v-for="(project, index) in scheduledProjects" :key="index">
            Alarm goes off: {{ project.scheduledTime }}
          </li>
        </ul>
        <!-- <BaseButtonStyle @click="stopSound">OK</BaseButtonStyle> -->
        <BaseButtonStyle @click="showConfirm = false" style="width: 65px;">OK</BaseButtonStyle>
      </template>
      <template #footer>
        <BaseButtonStyle @click="showConfirm = false"></BaseButtonStyle>
      </template>
      </ModalComponent>
     </div>
     <footer>
        <p>Copyright&copy; {{ currentYear }} Abraham Parker, NY</p>
    </footer>
  </template>
  
  <script>
  import ModalComponent from '@/components/ModalComponent.vue';
  import BaseButtonStyle from '@/components/BaseButtonStyle.vue';
  import BaseInput from '@/components/BaseInput.vue';
  import BaseSelect from '@/components/BaseSelect.vue';
  import MarqueeStyle from '@/components/MarqueeStyle.vue';
  export default {
    name:"CreateAlertSelectVersion",
    components: {
        ModalComponent,
        BaseButtonStyle,
        BaseInput,
        BaseSelect,
        MarqueeStyle,
    },
    data() {
      return {
        alertHours: '',
        alertMinutes: '', 
        totalTime: '',
        alertMessage: '',
        categories: [
                      "Go for lunch OL",
                      "Come Back from lunch IL",
                      "Ending Tour",
                      "Stand up meeting",
                      "Other Meeting",
                      "Manager Call",
                      "Teammate Call",
                      "CustomMessage"
                    ],
      myEvent: {
                category: "",
              },

        scheduledProjects: [],
        isModalVisible: false,
        modalMessage: '',
        audio: null,
        
        showConfirm: false,
        showModal: false,


        selectedHours: [
                        '0','1','2','3','4','5','6','7','8','9','10','11','12'
                        ],
        selectedMinutes: [
                        '0','1','2','3','4','5','6','7','8','9','10',
                        '11','12','13','14','15','16','17','18','19','20',
                        '21','22','23','24','25','26','27','28','29','30',
                        '31','32','33','34','35','36','37','38','39','40',
                        '41','42','43','44','45','46','47','48','49','50',
                        '51','52','53','54','55','56','57','58','59'
                        ],
        myTimeHour: {
                      hour: '0'
                    },
        myTimeMin: {
                      min: '0'
                    },
       // imageUrl: '../assets/programcomputer.gif',
        modalImageUrl: '',
        currentYear: new Date().getYear(),
        backgroundimage: '../assets/handclock.jpg',
              
      };
    },
    mounted() {
        setInterval(() => {
            this.currentYear = new Date().getFullYear()
        }, 1000);
        window.addEventListener('resize', this.handleResize);
    },

    methods: {
    
      createAlert() {
        const now = new Date();
        this.showConfirm=true;
        //this.showModal= true;
        const alert = {
            //showModal: true,
            message: this.showCustomeTextbox ? this.alertMessage: this.myEvent.category,
            createdTime: now,
        };
        // Schedule project creation 30 minutes after alert creation
        this.scheduleProject(alert, this.totalMinutes * 60000);
        this.alertMessage = ''; // Reset input field
      },


      scheduleProject(alert, delay) {
        const scheduledTime = new Date(Date.now() + delay);
        //this.showModal= true;
        const project = {
          scheduledTime: scheduledTime.toLocaleTimeString(),
          alert: alert,
         
        };
  
        this.scheduledProjects.push(project);
  
        // Automatically display modal and play sound after 30 minutes
        setTimeout(() => {
          this.showModalWithSound(project.alert.message);
        }, delay);
      },
      showModalWithSound(message) {
        this.showModal= true;
        this.modalMessage = `Alarm created for: "${message}"`;
        this.isModalVisible = true;
        this.imageUrl='@/assets/programcomputer.gif',
        this.playSound();
      },
      playSound() {
        this.audio = new Audio(require('@/assets/Home_Computermp3.mp3')); // Ensure the sound file is in the assets folder
        this.audio.loop = true; // Loop the sound until stopped
        this.audio.play();
      },
      stopSound() {
        if (this.audio) {
          this.audio.pause();
          this.audio.currentTime = 0; // Reset the audio to the beginning
        }
        this.isModalVisible = false; // Hide the modal
      },
    
    },
    computed: {
      totalMinutes() {
       //Convert hours and minutes to total minutes
        const calHours = Number (this.myTimeHour.hour );
        const calMinuts = Number (this.myTimeMin.min);
     
        //const minMinutes = this.alertMinutes;
        return  (calHours * 60 ) + calMinuts;
      },


      //Show the textbox if 'customMessage' is selected
      showCustomeTextbox() {
         return this.myEvent.category === "CustomMessage";
          //this.myEvent.category === this.modalMessage;

      },

    },
  };
  </script>
  
  <style scoped>
  .optionWidth {
    widows: 10px;
  }

  label {
  color: rgba(0, 0, 0, 0.5);
  font-weight: 700;
  }
  .img {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 150px;
  }

  .background-image-top {
  background-image: url('../assets/programcomputer.gif'); /* Replace with the path to your image */
    background-size: cover; /* Cover the entire container */
    background-position: center; /* Center the image */
     /*min-height: 100%; Ensure the container takes up the full height of the viewport */
    /* Add any other CSS styles as needed */
    display: flex;
    flex-wrap: wrap;
    position:sticky;
    background-repeat: no-repeat;
    background-size: 450px 575px;
 
  }
  .body {
    box-sizing: border-box;
    width: 500px;
    padding: 0 20px 20px;
    margin: 0 auto;
    background-image: url('../assets/handclock.jpg'); /* Path from the public directory */
    background-position: center; /* Center the background image */
    
  }
  #background-image {
  width: 100%;
  height: 100vh;
  background-image: url('../assets/handclock.jpg'); /* Path from the public directory */
  background-size: cover; /* Cover the entire element */
  background-position: center; /* Center the background image */
  background-repeat: no-repeat; /* Prevent repeating the image */
  }

  footer {
    margin-top: 200px;
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 0px ;
    width: 100%;
    height: 1.5rem; 
  
  }
  </style>