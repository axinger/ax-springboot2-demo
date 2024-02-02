package com.axing.demo;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.EnumUtil;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumUtilTests {
    @Test
    void test1() {
        List<String> names = EnumUtil.getNames(TestEnum.class);
//结果：[TEST1, TEST2, TEST3]

        Console.log(names);
        System.out.println(names);
    }

    //定义枚举
//    public enum TestEnum{
//        TEST1("type1"), TEST2("type2"), TEST3("type3");
//
//        private TestEnum(String type) {
//            this.type = type;
//        }
//
//        private String type;
//
//        public String getType() {
//            return this.type;
//        }
//    }

    @Test
    void test2() {
//        List<Object> types = EnumUtil.getFieldValues(TestEnum.class, "type");
        List<Object> types = EnumUtil.getFieldValues(TestEnum.class, "type1");
//结果：[type1, type2, type3]

        System.out.println("types = " + types);
    }

    @Test
    void test3() {
        Map<String, TestEnum> enumMap = EnumUtil.getEnumMap(TestEnum.class);
        System.out.println("enumMap = " + enumMap);

        TestEnum test1 = enumMap.get("TEST1");// 结果为：TestEnum.TEST1
        System.out.println("test1 = " + test1);

    }

    @Test
    void test4() {
        Map<String, Object> enumMap = EnumUtil.getNameFieldMap(TestEnum.class, "type");
        Object test1 = enumMap.get("TEST1");// 结果为：type1
        System.out.println("test1 = " + test1);
    }

    @Test
    void test5() {
        Map<String, Object> enumMap = EnumUtil.getNameFieldMap(TestEnum.class, "age");
        System.out.println("enumMap = " + enumMap);


        Object test1 = enumMap.get("TEST1");// 结果为：type1
        System.out.println("test1 = " + test1);
        System.out.println("TestEnum.TEST1.name = " + TestEnum.TEST1.name());
        Object o = enumMap.get(TestEnum.TEST1.name());
        System.out.println("o = " + o);
    }

    @Test
    void test6() {
//        EnumUtil.get(TestEnum.class,"name")
        List<String> fieldNames = EnumUtil.getFieldNames(TestEnum.class);
        System.out.println("fieldNames = " + fieldNames);

        LinkedHashMap<String, TestEnum> enumMap = EnumUtil.getEnumMap(TestEnum.class);
        System.out.println("enumMap = " + enumMap);
    }

    //定义枚举
    @Getter
    public enum TestEnum {
        TEST1("1", "A"), TEST2("2", "B"), TEST3("3", "C");

        private final String type;
        private final String age;

        private TestEnum(String type, String age) {
            this.type = type;
            this.age = age;
        }
    }
}
