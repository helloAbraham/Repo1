<!-- 
    Our component should be highly reusable lebel property can be found
    and they should be able to accecpt two-way model binding through
    v-modling 
    Todo list:<template>
      1. Add label prop
      2. Allow v-model binding
    </template>
-->
<template>
    <input
      class="field"
      type="checkbox"
      :checked="modelValue"
      @change="$emit('update:modelValue', $event.target.checked)"
    />
    <label v-if="label">{{ label }}</label>
  </template>
  
  <script>
  export default {
    name: "BaseCheckbox",
    props: {
      label: {
        type: String,
        default: "",
      },
      modelValue: {
        type: Boolean,
        default: false,
      },
    },
  };
  </script>
  
<style scoped>
</style>
  