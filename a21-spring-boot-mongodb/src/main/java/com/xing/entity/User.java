package com.xing.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName User.java
 * @description TODO
 * @createTime 2022年07月12日 19:11:00
 */

@Data
public class User {

    private Integer age;
    private String name;
    private Date birthday;
}
