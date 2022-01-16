package com.ax.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Student")
@Data
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private int id;
    private String name;
    private int age;
    private int sex;
    private String address;

}
