package com.axing.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.List;

@CacheConfig(cacheNames = {"st", "sc"})
public interface IServiceCacheable<T> extends IService<T> {

    // @Cacheable(key = "#id")
    // @Override
    // default T getById(Serializable id) {
    //     return IService.super.getById(id);
    // }
    //
    // @Override
    // default List<T> list() {
    //     return IService.super.list();
    // }


}
