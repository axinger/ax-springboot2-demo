//package com.ax.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName RedissonConfig.java
// * @Description TODO
// * @createTime 2022年02月22日 19:18:00
// */
//
//@Configuration
//public class RedissonConfig {
//
//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson() {
//
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://47.101.156.93:6379")
//
//        ;
//
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//
//    }
//}
