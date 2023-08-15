package com.axing;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PostMapping("/save")
    public Person save(@RequestBody PersonDto personDto) {
        return personRepository.save(personDto.createPerson());
    }

    @GetMapping("/all")
    private List<Person> all() {
        return personRepository.findAll();
    }
}
