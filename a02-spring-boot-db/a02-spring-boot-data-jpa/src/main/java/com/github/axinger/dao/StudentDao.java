package com.github.axinger.dao;

import com.github.axinger.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer> {
    @Query("select username from sys_user")
    List<Student> select();
}
