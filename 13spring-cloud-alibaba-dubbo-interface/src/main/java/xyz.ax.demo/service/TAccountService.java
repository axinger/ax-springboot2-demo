package xyz.ax.demo.service;

import xyz.ax.demo.entity.TAccount;

public interface TAccountService {
    int deleteByPrimaryKey(Long id);

    int insert(TAccount record);

    int insertSelective(TAccount record);

    TAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccount record);

    int updateByPrimaryKey(TAccount record);
}