package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @TableName t_school
 */
@Data
@TableName(value = "t_school")
@Entity(name = "t_school")
public class School implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    // mybatis注解
    @TableId(type = IdType.AUTO)

    // jpa注解
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint comment '主键ID'")
    private Long id;
    /**
     *
     */
    @Column(name = "area", columnDefinition = "double comment '区域'")
    private Double area;
    /**
     *
     */
    @Column(name = "school_name", columnDefinition = "varchar(100) comment '学校名称'")
    private String schoolName;
    /**
     *
     */
    @Column(name = "zone", columnDefinition = "varchar(100) comment '区'")
    private String zone;


    @TableField(exist = false)
    @Transient
    private List<Room> roomList;
}
