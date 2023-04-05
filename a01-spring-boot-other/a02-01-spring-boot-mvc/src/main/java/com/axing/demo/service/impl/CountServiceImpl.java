package com.axing.demo.service.impl;

import com.axing.demo.service.CountService;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService {

    private int count;

    @Override
    public Integer count() {
        return count++;
    }
}
