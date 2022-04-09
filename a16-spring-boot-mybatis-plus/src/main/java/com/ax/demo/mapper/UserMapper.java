package com.ax.demo.mapper;

import com.ax.demo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xing
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-03-26 19:22:28
* @Entity com.ax.a16.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




