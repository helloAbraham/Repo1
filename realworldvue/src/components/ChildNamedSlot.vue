<template>

   <header class="header-style">
    <slot name="header"></slot>
   </header>

   <main class="main-page-style">
    <slot name="default"></slot>
   </main>

   <aside>
    <slot name="sidebar"></slot>
   </aside>
</template>

<script>
    export default {
        name:'ChildNamedSlot',
        
    }
</script>

<style scoped>
.header-style {
    color: aqua;
}

.main-page-style {
    color:chocolate;
    font-size: 18px;
}
</style>