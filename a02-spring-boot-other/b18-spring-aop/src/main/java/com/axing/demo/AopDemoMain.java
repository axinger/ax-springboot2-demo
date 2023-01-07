package com.axing.demo;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AopDemoMain {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
