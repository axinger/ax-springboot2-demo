package com.ax.model;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String name;
    private Integer age;
}
