package com.github.axinger.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface DynamicQueryMapper {

    @MapKey("id")
        // 使用${}引用参数作为Map Key字段
    List<Map<String, Object>> queryTableMap(@Param("sql") String sql);


    List<LinkedHashMap<String, Object>> queryTableLinkedHashMap(@Param("sql") String sql);

    @MapKey("id")
        // resultType="java.util.LinkedHashMap",但返回值是 Map 泛型
    List<Map<String, Object>> queryTableLinkedHashMap2(@Param("sql") String sql);

    List<TreeMap<String, Object>> queryTableTreeMap(@Param("sql") String sql);


    @MapKey("${mapKey}")
//    @SelectProvider(type = DynamicSqlProvider.class, method = "buildQuery") // 不能校验
    List<Map<String, Object>> queryTableMap2(@Param("sql") String sql, @Param("mapKey") String mapKey);


    List<LinkedHashMap<String, Object>> queryTableHasId(@Param("sql") String sql, @Param("params") Map<String, Object> map);


    // 方式1：完全动态SQL（需严格校验）
//    @Insert("${sql}")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRawSql(@Param("sql") String sql);

}
