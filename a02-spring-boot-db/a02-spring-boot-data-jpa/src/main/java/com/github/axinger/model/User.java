package com.github.axinger.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    private String username;

    @Column(name = "password")
    private String password;
}
