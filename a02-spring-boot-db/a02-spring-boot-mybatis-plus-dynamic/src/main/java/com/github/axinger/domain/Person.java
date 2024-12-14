package com.github.axinger.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_person
 */
@TableName(value = "sys_person")
@Data
public class Person implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;
    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;
}
