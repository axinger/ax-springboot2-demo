package com.github.axinger;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class Python脚本Tests {

    /*

在 Spring Boot 中，ResourceUtils.getFile() 和 ClassPathResource 获取文件路径的方式有以下区别：

1. ResourceUtils.getFile("classpath:script.py")
返回的是标准的 java.io.File 对象

getAbsolutePath() 返回的是本地文件系统的绝对路径（Windows 下带盘符）

示例输出：D:\code\...\target\classes\script.py

缺点：当应用打包为 JAR 文件运行时，这种方法会失败，因为 JAR 中的资源不是独立的文件

2. new ClassPathResource("script.py")
是 Spring 提供的专门用于类路径资源访问的类

getURL().getPath() 返回的是 URL 编码的路径（以 / 开头）

示例输出：/D:/code/.../target/classes/script.py

优点：在 JAR 包中也能工作，因为它不依赖于文件系统路径
     */
    @Test
    public void testPythonTests() {
        // 要传递的两个数字参数
        String num1 = "10";
        String num2 = "3";

        try {
            /// 可用
            File file = ResourceUtils.getFile("classpath:script.py");
            String path1 = file.getAbsolutePath();
            System.out.println("path1 = " + path1);

            /// 不可用
            ClassPathResource resource = new ClassPathResource("script.py");
            String path = resource.getURL().getPath();
            System.out.println("path = " + path);

            /// 可用
            String path2 = resource.getFile().getAbsolutePath();
            System.out.println("path2 = " + path2);

            // 创建ProcessBuilder执行Python脚本
            ProcessBuilder pb = new ProcessBuilder("python", path2, num1, num2);
            Process process = pb.start();

            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python输出: " + line);

                // 这里可以添加JSON解析代码来处理返回的复杂数据
                // 例如使用Jackson或Gson库解析JSON
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行结束，退出码: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
1. JAR 包内的资源不是独立文件
当应用打包为 JAR 后：

所有资源文件（如 script.py）会被压缩到 JAR 包内部，不再是文件系统中的独立文件。

此时通过 ClassPathResource.getURL().getPath() 获取的路径（如 /D:/.../app.jar!/script.py）是无效的：

ProcessBuilder 无法直接访问 JAR 内部的资源路径。

操作系统也不认识这种嵌入在 JAR 中的路径格式。
     */

    @Test
    void test2() {
        String num1 = "10";
        String num2 = "3";

        try {
            ClassPathResource resource = new ClassPathResource("script.py");

            // 创建临时文件
            File tempFile = File.createTempFile("script", ".py");
            try (InputStream in = resource.getInputStream();
                 FileOutputStream out = new FileOutputStream(tempFile)) {
                FileCopyUtils.copy(in, out);
            }

            // 设置临时文件在程序退出时删除
            tempFile.deleteOnExit();

            // 执行 Python 脚本
            ProcessBuilder pb = new ProcessBuilder("python", tempFile.getAbsolutePath(), num1, num2);
            Process process = pb.start();

            // 读取输出
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Python输出: " + line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行结束，退出码: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


