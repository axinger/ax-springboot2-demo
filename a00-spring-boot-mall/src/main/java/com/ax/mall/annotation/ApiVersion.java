package com.ax.mall.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 定义接口的版本号 以v+数字样式
     */
    int value() default 1;
}