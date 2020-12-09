<template>
    <div class="home">
        <!-- 顶部栏组件 -->
        <c-head></c-head>
        <!-- 面包屑组件 -->
        <div class="bread">
            <c-bread></c-bread>
        </div>
        <!-- 商品列表 -->
        <div class="goods-list-container">
            <!-- 价格筛选组件 -->
            <c-price-range class="price-range" @change="onPriceChange"></c-price-range>
            <!-- 商品列表 -->
            <div class="goods-list">
                <!-- 商品容器 -->
                <div class="good-container" v-if="goodsList.length"
                     :class="(goodsList.length % 3) !== 0 ? 'fill-space' : ''">
                    <c-good-item v-for="(item, key) in goodsList" :key="key" :goods-item="item"/>
                </div>
                <!-- 懒加载 -->
                <!--                <c-paging class="paging"/>-->
                <div class="load-infinite infinite-list" v-infinite-scroll="loadMore"
                     infinite-scroll-disabled="busy" infinite-scroll-distance="10">
                    <img src="../../assets/loading-svg/loading-bars.svg" v-show="loading">
                </div>
            </div>
        </div>
        <!-- 底部栏组件 -->
        <c-foot/>
        <!-- 对话框组件 -->
        <c-modal width="400px" :is-show-modal="isShowModal" v-on:confirm="onConfirm" v-on:cancel="onCancel"></c-modal>
    </div>
</template>

<script>
    import CHead from '@components/public/c-head'
    import CFoot from '@components/public/c-foot'
    import CModal from '@components/public/c-modal'
    import CBread from '@components/public/c-bread'
    import CPriceRange from './components/c-price-range'
    import CSortBtn from './components/c-sort-btn'
    import CGoodItem from './components/c-good-item'
    import { SORT_TYPE } from '@/config/constant'
    import { pGetGoodsList } from '@api/goods/params'

    export default {
        name: 'home',
        components: {
            CHead,
            CFoot,
            CModal,
            CBread,
            CPriceRange,
            CSortBtn,
            CGoodItem
        },
        data() {
            return {
                isShowModal: false,
                like: {
                    isLike: false,
                    likeNum: 9
                },
                num: 2,
                sortType: SORT_TYPE.ASC,
                goodsList: [],
                loading: false,
                busy: false
            }
        },
        mounted() {
            console.log('mounted')
            this.getGoodsList()
            this.getGoodsLikeList()
            this.$bus.$on('login', () => {
                this.getGoodsLikeList()
            })
        },
        methods: {
            onConfirm() {
                this.isShowModal = false
                console.log('确认')
            },
            onCancel(type) {
                this.isShowModal = false
                if (type === 'close') {
                    return
                }
                console.log('取消')
            },
            onLike() {
                this.like.likeNum += this.like.isLike ? -1 : 1
                this.like.isLike = !this.like.isLike
            },
            onChange(num) {
                console.log(num)
                this.num = num
            },
            onPriceChange(priceRange) {
                console.log(this.$store.getters.getUserInfo.id)
                console.log(priceRange)
                pGetGoodsList.priceRange = priceRange
                this._resetPageNum()
            },
            changeSort() {
                this.sortType = this.sortType === SORT_TYPE.ASC ? SORT_TYPE.DESC : SORT_TYPE.ASC
                console.log(this.sortType)
                pGetGoodsList.sortType = this.sortType
                this._resetPageNum()
            },
            // 是否重置
            getGoodsList(isReset = false) {
                this.$api.goods.getGoodsList(pGetGoodsList).then(res => {
                    console.log(res)
                    this.loading = false
                    this.busy = false
                    if (isReset) {
                        this.goodsList = []
                    }
                    if (res.goodsList.length) {
                        this.goodsList = this.goodsList.concat(res.goodsList)
                    } else {
                        this.busy = true
                    }
                })
            },
            loadMore() {
                this.busy = true
                this.loading = true
                setTimeout(() => {
                    ++pGetGoodsList.currentPage
                    this.getGoodsList()
                }, 500)
            },
            // 获取商品点赞列表
            getGoodsLikeList() {
                this.$api.user.getGoodsLikeList().then(res => {
                    this.$store.dispatch('setGoodsLikeList', res.goodsLikeList)
                }).catch(err => {
                    console.error(err)
                    if (!err.goodsLikeList) {
                        this.$store.dispatch('setGoodsLikeList', [])
                    }
                })
            },
            // 重置页码
            _resetPageNum() {
                pGetGoodsList.currentPage = 0
                this.getGoodsList(true)
            }
        },
        destroyed() {
            console.log('destoryed')
            pGetGoodsList.currentPage = 0
            pGetGoodsList.sortType = 'ASC'
        }
    }
</script>

<style scoped lang="scss">
    .home {
        width: 100%;
        /*background: #42b983;*/
        background: rgb(0, 0, 0);
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;

        .bread {
            margin-top: 20px;
            width: 100%;
            background: #000000;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .goods-list-container {
            width: 1200px;
            display: flex;
            justify-content: center;
            margin-top: 10px;

            .price-range{
                    margin-top: 21px;
            }
                       
            .goods-list {
                display: flex;
                flex-direction: column;
                
                .good-container {
                    width: 920px;
                    margin-left: 26px;
                    display: flex;
                    justify-content: space-between;
                    flex-wrap: wrap;
                }

                .fill-space:after {
                    content: "";
                    width: 288px;
                    height: 496px;
                }

                .paging {
                    margin: 0 auto;
                }

            }
        }
    }

    .load-infinite {
        margin-top: 50px;
        height: 100px;
        text-align: center;
        /*background: ;*/

        img {
            width: 50px;
            height: 50px;
        }
    }
</style>
