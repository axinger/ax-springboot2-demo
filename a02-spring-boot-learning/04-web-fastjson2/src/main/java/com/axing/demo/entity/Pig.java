package com.axing.demo.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Pig {

    /**
     * 第二个字母大写,JSON解析
     * JSONField 对象解析成json的值
     */
    @JSONField(name = "aName")
    private String aName;

    private String aAge;
}
