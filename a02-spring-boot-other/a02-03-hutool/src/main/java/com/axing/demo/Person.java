package com.axing.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName com.ax.demo.Person.java
 * @description TODO
 * @createTime 2022年06月12日 03:01:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private Boolean big;

    private Integer age;
}
