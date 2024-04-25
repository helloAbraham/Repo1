<template>
    <input
      type="radio"
      :checked="modelValue === selectedValue"
      :value="selectedValue"
      @change="$emit('update:modelValue', selectedValue)"
      v-bind="$attrs"
    />
    <label v-if="label">{{ label }}</label>
  </template>
  
  <script>
  export default {
    name: "BaseRadio",
    props: {
      label: {
        type: String,
        default: "",
      },
      modelValue: {
        type: [String, Number],
        default: "",
      },
      selectedValue: {
        type: [String, Number],
        required: true,
      },
    },
  };
  </script>
  
  <style scoped></style>
  