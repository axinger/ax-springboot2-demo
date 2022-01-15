package dao;

import generator.TStorage;

public interface TStorageDao {
    int deleteByPrimaryKey(Long id);

    int insert(TStorage record);

    int insertSelective(TStorage record);

    TStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStorage record);

    int updateByPrimaryKey(TStorage record);
}