package com.ax.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author axing
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ConfigurationProperties(prefix = "t-person")
@Component
public class Person {

    private Long id;
    private String name;
    private Integer age;
    private User user2;
    private List list1 = new ArrayList();

}
