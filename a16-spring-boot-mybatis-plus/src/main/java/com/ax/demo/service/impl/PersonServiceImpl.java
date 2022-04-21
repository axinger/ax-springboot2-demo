package com.ax.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ax.demo.entity.Person;
import com.ax.demo.service.PersonService;
import com.ax.demo.mapper.PersonMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_person】的数据库操作Service实现
* @createDate 2022-04-24 09:38:32
*/
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person>
    implements PersonService{

}




