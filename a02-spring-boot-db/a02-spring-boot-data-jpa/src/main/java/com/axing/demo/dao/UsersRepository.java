package com.axing.demo.dao;

import com.axing.demo.model.Users;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository接口
 * 提供了方法名称命名查询方式
 * 提供了基于@Query注解查询与更新
 */
public interface UsersRepository extends Repository<Users, Integer> {

    List<Users> findAll();

}
