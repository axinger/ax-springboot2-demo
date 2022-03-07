package com.ax.a16.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.a16.domain.User;
import com.ax.a16.service.UserService;
import com.ax.a16.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-03-13 13:07:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




