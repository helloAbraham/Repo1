<template>
    <div>
        <h1>Parent Component</h1>
        <PropsReceiving :message="parentMessage" :count="parentCount" @update-count="handleUpdateCount" 
        :value="parentValue" @update-value="handleUpdateValue" />
        <p>Current Count: {{ parentCount }}</p>
       
    </div>
    <div>
        <p>Value in parent: {{ parentValue }}</p>
    </div>

    <div>
        <h1>Another example how to push messages</h1>
        <PropsChild @add-message="handleAddMessage"/>
        
            <ul>
                <li v-for="(message, index) in messages" :key="index">{{ message }}</li>
            </ul>
    
    </div>
</template>

<script>
import PropsReceiving from './PropsReceiving.vue'
import PropsChild from './PropsChild.vue'
    export default {
        name:"PropsExampleProductDisplay",

        components: {
            PropsReceiving,
            PropsChild,
        },
    
        data() {
            return {
                premium: false,
                parentMessage: 'Greeting from Parent',
                parentCount: 5,
                parentGreet: 'Hello World! ',

                messages: [],
            }
        },

        methods: {
            handleUpdateCount(newCount) {
                this.parentCount = newCount;
            },

            handleUpdateValue(newValue) {
                this.parentValue = newValue;
            },
            handleUpdateGreet(newGreet) {
                this.parentGreet.push(newGreet);
            },

            handleAddMessage(newMessage) {
                this.messages.push(newMessage);
            }

        }


       
    }
</script>