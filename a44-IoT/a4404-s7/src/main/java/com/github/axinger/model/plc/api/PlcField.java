package com.github.axinger.model.plc.api;

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 格式 "%DB1.DBW150:WORD"
 */
public @interface PlcField {

    String offset();

    PlcDataType dataType();
}
