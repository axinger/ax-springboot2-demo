package com.github.axinger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.axinger.domain.User;
import com.github.axinger.service.UserService;
import com.github.axinger.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-23 01:21:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




