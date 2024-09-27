<!-- 
    Our TODO List:<template>
      1. Add label prop
      2. Make it v-model capable
      3. Make the options reactive (We have to create an options prop)
-->
<template>
    <label v-if="label"> {{ label }}</label>
    <select
      class="field"
      :value="modelValue"
      v-bind="{
        ...$attrs,
        onChange: ($event) => {
          $emit('update:modelValue', $event.target.value);
        },
      }"
    >
      <option
        v-for="option in options"
        :key="option"
        :value="option"
        :selected="option === modelValue"
      >
        {{ option }}
      </option>
    </select>
  </template>
  
  <script>
  export default {
    name: "BaseSelect",
    props: {
      label: {
        type: String,
        default: "",
      },
      modelValue: {
        type: [String, Number],
        default: "",
      },
      options: {
        type: Array,
        required: true,
      },
    },
  };
  </script>
  
  <style scoped></style>
  