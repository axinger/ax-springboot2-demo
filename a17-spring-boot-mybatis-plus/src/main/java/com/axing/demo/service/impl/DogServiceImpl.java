package com.axing.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.axing.demo.domain.Dog;
import com.axing.demo.service.DogService;
import com.axing.demo.mapper.DogMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【Dog】的数据库操作Service实现
* @createDate 2022-10-03 15:20:16
*/
@Service
public class DogServiceImpl extends ServiceImpl<DogMapper, Dog>
implements DogService{

}
