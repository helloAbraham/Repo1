<template>
  <div v-if="visible" class="confirm-overlay">
    <div class="confirm-modal">
      <h3 class="confirm-title">{{ title }}</h3>
      <p class="confirm-message">{{ message }}</p>
      <div class="confirm-buttons">
        <BaseButtonStyle class="confirm-btn cancel" @click="cancel">❌ Cancel</BaseButtonStyle>
        <BaseButtonStyle class="confirm-btn ok" @click="confirm">✅ {{ confirmText }}</BaseButtonStyle>
      </div>
    </div>
  </div>
</template>

<script>

import BaseButtonStyle from './BaseButtonStyle.vue';

export default {
  name: "ConfirmModal",

  components: {
    BaseButtonStyle,
  },

  props: {
    title: { 
            type: String, 
            default: "Confirm" 
            },

    message: { 
            type: String, 
            default: "Are you sure?" 
            },

    confirmText: { 
                type: String,
                default: "Yes" 
                },

    visible: { 
            type: Boolean, 
            default: false 
            },

  },

  methods: {
    confirm() {
      this.$emit("confirm");
    },
    
    cancel() {
      this.$emit("cancel");
    },
  },
};
</script>

<style scoped>
.confirm-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.confirm-modal {
  background: #fff;
  padding: 20px 30px;
  border-radius: 10px;
  text-align: center;
  width: 320px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
}

.confirm-title {
  margin: 0;
  font-size: 1.3em;
  color: #333;
}

.confirm-message {
  margin: 10px 0 20px;
  color: #555;
}

.confirm-buttons {
  display: flex;
  justify-content: center;
  gap: 15px;
}

.confirm-btn {
  border: none;
  padding: 8px 16px;
  font-size: 1em;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s;
}

.confirm-btn.ok {
  background-color: #4caf50;
  color: white;
}

.confirm-btn.ok:hover {
  background-color: #43a047;
}

.confirm-btn.cancel {
  background-color: #f44336;
  color: white;
}

.confirm-btn.cancel:hover {
  background-color: #d32f2f;
}
</style>
<!-- 
You can now reuse <ConfirmModal> in any component, for example:
<ConfirmModal
  :visible="showDeleteConfirm"
  title="Delete Item?"
  message="Are you sure you want to delete this record?"
  confirmText="Delete"
  @confirm="deleteItem"
  @cancel="showDeleteConfirm = false"
/>
In this example, the parent component controls the visibility 
of the modal and handles the confirm and cancel actions. 
You can customize the title, message, and confirm button text as needed.
-->