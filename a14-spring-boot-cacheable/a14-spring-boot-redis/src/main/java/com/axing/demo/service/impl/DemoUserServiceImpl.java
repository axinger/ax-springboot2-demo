package com.axing.demo.service.impl;


import com.axing.demo.model.User;
import com.axing.demo.service.DemoUserService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * cacheNames：缓存位置的一段名称，不能为空，和value一个含义。
 * <p>
 * value：缓存位置的一段名称，不能为空，和cacheNames一个含义。
 * <p>
 * key：缓存的key，默认为空，表示使用方法的参数类型及参数值作为key，支持SpEL。
 * <p>
 * condition：触发条件，满足条件就加入缓存，默认为空，表示全部都加入缓存，支持SpEL。
 * <p>
 * allEntries：true表示清除value中的全部缓存，默认为false。
 */
@Service
@CacheConfig(cacheNames = "demo14::user")
public class DemoUserServiceImpl implements DemoUserService {

    @Override
//    @Cacheable(value = "user", key = "#userId")
    @Cacheable(key = "#id")
    public User findUser(Integer id) {
        System.out.println("UserServiceImpl.findUser");
        User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        return User.builder().id(id).name("jim").age(21).books(List.of(book)).build();
    }


    @Override
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        System.out.println("UserServiceImpl.updateUser");
        return user;
    }


    @Override
//    @CacheEvict(value = "user", key = "#userId") // CacheEvict 删除
    @CacheEvict(key = "#id")
    public void deleteUser(Integer id) {
        System.out.println("UserServiceImpl.deleteUser");
    }


    /**
     * @Description: @Caching是缓存的结合体，可以同时设置多了缓存的信息设置。
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#id")
            },
            put = {                  // 更新缓存可以通过id，email或者lastName进行key值查找。
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.name"),
                    @CachePut(value = "emp", key = "#result.date"),
            }
    )
    @Override
    public User getLastName(Integer id) {
        System.out.println("要查询的用户名为：" + id);
        User user = User.builder()
                .id(id)
                .name("jim")
                .date(new Date())
                .build();
        return user;
    }
}

