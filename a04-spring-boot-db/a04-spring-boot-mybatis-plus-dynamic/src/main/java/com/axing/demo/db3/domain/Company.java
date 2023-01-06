package com.axing.demo.db3.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName company
 */
@TableName(value ="company")
@Data
public class Company implements Serializable {
    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Object id;

    /**
     *
     */
    @TableField(value = "name")
    private String name;

    /**
     *
     */
    @TableField(value = "age")
    private Object age;

    /**
     *
     */
    @TableField(value = "address")
    private String address;

    /**
     *
     */
    @TableField(value = "salary")
    private Float salary;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
