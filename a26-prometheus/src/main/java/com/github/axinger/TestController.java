package com.github.axinger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("")
    public String home() {
        return "ok";
    }
}
