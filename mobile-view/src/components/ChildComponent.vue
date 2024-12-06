<template>
    <div>
    
        <h1>Hello it is child component</h1>
        <p>{{ myProp }}</p>
        <ul>
            <li v-for="fruit in fruitBucket" key="furit.name">
                <h2>{{ fruit.name }}</h2>
                <p>{{ fruit.test }}</p>
            </li>
        </ul>
    </div>
</template>

<script>


    export default {
        name:'ChildComponent',

        props: {
            myProp: {
                type: String,
                required: true
                },
            fruitBucket: {
                type: Array,
                required: true,

            }
        },
        created() {
            // props are exposed on `this`
            console.log(this.myProp);
        },
       

        data () {
            return {

            }
        },

        methods: {
            test: function() {
                this.fruitBucket
            }
        },

        mounted() {
            console.log(this.myProp); //output is undefined here
        }
 
  }

</script>

<style scoped>

</style>