package com.github.axinger.controller;

import com.axing.demo.dto.ILoginGroup;
import com.axing.demo.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(value = "/login")
    public Object json(@RequestBody @Validated(value = {ILoginGroup.class}) UserDTO dto) {
        System.out.println("dto = " + dto);
        return dto;
    }
}
