package com.axing.demo.model;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
}
