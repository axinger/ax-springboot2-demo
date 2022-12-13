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


    @PostMapping
    public Person save(@RequestBody PersonDto personDto) { // 1
        return personRepository.save(personDto.createPerson());
    }

    @GetMapping
    private List<Person> all() { // 2
        return personRepository.findAll();
    }
}
