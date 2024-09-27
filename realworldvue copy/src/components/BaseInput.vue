<template>
  <label v-if="label"> {{ label }}</label>
  <input
    v-bind="$attrs"
    :placeholder="label"
    class="bgcolor"
    :value="modelValue"
    @input="$emit('update:modelValue', $event.target.value)"
  />
</template>

<script>
export default {
  name: "BaseInput",

  props: {
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
  },
};
</script>

<style scoped>

  .bgcolor {
    margin: 10px;
    padding: 10px;
    background-color: #FFFDD0;
  }
</style>