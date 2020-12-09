<template>
    <!-- 购物车组件 -->
    <div class="c-cart" @click="goToCart">
        <!-- 购物车图标 -->
        <img src="./../../../public/images/public/cart.png">
        <!-- 展示购物车商品数 -->
        <span v-if="getCartLength">{{getCartLength}}</span>
    </div>
</template>

<script>
    export default {
        name: 'CCard',
        props: {
            cardNumber: {
                type: Number,
                default: 1
            }
        },
        computed: {
            getCartLength() {
                return this.$store.getters.getUserInfo.cartLength
            }
        },
        methods: {
            goToCart() {
                this.$router.push('/cart')
            }
        }
    }
</script>

<style scoped lang="scss">
    .c-cart {

        display: flex;

        img {
            width: 50px;
            height: 50px;
            opacity: .9;
        }

        span {
            display: block;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background: #ffffff;
            text-align: center;
            line-height: 30px;
            font-size: 22px;
            color: #000000;
            margin-left: -10px;
        }

    }
</style>
