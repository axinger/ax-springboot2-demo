package com.github.axinger.javafxdemo.server.impl;

import com.github.axinger.javafxdemo.server.HelloServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloServerImpl implements HelloServer {
    @Override
    public String hello() {
        return "111111"+ LocalDateTime.now();
    }
}
