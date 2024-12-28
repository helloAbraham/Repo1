<template>
    <!-- This is a child component using for LibraryScopedSlot 
     here data can be retrive from database or any single state v-model data binding :bookTitle equal "bookTitile"  -->
    <div class="book">
        <slot name="title" :bookTitle="bookTitle" />
    </div>
</template>

<script>
    export default {
        name:'BookScopedSlot',
        data() {
            return {
                bookTitle: 'Child Providing Data',
            }
        }
    }
</script>

<style scoped>

</style>