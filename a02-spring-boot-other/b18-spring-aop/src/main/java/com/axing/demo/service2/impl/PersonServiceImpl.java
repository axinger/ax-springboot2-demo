package com.axing.demo.service2.impl;

import cn.hutool.core.util.StrUtil;
import com.axing.demo.annotation.ApiVersion;
import com.axing.demo.service2.PersonService;
import org.springframework.stereotype.Service;

@Service
@ApiVersion(value = 2)
public class PersonServiceImpl implements PersonService {
    @Override

    // @ApiVersion
    public String add(String a, String b) {
        return StrUtil.format("add:{}_{}", a, b);
    }

    @Override
    public String add2(String a, String b) {
        return StrUtil.format("add2:{}_{}", a, b);
    }
    // @Override
    // public String add3(String a, String b) {
    //     return StrUtil.format("add3:{}_{}",a,b);
    // }
}
