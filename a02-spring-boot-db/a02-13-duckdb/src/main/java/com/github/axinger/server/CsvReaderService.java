package com.github.axinger.server;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CsvReaderService {

    private final JdbcTemplate jdbcTemplate;

    public CsvReaderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 读取CSV文件并返回结果
     */
    public List<Map<String, Object>> readCsv(String csvFilePath) {
        // 使用DuckDB的read_csv函数读取CSV文件
        String sql = "SELECT * FROM read_csv(?)";
        return jdbcTemplate.queryForList(sql, csvFilePath);
    }

    /**
     * 读取CSV文件并创建视图
     */
    public void createViewFromCsv(String csvFilePath, String viewName) {
        String sql = String.format("CREATE VIEW %s AS SELECT * FROM read_csv('%s')", viewName, csvFilePath);
        jdbcTemplate.execute(sql);
    }

    /**
     * 查询CSV数据（通过视图）
     */
    public List<Map<String, Object>> queryFromView(String viewName) {
        String sql = "SELECT * FROM " + viewName;
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 读取CSV文件并指定选项
     */
    public List<Map<String, Object>> readCsvWithOptions(String csvFilePath) {
        String sql = "SELECT * FROM read_csv(?, auto_detect=true, sample_size=-1)";
        return jdbcTemplate.queryForList(sql, csvFilePath);
    }
}
