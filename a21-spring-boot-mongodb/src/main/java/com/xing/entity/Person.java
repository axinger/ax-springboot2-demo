package com.xing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Person.java
 * @description TODO
 * @createTime 2022年05月06日 21:11:00
 */
@Data
@Document("Person")
@ToString(callSuper = true)
public class Person extends BaseMongoEntity{


    @Indexed //普通索引
    private String name;

    private int age;

}
