<!-- This page using BaseInput without import because we use main.js register the component BaseInput -->
<template>
    <div class="header-margin">
        <h1>My App Input Example</h1>
       <br />
        <BaseInput v-model="textInput"></BaseInput>
        <BaseButton>Submit</BaseButton>
        <BaseSelect>Select the item</BaseSelect>
        <BaseButtonStyle>StylishButton</BaseButtonStyle>
        <br />
        <br />

    </div>
</template>

<script>



    export default {
        name:"MyForm",
        data() {
            return {
                textInput: '',
            }
        },
     
        
    }
</script>

<style scoped>
.header-margin {
    padding-top: 350px;
}
</style>