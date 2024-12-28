<template>
    <!-- This example is Scoped Slot how data can be shown from a Child Component here child component is BookScopedSlot -->
    <BookScopedSlot>
        <template v-slot:title="slotProps">
            <h1>{{ slotProps.bookTitle }}</h1>
        </template>
    </BookScopedSlot>
</template>

<script>
import BookScopedSlot from './BookScopedSlot.vue';

    export default {
        name:'LibraryScopedSlot',
        components: {
            BookScopedSlot,
        }

    }
</script>

<style scoped>

</style>

