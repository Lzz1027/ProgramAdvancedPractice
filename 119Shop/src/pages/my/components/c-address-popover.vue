<template>
    <div>
        <el-popover
                placement="left"
                width="250"
                trigger="hover">
            <div class="address-popover-container">
                <p>{{address.name || '马力'}}</p>
                <p>{{address.completedAddress|| '四川省成都市浦江县XX村XX号'}}</p>
                <p>{{address.phone || 12345678910}}</p>
            </div>
            <span slot="reference" class="address-span">物流信息</span>
        </el-popover>
    </div>
</template>

<script>
    export default {
        name: 'CAddressPopover',
        props: ['address']
    }
</script>

<style scoped lang="scss">
    .address-popover-container {
        padding-left: 5px;

        p {
            margin: 10px !important;
            font-size: 14px !important;
            color: rgb(0, 0, 0);

            &:nth-child(1) {
                font-weight: bold;
            }
        }
    }

    .address-span {
        &:hover {
            color: #ffffff;
            padding-bottom: 2px;
            border-bottom: 1px solid #ffffff;
        }
    }
</style>
