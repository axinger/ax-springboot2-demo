package com.ax.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

//@Getter
//@Setter
@Data // set get
@ToString
@AllArgsConstructor // 有参
@NoArgsConstructor//无参
@SuperBuilder
public class Person {
    private String name;
    private Integer age;
    private Dog dog;
    private List<Dog> dogs;
    private List<String> email;
    private List<String> score;
    private Map<String, String> scoreMap;
    private List<String> scoreList;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
