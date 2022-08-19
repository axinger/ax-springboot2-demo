package com.axing.demo;

import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName UuidTests.java
 * @description TODO
 * @createTime 2022年07月28日 14:00:00
 */

public class UuidTests {


    @Test
    void test_uuid() {

        System.out.println("IdUtil.simpleUUID() = " + IdUtil.simpleUUID());

        System.out.println("UUID.fastUUID() = " + UUID.randomUUID());

        System.out.println("IdUtil.fastUUID() = " + IdUtil.fastUUID());
    }
}
