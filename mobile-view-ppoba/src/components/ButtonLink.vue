<template>
    <a :href="url" class="btn btn-2">
      <slot></slot>
    </a>

    
  </template>
  
  <script>
  export default {
    name: 'ButtonLink',
    props: {
      url: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <!-- <style scoped>
  .button {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
  }
  
  .button:hover {
    background-color: #0be565;
  }
  </style> -->
  <style scoped>
body{
  font-family: 'Montserrat', sans-serif;
  margin:0;
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  flex-wrap: wrap;
  width: 80vw;
  margin: 0 auto;
  min-height: 100vh;
}
.btn {
  flex: 1 1 auto;
  margin: 10px;
  padding: 10px 20px;
  text-align: center;
  text-decoration:aqua;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: rgb(255, 255, 255);
 /* text-shadow: 0px 0px 10px rgba(0,0,0,0.2);*/
  text-shadow: #070707;
  box-shadow: 0 0 20px #eee;
  border-radius: 10px;
  cursor:pointer;
 }

/* Demo Stuff End -> */

/* <- Magic Stuff Start */

.btn:hover {
    background-position: right center; /* change the direction of the change here */
    -webkit-transform: scale(1.02);
    transform: scale(1.02);
    box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}


.btn-2 {
  background-image: linear-gradient(to right, #f298d9 0%, #a6c1ee 51%, #dc6cbc 100%);
}

.btn-3 {
  background-image: linear-gradient(to right, #84fab0 0%, #8fd3f4 51%, #84fab0 100%);
}
.btn-4 {
  background-image: linear-gradient(to right, #a1c4fd 0%, #c2e9fb 51%, #a1c4fd 100%);
}

.btn-5 {
  background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 51%, #ffecd2 100%);
}
</style>