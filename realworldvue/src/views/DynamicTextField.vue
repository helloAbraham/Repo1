<template>
    <div class="hello">
      <h1>{{ msg }}</h1>
      <div class="w-full mt-4 p-10">
     
        <div v-for="(course, index) in courses" :key="index">
          <div class="w-full pl-3 py-2 border border-indigo-500 rounded">
            <input
              v-model="course.courseName"
              type="number"
              class="form-control"
              name="qtn"
              id="quantity"
            />
            <input
              v-model="course.courseInstructor"
              placeholder="enter you course Instructor"
              class="w-full pl-3 py-2 border border-indigo-500 rounded"
            />
            <input
              v-model="course.semsester"
              placeholder="enter you semester"
              class="w-full pl-3 py-2 border border-indigo-500 rounded"
            />
            <input
              v-model="course.dayornigjt"
              placeholder="enter day or night"
              class="w-full pl-3 py-2 border border-indigo-500 rounded"
            />
          </div>
            <div>   
              <button
          type="button"
          class="flex justify-start ml-2 rounded-md border px-3 py-2 bg-pink-600 text-white"
          @click="addMore()"
          >
          Add More 
          </button> 
          <button
              type="button"
              class="ml-2 rounded-md border px-3 py-2 bg-red-600 text-white"
              @click="remove(index)"
              v-show="index != 0"
            >
              Remove it
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- here adding addMoreItem-->
    <form>
    <div v-for="(form, index) in forms" :key="index" >
      <input v-model="form.sale_quantity" type="number" class="form-control" name="sale_quantity" id="sale_quantity" placeholder="Quantity">
      <input v-model="form.sale_item" type="String" class="form-control" name="sale_item" id="sale_item" placeholder="Item" >
      <input v-model="form.sale_tagprice" type="number" class="form-control" name="sale_tagprice" id="sale_tagprice" placeholder="Tag Price">
      <input v-model="form.sale_discount" type="number" class="form-contrl" name="sale_disocunt" id="sale_discount" placeholder="Discount">
      <input :value="form.sale_quantity * form.sale_tagprice - form.sale_discount/100 * form.sale_quantity * form.sale_tagprice" type="number" step="any" class="form-control" name="sale_price" id="sale_price" placeholder="Price">
       <!-- <input :value="form.itemPrice" type="number" step="any" class="form-control" name="sale_price" id="sale_price" placeholder="Price"> -->
        <br>
    
          Price {{ itemPrice}}
          Total: {{sale_total}}
          <div>   
                <button
                type="button"
                class="flex justify-start ml-2 rounded-md border px-3 py-2 bg-pink-600 text-white"
                @click="addMoreItem()"
                >
                  Add More 
                </button> 
                <button
                  type="button"
                  class="ml-2 rounded-md border px-3 py-2 bg-red-600 text-white"
                  @click="removeItem(index)"
                  v-show="index != 0"
                >
                 Remove it
                  </button>
            </div>
    </div>
  </form>
  </template>
  
  <script>
  export default {
    name: "DynamicTextField",
    props: {
      msg: String,
    },
    data() {
      return {

        forms: [
            {
            sale_quantity:0,
            sale_item:"",
            sale_tagprice:0,
            sale_discount:0,
            sale_price:0
            }
          ],
        courses: [
          {
            courseName: "",
            courseInstructor: "",
            semsester: "",
            dayornigjt: "",
          },
        ],
      };
    },
    methods: {
      addMore() {
        this.courses.push({
          courseName: "",
          courseInstructor: "",
          semsester: "",
          dayornigjt: "",
        });
      },
      removeIt(index) {
        this.courses.splice(index, 1);
      },

      addMoreItem() {
        this.forms.push({
            sale_quantity:0,
            sale_item:"",
            sale_tagprice:0,
            sale_discount:0,
            sale_price:0
        });
      },
      removeItem(index) {
        this.forms.splice(index, 1);
      },
    },



    computed: {
      total () {
      let calculatedTotal = this.form.sale_quantity * this.form.sale_rate;
      this.sale_total = calculatedTotal;
      
      return calculatedTotal;
    },

    itemPrice (){
      let calculatedItemPrice = (this.forms.sale_quantity * this.forms.sale_tagprice) - (this.forms.sale_discount/100) * (this.forms.sale_quantity * this.forms.sale_tagprice)
      this.sale_price = calculatedItemPrice;
      return calculatedItemPrice;
    }
  }
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  </style>
  