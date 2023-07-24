package com.axing.b.service.impl;

import com.axing.b.service.BService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BServiceImpl implements BService {

    @Value("${axing.person.id}")
    private String name;

    @Override
    public String test() {
        return "b==============="+name;
    }
}
