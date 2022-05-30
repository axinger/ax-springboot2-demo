package com.xing.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Dog.java
 * @description TODO
 * @createTime 2022年05月06日 22:37:00
 */
@Data
@Document("Dog")
@ToString(callSuper = true)
public class Dog extends BaseMongoEntity {


    @Indexed //普通索引
    private String name;
    private String address;
    private int age;

}
