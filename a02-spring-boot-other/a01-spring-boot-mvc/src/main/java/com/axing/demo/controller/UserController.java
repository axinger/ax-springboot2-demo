package com.axing.demo.controller;

import com.axing.demo.model.PersonDTO;
import com.axing.demo.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}

