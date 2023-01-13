package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName book
 */
@TableName(value = "book")
@Data
public class BookEntity implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "book_author")
    private String bookAuthor;

    /**
     *
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     *
     */
    @TableField(value = "book_price")
    private Double bookPrice;

    /**
     *
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     *
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;


    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     *每种策略的作用：
     * 值	描述
     * IGNORED	忽略空值判断，实体对象的字段是什么值就用什么值更新，支持null值更新操作
     * NOT_NULL	进行非NULL判断，也是默认策略，相当于age!=null
     * NOT_EMPTY	进行非空判断，主要是针对字符串类型，相当于name != null and name != ‘’
     * NEVER	从不更新，不管字段是否有值，都不进行更新
     * DEFAULT	追随全局配置
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE,updateStrategy=FieldStrategy.IGNORED)
    // @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE,update="now()")
    // @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    // @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime; // 这个用date

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
