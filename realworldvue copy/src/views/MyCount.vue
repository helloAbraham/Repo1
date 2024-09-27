<template>
    <div>
        <h1> Below you select any list value, I will tell you what you click!</h1>
        <MyList :listdata="items" @clickedItem="getClickedItem"/>
        <h2> You just clicked {{ clickedItemLocal }}</h2>
    </div>
</template>

<script>
import MyList from '@/components/MyList.vue';


    export default {
        name:"MyCount",

        data(){
            return{
                items: ['first', 'second', 'third'],
                clickedItemLocal: ''
            }
        },
        components: {
            MyList
        },
        methods: {
            getClickedItem(myvalue){
                this.clickedItemLocal = myvalue;
            }
        }
    }
</script>

<style scoped>

</style>