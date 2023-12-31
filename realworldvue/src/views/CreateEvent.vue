<template>
  <div>
    <form>
      <h1>Create an Event</h1>
      <BaseSelect
        :options="categories"
        v-model="event.category"
        label="Select a category"
      />
      <h3>Name & describe the event</h3>
      <!-- here BaseInput use just single use each time input for example title, then description-->
      <BaseInput v-model="event.title" label="Title" type="text" />
      <BaseInput v-model="event.description" label="Description" type="text" />
      <h3>Where is your event?</h3>
      <BaseInput v-model="event.location" label="Location" type="text" />
      <h3>Are pets allowed?</h3>
      <div>
        <BaseRadio
          v-model="event.pets"
          :selectedValue="1"
          label="Yes"
          name="pets"
        />
      </div>

      <div>
        <BaseRadio
          v-model="event.pets"
          :selectedValue="0"
          label="No"
          name="pets"
        />
      </div>
      <h3>Extras if any ...</h3>
      <div>
        <BaseCheckbox v-model="event.extras.catering" label="Catering" />
        <BaseCheckbox v-model="event.extras.music" label="Live Music" />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import BaseInput from "@/components/BaseInput.vue";
import BaseSelect from "@/components/BaseSelect.vue";
import BaseCheckbox from "@/components/BaseCheckbox.vue";
import BaseRadio from "@/components/BaseRadio.vue";
export default {
  name: "CreateEvent",
  data() {
    return {
      categories: [
        "sustainbility",
        "nature",
        "animal welfare",
        "housing",
        "eduaction",
        "food",
        "community",
      ],
      event: {
        category: "",
        title: "",
        description: "",
        location: "",
        pets: 1,
        extras: {
          catering: false,
          music: false,
        },
      },
    };
  },
  components: {
    BaseInput,
    BaseSelect,
    BaseCheckbox,
    BaseRadio,
  },
};
</script>

<style scoped></style>
