package com.github.axinger;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ProcessBuilderTests {

    @Test
    public void test01() {
        URL url = ResourceUtil.getResource("js/test.js");
        String absolutePath = FileUtil.getAbsolutePath(url.getPath());


        // 绝对路径
        ProcessBuilder pb = new ProcessBuilder("node", absolutePath);

        // 可选：设置工作目录（比如 JS 文件所在目录）
//        pb.directory(new File("/opt"));

        // 合并错误输出到标准输出，方便一起读取
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();

            // 读取 JS 输出
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("每一行的log: " + line);
            }

            // 等待执行完成
            int exitCode = process.waitFor();
            System.out.println("退出获取值: " + exitCode);

        } catch (Exception e) {

            System.err.println("e = " + e);
        }
    }
}
