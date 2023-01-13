package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName book
 */
@TableName(value = "book")
@Data
public class BookEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id")
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
}
