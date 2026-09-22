<template>
  <div v-if="visible" class="modal-overlay">
    <div class="modal-content">
      <h3>{{ title }}</h3>
      <p>{{ message }}</p>
      <div class="modal-buttons">
        <BaseButtonStyle @click="$emit('confirm')">{{ confirmText || 'OK' }}</BaseButtonStyle>
        <BaseButtonStyle @click="$emit('cancel')">Cancel</BaseButtonStyle>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';

export default {
  name: "ConfirmModalFlexButton", 
  components: {
    BaseButtonStyle,
  },

  props: {
    visible: Boolean,
    title: String,
    message: String,
    confirmText: String,
  },
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0; left: 0;
  width: 100%; height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.modal-content {
  background: #fff;
  padding: 20px 30px;
  border-radius: 10px;
  text-align: center;
  min-width: 300px;
}

.modal-buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}
</style>
