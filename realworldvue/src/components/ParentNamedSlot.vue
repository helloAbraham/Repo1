<template>
    <ChildNamedSlot>
        <template v-slot:header>
            <h1>Page Title</h1>
        </template>

        <template v-slot:default>
            <p>My Page contains are here ... </p>
        </template>

        <template v-slot:sidebar>
            <ul>
                <li>Bullet # 1</li>
                <li>Bullet # 2</li>
                <li>Bullet # 3</li>
            </ul>
        </template>
    </ChildNamedSlot>
</template>

<script>
import ChildNamedSlot from './ChildNamedSlot.vue';

    export default {
        name: 'ParentNamedSlot',
        components: {
            ChildNamedSlot,
        }
        
    }
</script>

<style scoped>

</style>