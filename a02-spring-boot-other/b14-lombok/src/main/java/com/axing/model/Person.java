package com.axing.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
public class Person {

    private String name;

    /**
     * None来禁用getter 和 setter 的生成。
     */
    @Setter(value = AccessLevel.NONE)
    private String testNone;

    @Setter(value = AccessLevel.PRIVATE)
    private String testPrivate;

    /**
     * 生成的getter和setter不会以bean标准 get，is 或 set 为前缀，会和属性名一样
     * 但jackjson无法正常解析,需要注意
     */
    @Accessors(fluent = true)
    private String aFirst;

    /**
     * 可以使用 += 添加前缀，或者使用 -= 删除前缀
     */
    @Accessors(prefix = {"+=my"})
    private String testPre;


    void test() {

    }

}
