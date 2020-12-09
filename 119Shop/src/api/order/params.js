export const pCreateOrder = Object.seal({
    userId: '1',
    addressId: '',
    orderList: []
})

export const pCreateOrderItem = Object.seal({
    userId: 1,
    goodsId: '',
    purchaseNum: 0
})

export const pPayForOrder = Object.seal({
    orderId: '',
    userId: 1,
    paykey: ''
})

export const pGetOrderList = Object.seal({
    userId: 1,
    currentPage: 0,
    pageSize: 6
})

export const pDeleteOrder = Object.seal({
    userId: 1,
    orderId: ''
})
