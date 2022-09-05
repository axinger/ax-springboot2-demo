package com.axing.demo;

import cn.hutool.core.lang.func.LambdaUtil;
import org.junit.jupiter.api.Test;

public class LambdaUtilTest {

    @Test
    void test() {
        String fieldName = LambdaUtil.getFieldName(Person::getName);
        System.out.println("fieldName = " + fieldName);

        String methodName = LambdaUtil.getMethodName(Person::getName);
        System.out.println("methodName = " + methodName);
    }
}
