package com.axing.demo.controller;

import com.axing.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonMixinController {

    @GetMapping(value = "/JsonMixin")
    public Object json() {
        User person = new User();
        person.setName("jim");
        person.setAge(10);
        System.out.println("person = " + person);
        return person;
    }
}
