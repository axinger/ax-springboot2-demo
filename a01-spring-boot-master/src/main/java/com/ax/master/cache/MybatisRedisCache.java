//package com.ax.master.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.cache.Cache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * @author xing
// */
//@Slf4j
////@Order(0)
////@Component
//public class MybatisRedisCache implements Cache {
//
//    private static RedisTemplate redisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate2;
//
//    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    //private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
//    private String id;
//
//    public MybatisRedisCache(String id) {
//        if (id == null) {
//            throw new IllegalArgumentException("Cache instances require an ID");
//        }
//        this.id = id;
//    }
//
//    public static void setRedisConnectionFactory(RedisTemplate redisTemplate) {
//        MybatisRedisCache.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public void putObject(Object key, Object value) {
//
//        log.info("redisTemplate 1= " + redisTemplate);
//        log.info("redisTemplate2 ================== " + redisTemplate2);
//
//        log.info("[结果放入到缓存中: " + key + ", value = " + value + ", getId = " + getId());
//
//
//        redisTemplate.boundHashOps(getId()).put(key, value);
//        log.info("[结果放入到缓存中: " + key + "=" + value + " ]");
//
//    }
//
//    @Override
//    public Object getObject(Object key) {
////        log.info("[从缓存中获取了: key = " + key + ", getId = " + getId());
//        log.info("redisTemplate 2= " + redisTemplate);
//        System.out.println("redisTemplate = " + redisTemplate);
//        Object value = redisTemplate.boundHashOps(getId()).get(key);
//        log.info("[从缓存中获取了: " + key + ",value =" + value + " ]");
//        return value;
//    }
//
//    @Override
//    public Object removeObject(Object key) {
//        Object value = redisTemplate.boundHashOps(getId()).delete(key);
//        log.info("[从缓存删除了: " + key + "=" + value + " ]");
//        return value;
//    }
//
//    @Override
//    public void clear() {
//        redisTemplate.delete(getId());
//        log.info("清空缓存!!!");
//    }
//
//    @Override
//    public int getSize() {
//        Long size = redisTemplate.boundHashOps(getId()).size();
//        return size == null ? 0 : size.intValue();
//    }
//
//    @Override
//    public ReadWriteLock getReadWriteLock() {
//        return readWriteLock;
//    }
//}
