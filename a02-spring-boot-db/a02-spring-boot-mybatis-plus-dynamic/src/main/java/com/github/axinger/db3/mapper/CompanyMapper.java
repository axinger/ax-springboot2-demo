package com.github.axinger.db3.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.axinger.db3.domain.Company;

/**
 * @author xing
 * @description 针对表【company】的数据库操作Mapper
 * @createDate 2023-01-06 09:54:36
 * @Entity com.github.axinger.db3.domain.Company
 */
@DS("db_ax_pgsql")
public interface CompanyMapper extends BaseMapper<Company> {

}




