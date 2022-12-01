package com.axing.service.impl;

import com.axing.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private int count;

    @Override
    public Integer count() {
        return count++;
    }
}
