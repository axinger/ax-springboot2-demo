package com.ax.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName t_person
 */
@TableName(value ="t_person")
@Data
public class Person implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private Object gender;

    /**
     *
     */
    private Date data;

    /**
     *
     */
    private Date time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}