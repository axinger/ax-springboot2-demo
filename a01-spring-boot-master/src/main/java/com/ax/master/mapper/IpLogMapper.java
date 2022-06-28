package com.ax.master.mapper;

import com.ax.master.entity.IpLog;
import com.ax.master.query.IpLogQueryObject;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
/**
 *   eviction: 回收策略,FIFO先进先出,LRU最近最少使用
 *   flushInterval 刷新间隔,多长时间清空一次
 *         */
//@CacheNamespace(eviction = MybatisRedisCache.class)
/**
 * @author xing
 * @author axing
 */
//@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
// 如果接口上不增加这个注解,在调用tk.mybatis 或 mybatis-plus提供的原生接口时有可能导致缓存中脏数据的产生
//@CacheNamespaceRef(MybatisRedisCache.class)
public interface IpLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IpLog record);

    int insertSelective(IpLog record);

    int insertList(List<IpLog> list);

    IpLog selectByPrimaryKey(Long id);

    List<IpLog> selectIfParam(IpLog log);

    List<IpLog> selectChoose(IpLog log);

    List<IpLog> selectByIdList(List<Integer> ids);

    int updateByPrimaryKeySelective(IpLog record);

    int updateByPrimaryKey(IpLog record);

    /**
     * 分页查询 个数
     */
    int queryForCount(IpLogQueryObject queryObject);

    /**
     * 分页查询 内容
     */
    List<IpLog> query(IpLogQueryObject queryObject);

    List<IpLog> queryPage(IpLogQueryObject queryObject);

    List<IpLog> findByPageInfo();


    Page<IpLog> findByPage();


    int deleteByIdList(List<Long> ids);


    /**
     * 批量更新,多个sql,
     * 需要 url: jdbc:mysql://localhost:3306/10_p1?characterEncoding=utf-8&allowMultiQueries=true
     *
     * @param list
     * @return
     */
    int updateByList(List<IpLog> list);

    /**
     * 批量更新,拼接一条sql
     *
     * @param list
     * @return
     */
    int updateByListWhen(List<IpLog> list);


    int deleteById(int id);
}
