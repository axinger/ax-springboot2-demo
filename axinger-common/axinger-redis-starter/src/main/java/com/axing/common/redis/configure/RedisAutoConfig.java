package com.axing.common.redis.configure;

import cn.hutool.json.JSON;
import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;
import com.axing.common.redis.service.RedisService;
import com.axing.common.redis.service.impl.RedisServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * redis配置
 *
 * @author axing
 */
@Configuration
@EnableCaching
public class RedisAutoConfig extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory factory;

    @Bean
    @ConditionalOnMissingBean(RedisService.class)
    public RedisService redisService() {
        return new RedisServiceImpl();
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
//
//        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
//
//        // 使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(serializer);
//
//        // Hash的key也采用StringRedisSerializer的序列化方式
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(serializer);
//
//        template.afterPropertiesSet();

//        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        // 设置键（key）的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置值（value）的序列化方式
        redisTemplate.setHashValueSerializer(redisSerializer());
        redisTemplate.setValueSerializer(redisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Override
    public CacheManager cacheManager() {


//        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        // 配置序列化（解决乱码的问题）,过期时间600秒
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

//    public RedisSerializer<String> redisSerializer() {
//        return new StringRedisSerializer();
//    }


    @Bean
    public RedisSerializer redisSerializer() {
        GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
        return redisSerializer;
    }
}
