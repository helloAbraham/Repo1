<!--- Exmple of two way binding this MyList components will talk to MyCount.vue -->
<template>
    <div>
        <ul>
            <li v-for="(value, index) in listdata" 
            :key="index"
            @click="$emit('clickedItem', value)"
            >
            {{ value }}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        props: {
            listdata:{
                type: Array,
                requried: true,
                validator:(value) => {
                    return value.length <=10;
                }
            }
          

        },
    }
</script>

<style scoped>

</style>