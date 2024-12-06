<template>
    <div class="topPadding">
        <div class="upload-container">
            <h1>Upload Image</h1> <br/><br/>
            <input type="file" @change="handleFileChange" />
            <br/>
            <BaseButtonStyle @click="uploadImage">Upload</BaseButtonStyle>
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

                try {                              
                    const response = await axios.post('https://api.abrahamparker.com/api/images/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    }
                    });
                    //this.uploadStatus = 'File uploaded successfully: ' + response.data;
                    this.uploadStatus = response.data;
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
</style>