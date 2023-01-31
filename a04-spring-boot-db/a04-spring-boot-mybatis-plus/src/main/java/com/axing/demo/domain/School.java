package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

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
    @Column(name = "id")
    @Comment("主键")
    private Long id;
    /**
     *
     */
    @Column(name = "area")
    private Double area;
    /**
     *
     */
    @Column(name = "school_name", length = 50)
    @Comment("学校名称")
    private String schoolName;
    /**
     *
     */
    @Column(name = "zone")
    private String zone;


    @TableField(exist = false)
    @Transient
    private List<Room> roomList;
}
