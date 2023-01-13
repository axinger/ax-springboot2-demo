package com.axing.demo.service.impl;

import com.axing.demo.domain.BookEntity;
import com.axing.demo.mapper.BookMapper;
import com.axing.demo.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【book】的数据库操作Service实现
 * @createDate 2023-01-12 14:56:04
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity>
        implements BookService {

}




