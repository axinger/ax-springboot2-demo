package com.axing.model.dto;

import com.axing.entity.Person;
import lombok.Data;

@Data
public class PersonDto {
    private String name;
    private Integer age;
    public Person createPerson(){
        Person person = new Person();
        person.setName(this.name);
        person.setAge(this.age);
        return person;
    }
}
