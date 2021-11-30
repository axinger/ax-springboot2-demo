package com.ax.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Getter
//@Setter
@Data // set get
@ToString
@AllArgsConstructor // 有参
@NoArgsConstructor//无参
@SuperBuilder
@Component
@ConfigurationProperties(prefix = "t-person")
public class TPerson {
    private String name;
    private Integer age;
    private Dog dog;
    private List<Dog> dogs;
    private List<String> email;
    private List<Map<String, String>> score;

//    private List<String> score;
    private Map<String, String> scoreMap;
    private List<String> scoreList;

    public TPerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
