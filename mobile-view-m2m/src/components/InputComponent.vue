<template>
  <input type="text" :value="modelValue" @input="$emit('update:modelValue', $event.target.value)" />
</template>

<script>
    export default {
        name:'InputComponent',
        props: {
            modelValue: String,
        }
    }
</script>