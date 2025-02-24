package com.github.axinger;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.EnumUtil;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumUtilTests {
    @Test
    void test1() {
        List<String> names = EnumUtil.getNames(Gender.class);
//结果：[TEST1, TEST2, TEST3]

        Console.log(names);
        System.out.println(names);
    }

    //定义枚举
//    public enum Gender{
//        TEST1("type1"), TEST2("type2"), TEST3("type3");
//
//        private Gender(String type) {
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

        // 指定属性的值
        List<Object> types = EnumUtil.getFieldValues(Gender.class, LambdaUtil.getFieldName(Gender::getCode));
//结果：types = [1, 2, 3]
        System.out.println("types = " + types);

        List<Object> fieldValues = EnumUtil.getFieldValues(Gender.class, LambdaUtil.getFieldName(Gender::getAlias));
        System.out.println("fieldValues = " + fieldValues);
    }

    @Test
    void test3() {
        Map<String, Gender> enumMap = EnumUtil.getEnumMap(Gender.class);
        System.out.println("enumMap = " + enumMap);

        Gender test1 = enumMap.get("FEMALE");// 结果为：Gender.FEMALE
        System.out.println("test1 = " + test1);

    }

    @Test
    void test4() {
        Map<String, Object> enumMap = EnumUtil.getNameFieldMap(Gender.class, LambdaUtil.getFieldName(Gender::getCode));
        Object test1 = enumMap.get("MALE");// 结果为：type1
        System.out.println("test1 = " + test1);
    }

    @Test
    void test5() {
        Map<String, Object> enumMap = EnumUtil.getNameFieldMap(Gender.class, LambdaUtil.getFieldName(Gender::getAlias));
        System.out.println("enumMap = " + enumMap);


        Object test1 = enumMap.get("MALE");// 结果为：type1
        System.out.println("test1 = " + test1);
        System.out.println("Gender.TEST1.name = " + Gender.MALE.name());
        Object o = enumMap.get(Gender.MALE.name());
        System.out.println("o = " + o);
    }

    @Test
    void test6() {
//        EnumUtil.get(Gender.class,"name")
        List<String> fieldNames = EnumUtil.getFieldNames(Gender.class);
        System.out.println("fieldNames = " + fieldNames);

        LinkedHashMap<String, Gender> enumMap = EnumUtil.getEnumMap(Gender.class);
        System.out.println("enumMap = " + enumMap);
    }


}
