package com.github.axinger.mapper;

import com.github.axinger.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

/**
* @author xing
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-11-23 01:21:27
* @Entity com.github.axinger.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 500)
    @ResultType(User.class)
    void selectAll(ResultHandler<User> handler);
}




