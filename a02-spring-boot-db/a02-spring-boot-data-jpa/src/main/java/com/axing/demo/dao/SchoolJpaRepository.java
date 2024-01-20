package com.axing.demo.dao;

import com.axing.demo.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolJpaRepository extends JpaRepository<School, Integer> {
}
