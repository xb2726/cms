package com.serendipity.extra.query;

import java.lang.annotation.*;

/**
 * @Auther bin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Condition {

    // 查询的条件
    MatchMode machMode() default MatchMode.EQ;

    // 模糊匹配方式，与like方式配合使用
    LikeMode likeMode() default LikeMode.ANYWHERE;

    // 对应的条件字段
    String target() default "";


    // 条件描述
    String value() default "";

    // 与IN条件配合使用
    String[] targets() default {};
}
