package com.axing.demo.mapper;

import com.axing.demo.domain.BookEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @author xing
 * @description 针对表【book】的数据库操作Mapper
 * @createDate 2023-01-18 10:00:35
 * @Entity com.axing.demo.domain.BookEntity
 */
public interface BookMapper extends BaseMapper<BookEntity> {

    IPage<BookEntity> customSqlSegment(@Param(Constants.WRAPPER) Wrapper<BookEntity> wrapper, Page<Object> page);


    IPage<BookEntity> sqlSegment(@Param(Constants.WRAPPER) Wrapper<BookEntity> wrapper, Page<Object> page);
}




