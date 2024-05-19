<!-- This is the best example for button click for show component or not 
also transition animation inside it-->
<template>
  <div class="gap-from-top">
    <ButtonLink @click="toggleComponent" >Login</ButtonLink>
    <transition name="fade">
     <div v-if="showLoginForm">
      <LoginForm />
     </div>
    </transition>
  </div>
  <div class="gap-from-top">
    <h1>This is an about page</h1>
  </div>
  <div>
    <InputComponent v-model="name"/>
  </div>
  <div>
    <button @click="toggleModal">Open</button>
  </div>
  <br/>
  <div>
    <transition name="fade">
      <div v-if="isOpen" class="modal">
        <p class="form-style">
         
        <button @click="toggleModal">Close</button>
        This is model where is the close buttone. 
        this modal will close I mean dissapler 
        slowly...
        </p>
      </div>
    </transition>
  </div>
  <div>
    <ButtonLink @click="openModal">Open Modal</ButtonLink>
    <transition name="fade">
    <Modal :is-open="modalOpen" @close="closeModal" title="Example My Modal">
      <p>This is the content of the modal. We can make it bigger 
        or smaller. 
        1. line 3
        2. Line 4
      </p>
      <span>
        New space here
      </span>
      <span>
        
      </span>
      <div class="top-space">
        <ButtonLink @click="submitForm">Submit</ButtonLink>
        <ButtonLink @click="closeModal">Cancel</ButtonLink>
      </div>
      
    </Modal>
    </transition>
  </div>
  <br/>
  <br/>

  
</template>


<script>
import ButtonLink from '@/components/ButtonLink.vue';
import InputComponent from '@/components/InputComponent.vue';
import Modal from '@/components/Modal.vue';
import LoginForm from '@/components/LoginForm.vue';


export default{
  name:'AboutView',

  components:{
    InputComponent,
    Modal,
    ButtonLink,
    LoginForm,
    
  },
  data(){
    return{
      name: '',
      isOpen: false,
      modalOpen: false,
      showLoginForm: false,
    }
  },
  methods: {
    toggleModal() {
        this.isOpen = !this.isOpen
    },

    openModal() {
      this.modalOpen = true;
    },

    closeModal() {
      this.modalOpen = false;
    },

    submitForm() {
      //Handle form submission logic below
      console.log('Form submitted!');
      this.closeModal();
    },

    openLoginForm() {
      this.loginFormModal = true;
    },
    closeLoginForm() {
      this.loginFormModal = false;
    },

    toggleComponent() {
      this.showLoginForm = !this.showLoginForm;
    }

  },

}
</script>

<style scoped>
.gap-from-top{
  padding-top: 150px;
}
.form-style{
    height: 450px;
    width: 450px;
    background-color: #fff;
    padding: 10px 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 10px;
    box-shadow: -3px 3px 10px rgba(0, 0, 0, 0.4);

}
.fade-enter {
    opacity: 0;
}
.fade-enter-active, .fade-leave-to {
    transition: opacity 0.5s ease-out;
}
.fade-leave-to {
    opacity: 0;
}
.top-space, .left-space {
  padding-top: 50px;
  padding-left: 120px;
}
</style>

