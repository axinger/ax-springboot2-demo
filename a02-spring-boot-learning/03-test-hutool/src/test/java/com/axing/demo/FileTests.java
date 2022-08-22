package com.axing.demo;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTests {

    @Test
    void test(){
        File file = new File("a/b/c.jpg");
        String name = FileUtil.getName(file);
        System.out.println("name = " + name);
        String suffix = FileUtil.getSuffix(file);
        System.out.println("suffix = " + suffix);
        String prefix = FileUtil.getPrefix(file);
        System.out.println("prefix = " + prefix);

        String extName = FileUtil.extName(file);
        System.out.println("extName = " + extName);
        String mainName = FileUtil.mainName(file);
        System.out.println("mainName = " + mainName);

        String tmpDirPath = FileUtil.getTmpDirPath();
        System.out.println("tmpDirPath = " + tmpDirPath);
        String userHomePath = FileUtil.getUserHomePath();
        System.out.println("userHomePath = " + userHomePath);
        File userHomeDir = FileUtil.getUserHomeDir();
        String path = userHomeDir.getPath();
        System.out.println("path = " + path);

        File touch = FileUtil.touch(userHomePath, "/a/b/c.docx");
        System.out.println("touch = " + touch.getPath());

        String path1 = FileUtil.mkdir(userHomePath+"/f/b/c").getPath();
        System.out.println("path1 = " + path1);

        String type = FileTypeUtil.getTypeByPath("/Users/xing/Develop/work/iot-document/118-加热炉-设备.xlsx");
        System.out.println("type = " + type);
    }
}
