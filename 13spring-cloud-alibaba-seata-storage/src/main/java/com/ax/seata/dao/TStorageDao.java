package com.ax.seata.dao;

import com.ax.seata.domain.TStorage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TStorageDao {

    int deleteByPrimaryKey(Long id);

    int insert(TStorage record);

    int insertSelective(TStorage record);

    TStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStorage record);

    int updateByPrimaryKey(TStorage record);

    int decrease(@Param("productId") Long productId, @Param("count") Integer count);
}