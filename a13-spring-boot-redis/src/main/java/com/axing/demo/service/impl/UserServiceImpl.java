package com.axing.demo.service.impl;


import com.axing.demo.model.Person;
import com.axing.demo.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
@CacheConfig(cacheNames = "demo13:user")
public class UserServiceImpl implements UserService {

    @Override
//    @Cacheable(value = "user", key = "#userId")
    @Cacheable(key = "#id")
    public Person findUser(Integer id) {
        System.out.println("UserServiceImpl.findUser");
        return Person.builder().id(id).name("jim").age(21).build();
    }


    @Override
    @CachePut(key = "#person.id")
    public Person updateUser(Person person) {
        System.out.println("UserServiceImpl.updateUser");
        return person;
    }


    @Override
//    @CacheEvict(value = "user", key = "#userId") // CacheEvict 删除
    @CacheEvict(key = "#id")
    public void deleteUser(Integer id) {
        System.out.println("UserServiceImpl.deleteUser");
    }
}

