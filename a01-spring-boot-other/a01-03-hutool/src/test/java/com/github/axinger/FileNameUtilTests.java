package com.github.axinger;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileNameUtilTests {
    @Test
    void test() {
        File file = FileUtil.file("/opt/test.txt");

// "test"
        String name = FileNameUtil.mainName(file);
        System.out.println("name = " + name);

// "txt"
        String extName = FileNameUtil.extName(file);
        System.out.println("extName = " + extName);
        String prefix = FileNameUtil.getPrefix(file);
        System.out.println("prefix = " + prefix);
        String suffix = FileNameUtil.getSuffix(file);
        System.out.println("suffix = " + suffix);

    }
}
