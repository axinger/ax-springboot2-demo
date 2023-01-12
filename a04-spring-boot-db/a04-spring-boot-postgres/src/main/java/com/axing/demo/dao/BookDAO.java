package com.axing.demo.dao;

import com.axing.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDAO extends JpaRepository<Book, Long> {
}
