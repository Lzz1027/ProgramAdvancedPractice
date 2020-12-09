/**
 * 基础常量，存放一行的常量
 */

export const MAX_PRICE = 1000000000
export const PAGE_SIZE = 6

/**
 * 对象常量，存放多行的常量
 */

export const TIP_TYPE = Object.freeze({
    INFO: 'info',
    SUCCESS: 'success',
    WARNING: 'warning',
    ERROR: 'error'
})

export const PRICE_RANGE = Object.freeze({
    ALL: {
        start: 0,
        limit: MAX_PRICE,
        label: '全部价格',
        value: 'ALL'
    },
    CHEEPER: {
        start: 3000,
        limit: 5000,
        label: '3000￥ - 5000￥',
        value: 'CHEEPER'
    },
    CHEEP: {
        start: 5000,
        limit: 8000,
        label: '5000￥ - 8000￥',
        value: 'CHEEP'
    },
    EXPENSIVE: {
        start: 8000,
        limit: 12000,
        label: '8000￥ - 12000￥',
        value: 'EXPENSIVE'
    },
    EXPENSIVER: {
        start: 12000,
        limit: MAX_PRICE,
        label: '12000￥ +',
        value: 'EXPENSIVER'
    }
})

export const SORT_TYPE = Object.freeze({
    ASC: 'ASC',
    DESC: 'DESC'
})
