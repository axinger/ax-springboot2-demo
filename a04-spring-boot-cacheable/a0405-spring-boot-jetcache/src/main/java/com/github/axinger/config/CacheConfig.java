package com.github.axinger.config;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.support.EncoderParser;
import com.alicp.jetcache.template.QuickConfig;
import com.github.axinger.config.JsonEncoderParser;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Configuration
public class CacheConfig {

//    @Bean
//    public Caffeine caffeine() {
//        return Caffeine.newBuilder()
//                .initialCapacity(200)
//                .maximumSize(2000)
//                .weakKeys()
//                .recordStats();
//    }

    @Bean
    public EncoderParser encoderParser(){
        return new JsonEncoderParser();	// 支持json序列化
    }

//    @Autowired
//    private CacheManager cacheManager;
//    private Cache<String, Object> userCache;
//
//    @PostConstruct
//    public void init() {
//        QuickConfig qc = QuickConfig.newBuilder("userCache")
//                .expire(Duration.ofSeconds(100))
//                .cacheType(CacheType.BOTH) // two level cache
//                .localLimit(50)
//                .syncLocal(true) // invalidate local cache in all jvm process after update
//                .build();
//        userCache = cacheManager.getOrCreateCache(qc);
//    }

}
