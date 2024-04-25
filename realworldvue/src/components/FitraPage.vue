<template>
    <div>
        <!-- Text box bound to the fetched data -->
        <input type="text" v-model="apiDataCategory" />
        <input type="text" v-model="apiDataDate" />
        <input type="text" v-model="apiDataLocation" />

        <!-- Button to post the update data -->
        <button @click="postMyData">Submit</button>
    </div>
</template>

<script>
    export default {
        name: "FitraPage",
        data() {
            return {
                apiDataDate: [],
                apiDataCategory:[],
                apiDataLocation:[],
            };
        },

        mounted() {
            //URL to fetch the data from 
            fetch('https://my-json-server.typicode.com/helloAbraham/Repo1/events')
            .then(response => response.json())
            .then(data => {
                //Set the data property with the value from the API
                this.apiDataDate= data[0].date;
                this.apiDataCategory= data[0].category;
                this.apiDataLocation= data[0].location;
            })
        },
        methods: {
            postMyData() {
      // URL to post the updated data
      const apiUrl = 'https://api.example.com/update';

      // Make a POST request to the API
      fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ data: this.apiData }), // Send the updated data as JSON
      })
        .then(response => response.json())
        .then(data => {
          // Handle the API response
          console.log('Data posted successfully:', data);
        })
        .catch(error => {
          console.error('Error posting data to API:', error);
        });
        }
        
        }
    }
</script>

<style scoped>

</style>