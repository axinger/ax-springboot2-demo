package com.github.axinger.dao;

import com.github.axinger.entity.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSubjectDAO extends JpaRepository<SchoolSubject, Integer> {
}
