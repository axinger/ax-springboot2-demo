package com.axing.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Date date;
    private List<User.Book> books;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Book implements Serializable {
        private Integer id;
        private String name;
    }
}

