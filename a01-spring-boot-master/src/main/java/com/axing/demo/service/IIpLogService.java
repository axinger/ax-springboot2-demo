package com.axing.demo.service;

import com.axing.demo.entity.IpLog;
import com.axing.demo.query.IpLogQueryObject;
import com.axing.demo.service.impl.RedisService;
import com.axing.demo.util.axUtil.AxPageResultEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


/**
 * @author axing
 */
public interface IIpLogService {

    /**
     * 查询
     *
     * @param queryObject
     * @return AxPageResultEntity
     */
    AxPageResultEntity query(IpLogQueryObject queryObject);

    @Cacheable(value = RedisService.REDIS_VALUE_IPLOG)
    IpLog getByKey(Long id);

    /**
     * 插入
     *
     * @param ipLog
     */
    void insert(IpLog ipLog);

    /**
     * 更新
     *
     * @param ipLog
     * @return
     */
    int updateByEntity(IpLog ipLog);

    /**
     * 查询
     *
     * @param queryObject
     * @return int
     */
    int queryForCount(IpLogQueryObject queryObject);

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return PageInfo
     */
    PageInfo<IpLog> findByPageInfo(int pageNum, int pageSize);

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return Page
     */
    Page<IpLog> findByPage(int pageNum, int pageSize);

    Object findAll();

    Object updateByListWhen(List<IpLog> list);

//    PageInfo queryPage(IpLogQueryObject queryObject, int pageNum, int pageSize);

}
