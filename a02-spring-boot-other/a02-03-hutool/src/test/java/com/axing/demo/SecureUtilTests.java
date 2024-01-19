package com.axing.demo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import org.junit.jupiter.api.Test;

public class SecureUtilTests {

    @Test
    void test1(){
        String s = SecureUtil.md5("123");
        System.out.println("s = " + s);
    }
}
