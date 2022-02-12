package com.binx.web.swagger;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerGroup {
    String value() default "";

    String version() default "1.1.0";

    String title();

    String description() default "";

    String basePackage() default "com.yunzhicloud";

    String[] paths() default {};
}
