package com.axing.demo.JsonMixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonMixin;

@JsonMixin(Person.class) //1
public abstract class FullNameMixin {
    @JsonProperty("fullName") //2
    String name; //3
}