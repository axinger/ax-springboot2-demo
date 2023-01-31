package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName book
 */
@Data
@TableName(value = "book")
@Entity(name = "book")
public class BookEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增,有nextval('book_id_seq'::regclass),连续
    // @GeneratedValue(strategy = GenerationType.AUTO) // 自增但不连续
    @Column(name = "id")
    @Comment("主键")
    // 字段值为只读的，不允许插入和修改。通常用于主键和外键
    // @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    /**
     *
     */
    @Column(name = "book_author")
    @Comment("作者")
    @TableField(value = "book_author")
    private String bookAuthor;

    /**
     * 字段“text”，指定建表时SQL语句 如“varchar(50) NOT NULL”
     */
    @Column(name = "book_name", length = 50)
    @Comment("书名")
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 指定字段“totalAmount”交易金额的精度（长度）为10，小数点位数为2位，且值不能为null
     */
    @Column(name = "book_price", precision = 10, scale = 2)
    @Comment("价格")
    @TableField(value = "book_price")
    private BigDecimal bookPrice;

    /**
     * 分组求个数
     */
    @Transient
    @TableField(value = "count(*)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Long count;


    /**
     * 分组求和
     */
    @Transient
    @TableField(value = "sum(book_price)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private BigDecimal sumBookPrice;

    /**
     * 分组平均值
     */
    @Transient
    @TableField(value = "avg(book_price)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private BigDecimal avgBookPrice;

    /**
     * 分组最小值
     */
    @Transient
    @TableField(value = "min(book_price)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private BigDecimal minBookPrice;

    /**
     * 分组最大值
     */
    @Transient
    // @TableField(value = "max(book_price)", exist = false)// mbp这个不可以
    @TableField(value = "max(book_price)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private BigDecimal maxBookPrice;
}
