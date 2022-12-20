package com.axing.demo.db2.mapper;

import com.axing.demo.db2.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xing
 * @description 针对表【t_user_info(用户信息)】的数据库操作Mapper
 * @createDate 2022-07-27 10:43:02
 * @Entity com.ax.demo.db2.entity.UserInfo
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




