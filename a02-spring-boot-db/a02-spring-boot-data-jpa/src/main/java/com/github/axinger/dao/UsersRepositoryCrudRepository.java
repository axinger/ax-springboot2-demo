package com.github.axinger.dao;

import com.github.axinger.model.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口继承了Repository接口
 * <p>
 * CrudRepository提供了基本的增删改查，不再需要我们自定义。
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {
}
