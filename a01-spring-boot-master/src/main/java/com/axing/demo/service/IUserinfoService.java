package com.axing.demo.service;

import com.axing.demo.entity.Userinfo;
import com.axing.demo.service.impl.RedisService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author axing
 */
public interface IUserinfoService {

    @Cacheable(value = RedisService.REDIS_VALUE_USERINFO)
    Userinfo getUserinfoWithKey(long id);

    Userinfo selectUserWithRelo(long id);

    //        @Cacheable(value = RedisService.REDIS_VALUE_USERINFO,sync=true)
    @Cacheable(value = RedisService.REDIS_VALUE_USERINFO, sync = true)
    List<Userinfo> getAllUserinfo();

    List<Userinfo> getAllUserinfoWithRedis();


    int insert(Userinfo record);

}
