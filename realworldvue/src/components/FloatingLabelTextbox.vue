<template>
    <div class="body">
        <form class="form-style" action="">
            <h1>LOGIN</h1>
            <div class="input-fields">
                <input type="email" name="" id="" placeholder="" required="true">
                <label for="">Email</label>
            </div>
            <div class="input-fields">
                <input type="password" name="" id="" placeholder="" required="true">
                <label for="">Password</label>
                <span>show</span>    
            </div>
          <input type="submit" value="Login" name="" id="btn">
          <div id="forget_password">
            <i>Forgot Password?</i><span>Click here</span>
          </div>
        </form>
</div>
</template>

<script>
    export default {
        name: "FloatingLabelTextbox",

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
    padding: 10px 20 px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    box-shadow: -3px 3px 10px rgba(0, 0, 0, 0.4);

}
.h1{
    text-align: center;
}
.input-fields{
    position: relative;
    width: 430px;
    height: 40px;
    margin-top: 30px;

}
.input-fields input{
    width: 100%;
    height: 100%;
    outline: 1.4px solid black;
    border: navajowhite;
    border-radius: 8px;
    padding: 5px 10px;
    font-size: 18px;
}
.input-fields input:focus{
    outline: 2px solit#1f3040;
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
    padding: 0 15px;
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
    left: 375px;
}
#btn{
    margin-top: 30px;
    padding: 10px 20px;
    border: none;
    color: white;
    background-color: #1f3040;
    border-radius: 8px;
    font-size: 10px;
    font-weight: bold;
    cursor: pointer;

}
#forgot_password span{
    text-decoration: underline;
    cursor: pointer;

}
#forget_password {
    text-align: center;
    margin-top: 10px;
}
</style>