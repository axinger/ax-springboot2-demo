//package com.ax.mall.config;
//
//import com.ax.mall.listener.redis.OrderMessageListener;
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.serializer.*;
//
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName RedisCacheConfig.java
// * @Description TODO
// * @createTime 2022年01月18日 22:56:00
// */
//
////@EnableCaching
////@Configuration
//public class RedisCacheConfig extends CachingConfigurerSupport {
//
//    public RedisSerializer<String> redisSerializer() {
//        return new StringRedisSerializer();
//    }
//
//    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        return jackson2JsonRedisSerializer;
//    }
//
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = jackson2JsonRedisSerializer();
////        RedisSerializer<String> redisSerializer = redisSerializer();
////
////        template.setConnectionFactory(factory);
////        //key序列化方式
////        template.setKeySerializer(redisSerializer);
////        //value序列化
////        template.setValueSerializer(jackson2JsonRedisSerializer);
////        //value hashmap序列化
////        template.setHashValueSerializer(jackson2JsonRedisSerializer);
////        return template;
////    }
////
////    @Autowired
////    private LettuceConnectionFactory connectionFactory;
//
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
////        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
////        initDomainRedisTemplate(redisTemplate, factory);
////        System.out.println("redisTemplate 初始化 RedisConfig ==============");
////        return redisTemplate;
////    }
//
//
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory factory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        //设置key序列化方式
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//        //设置value序列化方式
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//
//    /**
//     * 设置数据存入 redis 的序列化方式
//     *
//     * @param template
//     * @param factory
//     */
//    private void initDomainRedisTemplate(RedisTemplate<String, Object> template, RedisConnectionFactory factory) {
//        // 定义 key 的序列化方式为 string
//        // 需要注意这里Key使用了 StringRedisSerializer，那么Key只能是String类型的，不能为Long，Integer，否则会报错抛异常。
//        StringRedisSerializer redisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(redisSerializer);
//        // 定义 value 的序列化方式为 json
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//
//        //hash结构的key和value序列化方式
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.setEnableTransactionSupport(true);
//        template.setConnectionFactory(factory);
//
//        //解决日期序列化问题
//        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
//        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
//
//    }
//
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = jackson2JsonRedisSerializer();
//        RedisSerializer<String> redisSerializer = redisSerializer();
//
//        // 配置序列化（解决乱码的问题）,过期时间600秒
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(600))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
//                .disableCachingNullValues();
//        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .build();
//        return cacheManager;
//    }
//
//
//    private RedisCacheConfiguration redisCacheConfiguration(long seconds) {
//
//        RedisSerializationContext.SerializationPair serializationPair =
//                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer());
//
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(seconds))
//                .serializeValuesWith(serializationPair);
//        return configuration;
//
//    }
//
//    private Map<String, RedisCacheConfiguration> initialCacheConfigurations() {
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//
//        //设置@Cacheable(value  过期时间
////        redisCacheConfigurationMap.put(RedisService.REDIS_VALUE_IPLOG,redisCacheConfiguration(5));
//
//        return redisCacheConfigurationMap;
//    }
//
//    /**
//     * 初始化监听器
//     */
//    @Bean
//    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
//                                                                OrderMessageListener orderMessageListener) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        //container.addMessageListener(orderMessageListenerAdapter, new PatternTopic("__keyevent@0__:expired"));//这个是key失效频道
////        container.addMessageListener(orderMessageListener, new PatternTopic(RedisService.REDIS_VALUE_IPLOG));
//        return container;
//    }
//
//    @Bean
//    OrderMessageListener orderMessageListenerAdapter() {
//        return new OrderMessageListener();
//    }
//
//
//}
//
