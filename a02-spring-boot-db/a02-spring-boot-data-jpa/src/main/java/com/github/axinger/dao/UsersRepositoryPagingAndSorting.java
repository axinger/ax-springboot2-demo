package com.github.axinger.dao;

import com.github.axinger.model.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 3 PagingAndSortingRepository接口
 * 该接口继承了CrudRepository接口
 * <p>
 * 该接口提供了分页与排序的操作， 也就是该接口不用自己定义增删改查方法和分页排序方法
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users, Integer> {
}
