package com.axing.demo.jpa.repository;

import com.axing.demo.jpa.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xing
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
