package com.ax.master.jpa.repository;

import com.ax.master.jpa.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xing
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
