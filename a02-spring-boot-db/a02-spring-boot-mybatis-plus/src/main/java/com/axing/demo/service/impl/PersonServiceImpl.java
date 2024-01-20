package com.axing.demo.service.impl;

import com.axing.demo.domain.PersonEntity;
import com.axing.demo.mapper.PersonMapper;
import com.axing.demo.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author cepai
 * @description 针对表【person】的数据库操作Service实现
 * @createDate 2023-10-31 11:20:21
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonEntity>
        implements PersonService {

}




