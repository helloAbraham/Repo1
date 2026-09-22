<template>
    <div v-if="isOpen" class="modal-overlay">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <slot name="header">
              <h3>{{ title }}</h3>
            </slot>
            <button @click="closeModal" class="modal-close-btn">X</button>
          </div>
          <div class="modal-body">
            <slot></slot>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
import ButtonLink from './ButtonLink.vue';

  export default {
    props: {
      isOpen: {
        type: Boolean,
        required: true
      },
      title: {
        type: String,
        default: 'Modal Title'
      }
    },
    data() {
      return {

      }
    },

    components: {
      ButtonLink,
    },
    methods: {
      closeModal() {
        this.$emit('close');
      }
    }
  };
  </script>
  
  <style>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.7);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-dialog {
    background-color: #fff;
    border-radius: 5px;
    padding: 20px;
    width: 400px;
  }
  
  .modal-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
  }
  
  .modal-close-btn {
    border: none;
    background: none;
    cursor: pointer;
    font-size: 16px;
  }
  </style>
  