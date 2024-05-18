package com.github.axinger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ConfigurationProperties(prefix = "axing.person")
//@Component // 也可以使用 @EnableConfigurationProperties
public class Person {

    private Long id;
    private String name;
    private Integer age;
    private List list1 = new ArrayList();

}
