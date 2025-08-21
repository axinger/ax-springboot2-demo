package com.github.axinger.dao;

import com.github.axinger.entity.SchoolStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolStudentDAO extends JpaRepository<SchoolStudent, Integer> {
    @Query("select username from sys_user")
    List<SchoolStudent> select();
}
