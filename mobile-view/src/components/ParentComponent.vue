<template>
    <div>
        <ChildComponent v-on="$listeners" v-bind="$attrs" v-bind:fruit-bucket="fruits"></ChildComponent>
        <ChildComponent v-if="someValue" v-on="$listeners" v-bind="$attrs" :myProp="someValue" ></ChildComponent>
    
    </div>
</template>

<script>
import ChildComponent from '@/components/ChildComponent.vue';
    export default {
        name:'ParentComponent',
        components: {
            ChildComponent
        },
        data () {
            return { 
                someValue: 'Hello World!',
               //Let's make array of object example
               fruits: [
                    { name: "Apple", test: "Cruncy"},
                    { name: "Banana", test: "Sweet"},
                    { name: "Straberry", test: "Soft-Sweet"}
                ],
               

                
        
        }

        },

    }
</script>
