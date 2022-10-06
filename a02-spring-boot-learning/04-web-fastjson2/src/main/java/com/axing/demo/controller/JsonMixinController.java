package com.axing.demo.controller;

import com.axing.demo.JsonMixin.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonMixinController {

    @RequestMapping(value = "/JsonMixin")
    public Object json() {
        Person person = new Person();
        person.setName("jim");
        person.setAge(10);
        System.out.println("person = " + person);

        return person;
    }
}
