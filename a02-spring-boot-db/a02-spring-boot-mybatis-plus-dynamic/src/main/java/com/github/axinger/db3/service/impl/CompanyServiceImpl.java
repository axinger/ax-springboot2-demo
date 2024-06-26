package com.github.axinger.db3.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.axinger.db3.domain.Company;
import com.github.axinger.db3.mapper.CompanyMapper;
import com.github.axinger.db3.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @author xing
 * @description 针对表【company】的数据库操作Service实现
 * @createDate 2023-01-06 09:54:36
 */
@Service
@DS("db_ax_pgsql")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
        implements CompanyService {

}




