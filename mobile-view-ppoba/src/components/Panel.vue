<template>
    <div class="panel" :class="'panel-' + severity">
        <div class="panel-heading" style="min-height:3em;">
            <span class="pull-right">
                <slot name="icons"></slot>
                <span v-if="collapsible">
                    <Icon @activate="open = !open" name="caret-square-dwon" title="Expand" sca;e="1.5" v-if="!open"/>
                    
                </span>
            </span>

        </div>

    </div>
</template>

<script>
    export default {
        name:"Panel",

        props: {
            collapsible: {
                type: Boolean,
                required: false,
                default: true
            },
            startOpen: {
                type: Boolean,
                required: false,
                default: true
            },
            severity: {
                type: String,
                required: false,
                default: "default"
            }
        },

        data() {
            return {
                open: this.startOpen,
            };
        }
        
    };
</script>

<style scoped>

</style>