package xyz.ax.demo.service;

import xyz.ax.demo.entity.TStorage;

public interface TStorageService {
    int deleteByPrimaryKey(Long id);

    int insert(TStorage record);

    int insertSelective(TStorage record);

    TStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStorage record);

    int updateByPrimaryKey(TStorage record);
}