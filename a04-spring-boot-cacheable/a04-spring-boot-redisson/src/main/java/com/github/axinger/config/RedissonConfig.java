package com.github.axinger.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

//    @Bean
//    public RedissonClient redissonClient(){
//        Config conf = new Config();
//        //单节点模式
//        SingleServerConfig singleServerConfig = conf.useSingleServer();
//        //设置连接地址：redis://127.0.0.1:6379
//        singleServerConfig.setAddress("redis://127.0.0.1:6379");
//        //设置连接密码
//        //使用json序列化方式
//        Codec codec = new JsonJacksonCodec();
//        conf.setCodec(codec);
//        RedissonClient redissonClient = Redisson.create(conf);
//        return redissonClient;
//    }
}
