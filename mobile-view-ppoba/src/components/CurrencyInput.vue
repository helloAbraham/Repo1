<template>
    <div class="top-padding">
      <label for="currencyInput">Enter Currency Amount:</label>
      <input ref="currencyInput" type="text" id="currencyInput" v-model="formattedCurrency" @input="updateCurrency" placeholder="0.00">
    </div>
  </template>
  
  <script>
  export default {
    mounted() {
      this.moveCursorToEnd();
    },
    data() {
      return {
        currency: ''
      };
    },
    computed: {
      formattedCurrency() {
        let value = this.currency.replace(/\D/g, ''); // Remove non-digit characters
        value = value.padStart(3, '0')
        const cents = value.substring(value.length - 2);
        let dollars = value.substring(0, value.length - 2);
        value = value.padEnd('');
        // Remove leading zeros and handle the case when dollars is empty
        dollars = dollars.replace(/^0+/, '');
        if (!dollars) {
          dollars = '0';
        }
        
        return dollars + '.' + cents;
      }
    },
    methods: {
      updateCurrency(event) {
        // Update the currency value based on the formatted input value
        this.currency = event.target.value.replace(/\D/g, '');
      },
      moveCursorToEnd() {
        // Move cursor to the end of the input field
        const input = this.$refs.currencyInput;
        input.focus();
        input.setSelectionRange(input.value.length, input.value.length);
      }
    }
  };
  </script>
  
  
  
  
<style scoped>
.top-padding {
    padding-top: 235px;
    margin-bottom: 200px;
   
    }

</style>

