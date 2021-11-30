package com.ax.shop.jpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年12月16日 00:53:00
 */

@Data

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user_info")

public class User {

    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "user_type")
    String userType;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;


}
