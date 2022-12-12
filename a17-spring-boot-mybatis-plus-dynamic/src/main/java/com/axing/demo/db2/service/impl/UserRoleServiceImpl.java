package com.axing.demo.db2.service.impl;

import com.axing.demo.db2.entity.UserRole;
import com.axing.demo.db2.mapper.UserRoleMapper;
import com.axing.demo.db2.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【t_user_role(角色信息)】的数据库操作Service实现
 * @createDate 2022-07-27 10:48:23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements UserRoleService {

}




