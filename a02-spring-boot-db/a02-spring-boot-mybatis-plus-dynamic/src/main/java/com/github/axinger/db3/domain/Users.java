package com.github.axinger.db3.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName users
 */
@TableName(value = "users")
@Data
public class Users implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Object id;
    /**
     *
     */
    @TableField(value = "name")
    private String name;
    /**
     *
     */
    @TableField(value = "password")
    private String password;
}
