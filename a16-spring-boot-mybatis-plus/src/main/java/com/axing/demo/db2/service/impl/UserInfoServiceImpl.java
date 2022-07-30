package com.axing.demo.db2.service.impl;

import com.axing.demo.db2.entity.UserInfo;
import com.axing.demo.db2.mapper.UserInfoMapper;
import com.axing.demo.db2.service.UserInfoService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_user_info(用户信息)】的数据库操作Service实现
 * @createDate 2022-07-27 10:43:02
 */
@Service
@DS("db_ax_demo")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

}




