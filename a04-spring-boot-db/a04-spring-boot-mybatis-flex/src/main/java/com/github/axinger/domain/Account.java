package com.github.axinger.domain;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "tb_account")
@Accessors(chain = true)
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Data(staticConstructor = "create") // 会私有无参构造方法
public class Account extends Model<Account>  implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "user_name")
    private String userName;

    @Column(value = "age")
    private Integer age;

    @Column(value = "birthday")
    private LocalDateTime birthday;

    //最大年龄
    @Column(ignore = true)
    private int maxAge;
    @Column(ignore = true)
    private int mimAge;

    //平均年龄
    @Column(ignore = true)
    private int avgAge;

    // 分组后多个id逗号分割
    @Column(ignore = true)
    private String groupAfterId;
    @Column(ignore = true)
    private String groupAfterName;


    public static Account create(){
        return new Account();
    }
}
