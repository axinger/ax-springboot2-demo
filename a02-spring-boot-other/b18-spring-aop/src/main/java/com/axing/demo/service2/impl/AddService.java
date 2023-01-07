package com.axing.demo.service2.impl;

import cn.hutool.core.util.StrUtil;
import com.axing.demo.model.ApiVersion;
import org.springframework.stereotype.Service;

@Service
@ApiVersion
public class AddService {
    public String add3(String a, String b) {
        return StrUtil.format("add3:{}_{}", a, b);
    }
}
