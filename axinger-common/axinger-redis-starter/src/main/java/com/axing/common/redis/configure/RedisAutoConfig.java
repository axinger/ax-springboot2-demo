package com.axing.common.redis.configure;

import com.axing.common.redis.bean.RedisProperties;
import com.axing.common.redis.service.RedisService;
import com.axing.common.redis.service.impl.RedisServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName RedisConfig.java
 * @description
 * @createTime 2022年01月18日 22:56:00
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    private final ObjectMapper objectMapper;

    private final RedisProperties redisProperties;

    /**
     * 自定义key规则
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 设置RedisTemplate规则
     *
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 序列号key value
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(this.jsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(this.jsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 设置CacheManager缓存规则
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // 配置序列化（解决乱码的问题）,
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(this.jsonRedisSerializer()))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
        return cacheManager;
    }


    /**
     * 还有一个 GenericJackson2JsonRedisSerializer
     *
     * @return
     */
    @Bean
    public RedisSerializer jsonRedisSerializer() {
        ObjectMapper objectMapper = this.objectMapper;
        // 将当前对象的数据类型也存入序列化的结果字符串中，以便反序列化
        if (redisProperties.isSavePackageName()) {
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        }

        // 解决jackson2无法反序列化LocalDateTime的问题
        //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;

        //GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        //return redisSerializer;

//        FastJson2JsonRedisSerializer redisSerializer = new FastJson2JsonRedisSerializer<>(Object.class);
//        return redisSerializer;
//        GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
//        return redisSerializer;

    }


    @Bean
    public RedisService redisService() {
        RedisService redisService = new RedisServiceImpl(this.redisTemplate());
        return redisService;
    }
}
