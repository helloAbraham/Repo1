<template>
    <div class="body">
        <form class="form-style" action="" >
            <h2 class="input-fields">LOGIN</h2>
            <div class="input-fields">
                <input type="text" name="" id="" placeholder="" required="true">
                <label for="">User Name</label>
            </div>
            <div class="input-fields">
                <input type="password" name="" id="" placeholder="" required="true">
                <label for="">Password</label>
                <span>show</span>    
          <input type="submit" value="Login" name="" id="btn">
          </div>
          <div id="forget_password">
            <i>Forgot Password?</i><span>Click here</span>
          </div>
        </form>
</div>
</template>

<script>
    export default {
        name: "LoginForm",

    }
</script>

<style scoped>
.body{
    margin: 0;
    padding: 0;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #1f3040;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
    box-sizing: border-box;
}
   .form-style{
    height: 450px;
    width: 450px;
    background-color: #fff;
    padding: 10px 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    box-shadow: -3px 3px 10px rgba(0, 0, 0, 0.4);

}
.h1{
    text-align: center;
    position: center;
  
}
.input-fields{
    position: relative;
    width: 400px;
    height: 40px;
    margin-top: 25px;
    left: 20px;

}
.input-fields input{
    width: 100%;
    height: 100%;
    outline: 1.4px solid black;
    border: navajowhite;
    border-radius: 8px;
    padding: 5px 10px;
    padding-left: 10px;
    font-size: 18px;
}
.input-fields input:focus{
    outline: 2px solid#2692df;
}
.input-fields input:focus + label {
    top: -10px;
    color: green;
}

.input-fields label{
    position: absolute;
    top: 15px;
    left: 15px;
    font-size: 18px;
    padding: 0 5px;
    pointer-events: none;
    transition: top .2s;
    background-color: white;
    color: rgb(99,99,99);

}
.input-fields input:not(:placeholder-shown) + label {
    top: -10px;
    color: green;
}
.input-fields span{
    position: absolute;
    top: 15px;
    left: 350px;
}
#btn{
    margin-top: 25px;
    width: 115px;
    padding: 10px 20px;
    border: none;
    color: white;
    background-color: #1f3040;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;

}
#forgot_password span{
    
    text-decoration: underline;
    cursor: pointer;

}
#forget_password {
    padding-top: 75px;
    text-align: center;
    margin-top: 10px;
}
</style>