<template>
    <div class="c-foot xy-center">
        <div class="detail">
            <span>119Shop - Online Computer Sales Platform</span>
            <span>Powered by VUE & SpringBoot</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'CFoot'
    }
</script>

<style scoped lang="scss">

    .c-foot {
        width: 100%;
        background: rgb(0, 0, 0);
        flex-direction: column;
        padding: 18px 0;
        .detail {
            width: 100%;
            text-align: center;
            border-top: 1px solid #ffffff;
            padding-top: 20px;
            span {
                display: block;
                font-family: Source Han Sans CN;
                color: rgb(255, 255, 255);
                font-size: 18px;
                margin: {
                    bottom: 15px;
                };
            }
        }
    }

</style>
