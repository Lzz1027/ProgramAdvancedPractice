export const pAddToCart = Object.seal({
    userId: null,
    goodsId: '',
    goodsNum: null
})

export const pUpdateCart = Object.seal({
    userId: 1,
    goodsId: '',
    goodsNum: null
})

export const pDeleteFromCart = Object.seal(({
    userId: 1,
    goodsId: ''
}))

export const pGetCartList = Object.seal({
    userId: 1,
    pageSize: 6,
    curPage: 0
})
