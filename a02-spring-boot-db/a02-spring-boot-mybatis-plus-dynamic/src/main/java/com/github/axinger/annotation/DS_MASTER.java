package com.github.axinger.annotation;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("db_ax_test")
public @interface DS_MASTER {
}
