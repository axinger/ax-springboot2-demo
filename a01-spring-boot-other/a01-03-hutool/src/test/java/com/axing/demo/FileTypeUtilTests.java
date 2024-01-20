package com.axing.demo;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTypeUtilTests {
    @Test
    void test1() {
        File file = FileUtil.file("d:/test.jpg");
        String type = FileTypeUtil.getType(file);
//输出 jpg则说明确实为jpg文件
        Console.log(type);
    }
}
