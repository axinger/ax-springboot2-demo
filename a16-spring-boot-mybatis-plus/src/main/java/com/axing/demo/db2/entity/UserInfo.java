package com.axing.demo.db2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @TableName t_user_info
 */
@TableName(value = "t_user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 密码MD5加密的
     */
    private String password;

    /**
     * 用户类型 0 1 2
     */
    private Integer userType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
