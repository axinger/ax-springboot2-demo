package com.github.axinger.model.dto;

import com.github.axinger.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String name;

    //    @EnumValidator(value = Gender.class)
    private Gender gender;
}
