package com.axing.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_price")
    private Double bookPrice;

    @Column(name = "book_author")
    private String bookAuthor;
}
