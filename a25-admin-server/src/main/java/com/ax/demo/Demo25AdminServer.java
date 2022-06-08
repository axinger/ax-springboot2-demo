package com.ax.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class Demo25AdminServer {
    public static void main(String[] args) {
        SpringApplication.run(Demo25AdminServer.class, args);
    }
}
