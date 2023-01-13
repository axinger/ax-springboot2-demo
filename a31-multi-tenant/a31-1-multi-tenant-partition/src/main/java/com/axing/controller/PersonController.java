package com.axing.controller;

import com.axing.entity.Person;
import com.axing.jpa.PersonRepository;
import com.axing.model.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * 1、通过设置在头信息中设置不同的TenantId，数据库中的tenant_id字段将自动存储头中的租户；
     * <p>
     * 2、通过设置在头信息中设置不同的TenantId，只能查询到该租户下的数据。
     */

    @PostMapping("/save")
    public Person save(@RequestBody PersonDto personDto) { // 1
        return personRepository.save(personDto.createPerson());
    }

    @GetMapping("/all")
    private List<Person> all() { // 2
        return personRepository.findAll();
    }
}
