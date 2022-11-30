package com.axing.demo.domain;

import com.axing.common.mybatis.entity.BaseEntity;
import com.axing.demo.enums.Gender;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @TableName t_student
 */
@TableName(value = "t_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Student extends BaseEntity {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private Gender gender;

    /**
     *
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}