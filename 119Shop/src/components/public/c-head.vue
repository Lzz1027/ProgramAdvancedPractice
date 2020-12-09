<template>
    <!-- 顶部栏组件 -->
    <div class="c-head">
        <!-- 头部栏 -->
        <div class="container">
            <!-- 左侧图表区 -->
            <div class="head-left">
                <!-- Logo -->
                <img src="./../../../public/images/public/laptop.png">
                <div class="head-text xy-center">
                    <span>119</span>
                    <span>shop119.com</span>
                </div>
            </div>
            <!-- 右侧信息区 -->
            <div class="head-right">
                <!-- 登录显示用户头像组件 -->
                <c-header-image v-if="userInfo.nickName"/>
                <!-- 不登录显示登录、注册 -->
                <div v-else>
                    <el-button class="head-right-btn" type="primary" @click="showLogin(true)">登录</el-button>
                    <el-button class="head-right-btn" type="primary" @click="showLogin(false)">注册</el-button>
                </div>
                <!-- 购物车组件 -->
                <c-card class="card" :card-number="cardNumber"></c-card>
            </div>
        </div>
        <!-- 轮播图组件 -->
        <c-banner></c-banner>
        <!-- 提示登录组件 -->
        <c-login :show-login="isShowLogin" :is-login="isLogin" @hide="closeLogin" @changeMode="onChangeMode"></c-login>
    </div>
</template>

<script>
    import CLogin from './c-login'
    import CCard from './c-cart'
    import CBanner from './c-banner'
    import CHeaderImage from './c-header-image'

    export default {
        name: 'CHead',
        components: {
            CLogin,
            CCard,
            CBanner,
            CHeaderImage
        },
        data() {
            return {
                isShowLogin: false,
                isLogin: true,
                cardNumber: 2
            }
        },
        computed: {
            userInfo() {
                return this.$store.getters.getUserInfo
            }
        },
        methods: {
            showLogin(type) {
                this.isLogin = type
                this.isShowLogin = true
            },
            closeLogin() {
                this.isShowLogin = false
            },
            // 改变登录模式
            onChangeMode(mode) {
                this.isLogin = mode
            },
            // 监听登录事件
            onLogin() {
                this.isShowLogin = true
            }
        },
        mounted() {
            this.$bus.$on('login', () => {
                this.onLogin()
            })
        }
    }
</script>

<style scoped lang="scss">
    .c-head {
        background: rgb(0, 0, 0);
        width: 100%;

        .container {
            height: 89px;
            justify-content: space-between;
        }

        .head-left {
            display: flex;

            img {
                width: 58px;
                height: 58px;
                margin-left: 5px;
            }

            .head-text {
                flex-direction: column;
                margin-left: 12px;

                span:nth-child(1) {
                    color: rgb(255, 255, 255);
                    font-size: 40px;
                    line-height: 40px;
                    font-family: DFPHaiBaoW12-GB;
                }

                span:nth-child(2) {
                    font-size: 22px;
                    line-height: 22px;
                    font-family: FZMiaoWuS-GB;
                    color: rgb(255, 255, 255);
                    margin: 0;
                }

            }

        }

        .head-right {
            display: flex;
            align-items: center;

            .head-right-btn {
                width: 101px;
                height: 40px;
                border-radius: 8px;
                background: rgb(100, 100, 100);
                color: #ffffff;
                border: none;
                margin-right: 10px;

                :hover {
                    opacity: .4;
                }

            }

            .card {
                margin-right: 10px;
            }

        }
    }
</style>
