package com.axing.demo.controller;

import com.axing.demo.config.MyTenantLineHandler;
import com.axing.demo.domain.PersonEntity;
import com.axing.demo.service.PersonService;
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

    @GetMapping("/list")
    public Object data() {
        MyTenantLineHandler.setTenantId("100");
        return personService.list();
    }


}
