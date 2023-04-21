package com.axing.demo.controller;

import com.axing.demo.dto.LoginDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postTest")
public class PostController {

    @PostMapping("/login")
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return List.of(username, password);
    }

    @PostMapping("/login2")
    public Object login2(@RequestBody @Validated LoginDTO dto) {
        return dto;
    }
}
