package com.axing.demo.redis.mapper;

import com.axing.demo.redis.domain.PersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.CacheConfig;

/**
 * @author cepai
 * @description 针对表【person】的数据库操作Mapper
 * @createDate 2023-12-19 14:55:09
 * @Entity com.axing.demo.redis.domain.PersonEntity
 */
//@CacheNamespace()
@CacheNamespace
public interface PersonMapper extends BaseMapper<PersonEntity> {

}




