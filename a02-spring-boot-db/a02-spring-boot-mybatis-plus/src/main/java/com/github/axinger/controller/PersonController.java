package com.github.axinger.controller;

import com.github.axinger.domain.PersonEntity;
import com.github.axinger.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/add")
    public Object add() {
        return personService.save(PersonEntity.builder().name("jim").build());
    }

    @GetMapping("/list")
    public Object data() {
        List<PersonEntity> list = personService.list();
        System.out.println("list = " + list);
        return list;
    }


}
