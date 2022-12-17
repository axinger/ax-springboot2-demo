package com.axing.demo.jpa.repository;

import com.axing.demo.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author xing
 * 他们存在继承关系：
 * <p>
 * 　　PagingAndSortingRepository 继承 CrudRepository
 * 　　JpaRepository 继承 PagingAndSortingRepository
 * <p>
 * 也就是说， CrudRepository 提供基本的增删改查；PagingAndSortingRepository 提供分页和排序方法；JpaRepository 提供JPA需要的方法。
 */
// @Repository
// public interface UserRepository extends CrudRepository<User, Long> {
// }

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
