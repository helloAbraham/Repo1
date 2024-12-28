<template>
    <div class="topPadding">
        <div class="upload-container">
            <h1>Upload Image</h1> <br/><br/>
            <input type="file" @change="handleFileChange" />
            <br/>
            <BaseButtonStyle @click="uploadImage"><i class='fas fa-folder-open'></i> &nbsp; Upload</BaseButtonStyle>
            <div v-if="uploadStatus">{{ uploadStatus }}</div>

        </div>
    </div>
</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';
import axios from 'axios';
    export default {
        name:'ImageUpload',
        data () {
            return {
                selectedFile: null,
                uploadMessage: '',
                uploadStatus:'',
                showConfirm: false,
            }
        },
        components: {
            BaseButtonStyle,
        },
        methods: {
                handleFileChange(event) {
                    this.selectedFile = event.target.files[0];
                    console.log('Selected file:', this.selectedFile);  // Check that the file is selected
                },
                
                async uploadImage() {
                if (!this.selectedFile) {
                    this.uploadStatus = 'Please select a file.';
                    return;
                }

                const formData = new FormData();
                formData.append('file', this.selectedFile);

                    //For production 'https://api.abrahamparker.com/api/images/upload'
                    //For localtest
                    //http://localhost:8082/api/images/upload
                try {                              
                    const response = await axios.post('https://api.abrahamparker.com/api/images/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    }
                    });
                    //this.uploadStatus = 'File uploaded successfully: ' + response.data;
                    this.uploadStatus = response.data;
                    //After file uploaded then clear the selectedFile
                    this.selectedFile = null;
                } catch (error) {
                    this.uploadStatus = 'Error uploading file.';
                    console.error("Error uploading file: ", error);
                }
            }
        }
        
        
    }
</script>

<style scoped>


.upload-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

  /* Example of styling the icon */
  i {
    color: #232929; /* Change the color of icons */
  }

</style>