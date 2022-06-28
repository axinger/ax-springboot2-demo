package com.ax.master.service;

import com.ax.master.entity.Userinfo;
import com.ax.master.service.impl.RedisService;
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
