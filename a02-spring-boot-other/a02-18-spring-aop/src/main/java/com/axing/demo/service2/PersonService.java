package com.axing.demo.service2;

import com.axing.demo.annotation.ApiVersion;

@ApiVersion
public interface PersonService {

    // @ApiVersion
    // 在接口中,不会被apo
    String add(String a, String b);

    String add2(String a, String b);
}
