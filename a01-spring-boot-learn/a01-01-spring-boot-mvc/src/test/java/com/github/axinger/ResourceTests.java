package com.github.axinger;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ResourceTests {

    @Test
    public void test1() throws Exception {

        System.out.println("java当前目录,main函数,和springboot 项目不一样: " + System.getProperty("user.dir"));

        File file = ResourceUtils.getFile("classpath:my.yml");
        String path1 = file.getAbsolutePath();
        System.out.println("path1 = " + path1);

    }

    @Test
    public void test2() throws Exception {

//        path2 = file:/D:/code/axinger/ax-springboot2-demo/a01-spring-boot-learn/a01-01-spring-boot-mvc/target/classes/my.yml
//        path3 = D:\code\axinger\ax-springboot2-demo\a01-spring-boot-learn\a01-01-spring-boot-mvc\target\classes\my.yml
//        ClassPathResource resource = new ClassPathResource("my.yml");
        ClassPathResource resource = new ClassPathResource("mybatis-config.xml");
        String path2 = resource.getURL().toString();
        String filename = resource.getFilename();
        System.out.println("filename = " + filename);
        System.out.println("path2 = " + path2);

        File file = resource.getFile();
        String path3 = file.getAbsolutePath();
        System.out.println("path3 = " + path3);

        InputStream inputStream = resource.getInputStream();
        System.out.println("inputStream = " + inputStream);
    }

    @Test
    public void test3() throws InterruptedException, IOException {

        System.out.println("java当前目录,main函数,和springboot 项目不一样: " + System.getProperty("user.dir"));

        File file = ResourceUtils.getFile("classpath:test.js");
        String path1 = file.getAbsolutePath();
        System.out.println("path1 = " + path1);

        ClassPathResource resource = new ClassPathResource("test.js");
        File file2 = resource.getFile();
        String path2 = file2.getAbsolutePath();

        InputStream inputStream = resource.getInputStream();
        System.out.println("inputStream = " + inputStream);

        // 定义要执行的命令
        String nodePath = "node"; // Node.js 的路径（如果已添加到环境变量，直接写 "node" 即可）
        String jsFilePath = path2; // 替换为你的 JavaScript 文件路径

        // 创建 ProcessBuilder 对象
        ProcessBuilder processBuilder = new ProcessBuilder(nodePath, jsFilePath, "arg1", "arg2");

        try {
            // 启动进程
            Process process = processBuilder.start();

            // 读取输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程结束并获取退出码
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(5);
    }

    //cn.hutool.core.io.resource
    @Test
    public void test4(){
        String string = ResourceUtil.readUtf8Str("123.json");

        System.out.println("string = " + string);

        //当资源不存在时返回null
        InputStream inputStream = ResourceUtil.getStreamSafe("123.json");
        System.out.println("inputStream = " + inputStream);

        URL url = ResourceUtil.getResource("123.json");
        System.out.println("url = " + url);

        List<URL> urlList = ResourceUtil.getResources("dir1/");
        System.out.println("urlList = " + urlList);

        Resource resource = ResourceUtil.getResourceObj("123.json");
        URL url1 = resource.getUrl();
        System.out.println("url1 = " + url1);
    }
}
