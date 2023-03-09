package com.axing.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.axing.common.dto.PageDTO;
import com.axing.demo.model.Dog;
import com.axing.demo.model.PersonDTO;
import com.axing.demo.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "UserController")
public class UserController {

    @GetMapping("/user")
    public UserDTO getUser() {
        UserDTO user = new UserDTO("jim", "123");
//        User user2 = new User();
        String name = user.username();
        System.out.println("name = " + name);

        UserDTO.range range = new UserDTO.range(1, 2);

        System.out.println(user.all());
        return user;
    }

    @GetMapping("/person")
    public PersonDTO person() {

        PersonDTO person = new PersonDTO();
        person.setName("jim");
        return person;
    }

    @Operation(summary = "post分页参数")
    @PostMapping("/page")
    public Object person(@RequestBody PageDTO<Dog> dto) {
        System.out.println("dto = " + dto);
        System.out.println(dto.data().id());
        System.out.println("JSON.toJSONString(dto) = " + JSON.toJSONString(dto));
        return dto;
    }


}

