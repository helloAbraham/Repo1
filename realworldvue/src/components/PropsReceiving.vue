<template>
    <div>
        <p>Shipping: {{ shipping }}</p>
        <p>Message: {{ message }}</p>
        <p>Count: {{ count }}</p>
        <button @click="incrementCount">Increment Count</button>
    </div>
    <div>
        <input v-model="localValue" @input="updateParent" />
    </div>
    <div>
        <p>{{ localValueAppend }}</p>
    </div>
</template>

<script>

    export default {
        name:"PropsReceiving",
        props: {
                premium: {
                    type: Boolean,
                    required: true
                },
                message: {
                    type: String,
                    required: true
                },
                count: {
                    type: Number,
                    default:0
                },
                value: {
                    type: String,
                    default: ''
                },
                
            },
        components: {
        },
    data() {
        return {
            localValue: this.value,
            message: ''
           
        }
    },
        computed: {
            shipping() {
                if (this.premium === false) {
                    return "Free"
                } else {
                    return 2.99
                }
            },
           
        },

        methods: {
            incrementCount() {
                //Emit an event to inform the parent to update the count
                this.$emit('update-count', this.count + 1);
            },

            updateParent() {
                this.$emit('update-value', this.localValue);
            },

       
        }
    }
</script>