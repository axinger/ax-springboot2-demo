package com.ax.a16.mapper;

import com.ax.a16.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xing
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-03-13 13:07:46
* @Entity com.ax.a16.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




