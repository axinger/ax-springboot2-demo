package com.axing.demo;

import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.Optional;

public class FileTests {

    @Test
    void test() {
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

        String path1 = FileUtil.mkdir(userHomePath + "/f/b/c").getPath();
        System.out.println("path1 = " + path1);

        String type = FileTypeUtil.getTypeByPath("/Users/xing/Develop/work/iot-document/118-加热炉-设备.xlsx");
        System.out.println("type = " + type);
    }


    @Test
    void test2() {
        File[] ls = FileUtil.ls("/Users/xing/Desktop/flink-ck");


        Optional<File> min1 = List.of(ls).stream()
                .filter(val -> FileUtil.isDirectory(val))
                .min((val1, val2) -> {
                    FileTime t1 = null;
                    FileTime t2 = null;
                    try {
                        t1 = Files.readAttributes(Paths.get(val1.getPath()), BasicFileAttributes.class).creationTime();
                        t2 = Files.readAttributes(Paths.get(val2.getPath()), BasicFileAttributes.class).creationTime();
                    } catch (Exception e) {
                    }
                    return CompareUtil.compare(t1, t2);
                });

        System.out.println("min = " + min1);

        File[] ls1 = FileUtil.ls(min1.get().getPath());
        Optional<File> min2 = List.of(ls1).stream()
                .filter(val -> FileUtil.isDirectory(val) && StrUtil.startWith(val.getName(), "chk-"))
                .min((val1, val2) -> {
                    FileTime t1 = null;
                    FileTime t2 = null;
                    try {
                        t1 = Files.readAttributes(Paths.get(val1.getPath()), BasicFileAttributes.class).creationTime();
                        t2 = Files.readAttributes(Paths.get(val2.getPath()), BasicFileAttributes.class).creationTime();
                    } catch (Exception e) {
                    }
                    return CompareUtil.compare(t1, t2);
                });

        System.out.println("min2 = " + min2);

    }
}
