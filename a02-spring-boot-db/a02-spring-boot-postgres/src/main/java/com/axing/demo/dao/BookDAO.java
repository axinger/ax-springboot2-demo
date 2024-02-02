package com.axing.demo.dao;

import com.axing.demo.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDAO extends JpaRepository<BookEntity, Long> {
}
