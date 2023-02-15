package com.axing.demo.mapper;

import com.axing.demo.domain.BookEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

/**
 * @author xing
 * @description 针对表【book】的数据库操作Mapper
 * @createDate 2023-01-18 10:00:35
 * @Entity com.axing.demo.domain.BookEntity
 */
@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {

    IPage<BookEntity> customSqlSegment(@Param(Constants.WRAPPER) Wrapper<BookEntity> wrapper, Page<Object> page);


    IPage<BookEntity> sqlSegment(@Param(Constants.WRAPPER) Wrapper<BookEntity> wrapper, Page<Object> page);

    @Select("select * from book")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 500)
    @ResultType(BookEntity.class)
    void streamSelect(ResultHandler<BookEntity> handler);
}




