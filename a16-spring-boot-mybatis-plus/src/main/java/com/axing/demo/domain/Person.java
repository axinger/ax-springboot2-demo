package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName t_person
 */
@TableName(value = "t_person")
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Integer age2;


    /**
     * 不会序列化,所以也不会映射表,业务需求不一样
     */
    private transient String name2 = "name2";

    /**
     * 不会映射表,但是可以序列化,业务需求不一样
     */
    @TableField(exist = false)
    private String name3 = "name3";


    @TableField(exist = false)
    private String name4 = "name4";

    // import java.beans.Transient; 下,只能在get方法上使用,有效
    @java.beans.Transient
    public String getName4() {
        return name4;
    }


    // 这个mb无效
//    @org.springframework.data.annotation.Transient
//    private  String name45= "name45";

}
