package com.axing.demo.annotation;

import com.axing.demo.entity.MsgTypeEnum;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {

    MsgTypeEnum value();
}
