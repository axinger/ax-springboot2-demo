package com.ax.shop.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xing   将redisTemplate注入
 * @Date: 2019/5/27 15:04
 * @Description:
 */
@Component
public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisTemplate redisTemplate) {
        MybatisRedisCache.setRedisConnectionFactory(redisTemplate);
    }
}
