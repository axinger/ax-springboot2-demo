package com.ax.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.demo.domain.User;
import com.ax.demo.service.UserService;
import com.ax.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-03-26 19:22:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




