<template>
    <div>
        <button v-on="$listeners" v-bind="$attrs" class="button" :class="buttonClass">
            <slot/>
        </button>
    </div>
</template>

<script>
    export default {
        name: "BaseButton",
        inheritAttrs: false,
        props: {
            buttonClass: {
                type: String
            }
        }
        
    }
</script>

<style scoped>
.button {
        display: inline-flex;
        align-items: center;
        justify-content: space-between;
        height: 52px;
        padding: 0 40px;
        background: transparent;
        background-color: #9be83d;
        border: none;
        border-radius: 6px;
        text-align: center;
        font-weight: 600;
        white-space: nowrap;
        transition: all 0.2s linear;
        background-image: linear-gradient(to right, #fbc2eb 0%, #a6c1ee 51%, #fbc2eb 100%);
        cursor: pointer;
    }
    .button:hover {
        background-position: right center;
        -webkit-transform: scale(1.02);
        transform: scale(1.02);
        box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        cursor: pointer;
    }
    .button:active{
        -webkit-transform: scale(1);
        transform: scale(1);
        box-shadow: none;
    }
    .button:focus {
        outline: 0;
    }
    .nutton:disabled {
        -webkit-transform: scale(1);
        transform: scale(1);
        box-shadow: none;
    }
    .button + .button {
        margin-left: 1em;
    }
    .button.-fill-gradient{
        background: linear-gradient(to right, #16c0b0, #84cf6a);
        color: #ffffff;
    }
    .button.-fill-gray {
        background: rgba(0, 0, 0, 0.5);
        color: #ffffff;
    }
    .button.-size-small {
        height: 32px;
    }
    .button.-icon-right {
        text-align: left;
        padding: 0 20px;
    }
    .button.-icon-right > .icon {
        margin-left: 10px;
    }
    .button.-icon-left {
        text-align: right;
        padding: 0 20px;
    }
    .button.-icon-left > .icon {
    margin-right: 10px;
    }
    .button.-icon-center {
    padding: 0 20px;
    }
    </style>