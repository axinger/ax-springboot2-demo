package com.axing.demo.JsonMixin;

import com.axing.demo.model.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(PersonDTO.class) // 1
public abstract class PersonDTOMixin {
    @JsonProperty("fullName") // 2
    String name; // 3
}
