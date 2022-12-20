package com.axing.common.redis.configure;

import com.axing.common.json.config.ObjectMapperConfig;
import com.axing.common.redis.bean.RedisProperties;
import com.axing.common.redis.service.RedisService;
import com.axing.common.redis.service.impl.RedisServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
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
@AutoConfiguration
@EnableCaching
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisProperties.class)
@AutoConfigureAfter(ObjectMapperConfig.class)
public class RedisAutoConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    private final ObjectMapperConfig objectMapperConfig;

    private final RedisProperties redisProperties;

    @Bean
    public RedisService redisService(RedisConnectionFactory factory) {
        return new RedisServiceImpl(this.redisTemplate(factory));
    }

    /**
     * 自定义key规则
     *
     * @return KeyGenerator
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
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 序列号key value
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(this.redisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(this.redisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 设置CacheManager缓存规则
     *
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        // 配置序列化（解决乱码的问题）,
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(this.redisSerializer()))
                .disableCachingNullValues();
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
    }


    /**
     * 还有一个 GenericJackson2JsonRedisSerializer
     *
     * @return RedisSerializer
     */
    @Bean
    @ConditionalOnMissingBean(RedisSerializer.class)
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();

        // // 将当前对象的数据类型也存入序列化的结果字符串中，以便反序列化
        if (redisProperties.isSavePackageName()) {
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        }

        // 解决jackson2无法反序列化LocalDateTime的问题
        // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        // return  new GenericJackson2JsonRedisSerializer(this.objectMapper());
        // return new Jackson2JsonRedisSerializer<>(Object.class);

        // GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        // return redisSerializer;

//        FastJson2JsonRedisSerializer redisSerializer = new FastJson2JsonRedisSerializer<>(Object.class);
//        return redisSerializer;
//        GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
//        return redisSerializer;

    }

}
