package com.axing.demo.model;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
	// set/get 省略
}
