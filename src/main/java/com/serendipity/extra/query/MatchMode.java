package com.serendipity.extra.query;

public enum MatchMode {
    // 天等于
    DAY_EQ,

    // 小于某天
    DAY_LT,

    // 大于某天
    DAY_GE,

    // 小于
    LT,

    // 小于等于
    LE,

    // 大于
    GT,

    // 大于等于
    GE,

    // like模糊匹配，可以和likeMode连用
    LIKE,

    // in
    IN,

    // 空
    NULL,

    // 不为空
    NOT_NULL,

    // 等于
    EQ,

    // 不等于
    NE,

    // not_in
    NOT_IN,

    // 或
    OR,

    // 等于某月
    MONTH_EQ
}
