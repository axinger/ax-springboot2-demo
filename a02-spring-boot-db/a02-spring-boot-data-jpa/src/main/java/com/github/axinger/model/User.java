package com.github.axinger.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity(name = "sys_user")
@Getter
@Setter
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
