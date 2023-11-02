package com.axing.server;

import com.axing.common.response.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component

// name 或者 name 必须有，
@FeignClient(value = "abc", url = "http://localhost:8080")

public interface UserServiceHttp {

    @GetMapping("/getUserToken")
    Result<Map<String, Object>> getUseToken(@RequestParam("username") String username,
                                            @RequestParam("password") String password,
                                            @RequestHeader("token") String token);


}

