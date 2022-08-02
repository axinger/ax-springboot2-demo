package com.axing.demo;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StrTests.java
 * @description TODO
 * @createTime 2022年08月02日 17:14:00
 */

public class StrTests {

    /**
     * 对齐
     */
    @Test
    void test_1() {
        System.out.println(StrUtil.padPre("1", 3, "0"));
    }
}
