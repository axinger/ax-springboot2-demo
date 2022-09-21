package com.axing.demo.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    String string1 = "A";

    String string2 = "B";

    List<String> list;
    List<String> list2;

    Map map1;
    Map map2;
    Boolean aBoolean;
    Boolean aBoolean2;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    Date date;


    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format = "yyyy年MM-dd HH:mm:ss")
    LocalDateTime localDateTime;

    Long aLong;
    Double aDouble;

    Float aFloat;

    Dog dog;

    private transient String aTransient;
    private transient String aaTransient;
}
