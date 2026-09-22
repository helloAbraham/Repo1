<template>
  <div v-if="visible" class="modal-overlay" @click.self="close">
    <div class="modal-content" role="dialog" aria-modal="true" :aria-label="ariaLabel">
      <header class="modal-header">
        <slot name="header">
          <h3>{{ title }}</h3>
        </slot>
        <BaseButtonStyle class="close-button" @click="close" aria-label="Close">&times;</BaseButtonStyle>
      </header>

      <main class="modal-body">
        <template v-if="imageUrl">
          <img class="modal-image" :src="imageUrl" alt="Modal image" />
        </template>
        <slot v-else>
          <p>Default Body Content</p>
        </slot>
      </main>

      <footer class="modal-footer">
        <slot name="footer">
          <BaseButtonStyle class="modal-action" @click="close">Close</BaseButtonStyle>
        </slot>
      </footer>
    </div>
  </div>
</template>

<script>
import BaseButtonStyle from './BaseButtonStyle.vue';


export default {
  name: "ModalComponentWithImg",
  components: {
    BaseButtonStyle,
  },
  props: {
    visible: { type: Boolean, default: false },
    imageUrl: { type: String, default: "" },
    title: { type: String, default: "Notice" },
    ariaLabel: { type: String, default: "Modal dialog" }
  },
  methods: {
    close() {
      this.$emit("close");
    }
  }
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.55);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
  padding: 16px;
}

.modal-content {
  background: #fff;
  border-radius: 8px;
  width: 90%;
  max-width: 560px;
  box-shadow: 0 8px 24px rgba(0,0,0,0.35);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 16px;
  background: #8ee2be;
}

.close-button {
  background: none;
  border: none;
  font-size: 1.6rem;
  line-height: 1;
  cursor: pointer;
  padding: 4px 8px;
}

.modal-body {
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 180px;
  background: #fff;
}

.modal-image {
  max-width: 100%;
  max-height: 440px;
  display: block;
  margin: 0 auto;
  object-fit: contain;
}

.modal-footer {
  padding: 12px 16px;
  background: bisque;
  display: flex;
  justify-content: center;
}

.modal-action {
  background: #28a745;
  color: white;
  border: none;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
}
</style>