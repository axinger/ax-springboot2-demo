package com.axing.demo.db3.mapper;

import com.axing.demo.db3.domain.Company;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author xing
 * @description 针对表【company】的数据库操作Mapper
 * @createDate 2023-01-06 09:54:36
 * @Entity com.axing.demo.db3.domain.Company
 */
@DS("db_ax_pgsql")
public interface CompanyMapper extends BaseMapper<Company> {

}




