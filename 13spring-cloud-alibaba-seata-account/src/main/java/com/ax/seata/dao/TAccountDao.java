package com.ax.seata.dao;

import com.ax.seata.domain.TAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface TAccountDao {
    int deleteByPrimaryKey(Long id);

    int insert(TAccount record);

    int insertSelective(TAccount record);

    TAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccount record);

    int updateByPrimaryKey(TAccount record);

    int decrease(@Param("userId") Long userId,@Param("money") BigDecimal money);

}