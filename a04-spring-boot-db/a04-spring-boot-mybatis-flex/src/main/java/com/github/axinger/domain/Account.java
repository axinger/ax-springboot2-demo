package com.github.axinger.domain;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Table(value = "tb_account")
@Data
public class Account implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "user_name")
    private String userName;

    @Column(value = "age")
    private Integer age;

    @Column(value = "birthday")
    private LocalDateTime birthday;

}
