package com.github.axinger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DockerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerDemoApplication.class, args);
        // 使用默认浏览器打开
        try {
            Runtime.getRuntime().exec(String.format("cmd /c start %s", "http://localhost:12021"));
        } catch (Exception e) {
            log.warn("打开客户端主页失败", e);
        }
    }
}
