package com.github.axinger.config;

import com.github.axinger.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Order(2)
@Configuration
@EnableBatchProcessing
public class BatchJobConfig3 {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    DataSource dataSource;

    @Autowired
    private DataSource businessDataSource;
//    @Bean
//    @StepScope
//    public JdbcCursorItemReader<User> itemReader(@Value("#{stepExecutionContext['start']}") Integer start,
//                                                 @Value("#{stepExecutionContext['end']}") Integer end) {
//        return new JdbcCursorItemReaderBuilder<User>()
//                .name("personItemReader")
//                .dataSource(dataSource)
//                .sql("SELECT * FROM user WHERE id BETWEEN ? AND ?")
//                .beanRowMapper(User.class)
//                .queryArguments(start, end)
//                .build();
//
//
//    }

    @Bean
    @StepScope
    public JdbcCursorItemReader<User> itemReader(@Value("#{stepExecutionContext['startIndex']}") Integer startIndex,
                                                 @Value("#{stepExecutionContext['endIndex']}") Integer endIndex) {
        // Fetch records by row number or offset range (ensure SQL supports it)
        String sql = "SELECT * FROM user LIMIT ? OFFSET ?";
        return new JdbcCursorItemReaderBuilder<User>()
                .name("personItemReader")
                .dataSource(businessDataSource)
                .sql(sql)
                .queryArguments(endIndex - startIndex + 1, startIndex)  // The LIMIT and OFFSET will ensure a unique range
                .beanRowMapper(User.class)
                .build();
    }



    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public ItemWriter<User> itemWriter() {
        return items -> {
            for (User item : items) {
                String sql = "INSERT INTO `user_2` (`id`, `name`, `email`) VALUES (?, ?, ?);";
                jdbcTemplate.update(sql, preparedStatement -> {
                    preparedStatement.setInt(1, item.getId());
                    preparedStatement.setString(2, item.getName());
                    preparedStatement.setString(3, item.getEmail());
                });

            }

        };
    }


    @Bean
    public Step subStep() {
        return stepBuilderFactory.get("subStep")
                .<User, User>chunk(100)
                .listener(readListener())
                .reader(itemReader(null,null))
                .writer(itemWriter())
                .build();
    }


    @Bean
    public Partitioner partitioner() {
        return gridSize -> {
//            Map<String, ExecutionContext> partitionMap = new HashMap<>();
//            int rangeSize = 1000;
//            for (int i = 0; i < gridSize; i++) {
//                ExecutionContext context = new ExecutionContext();
//                context.putInt("start", i * rangeSize);
//                context.putInt("end", (i + 1) * rangeSize - 1);
//                partitionMap.put("partition" + i, context);
//            }

            Map<String, ExecutionContext> partitionMap = new HashMap<>();

            // Get the total count of records in the user table
            int totalRecords = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
//            int totalRecords = 1000;

            int rangeSize = totalRecords / gridSize;  // Each partition should handle this number of records

            // For each partition, calculate the start and end index based on record count
            for (int i = 0; i < gridSize; i++) {
                ExecutionContext context = new ExecutionContext();
                int startIndex = i * rangeSize;
                int endIndex = (i + 1) * rangeSize - 1;
                if (i == gridSize - 1) {
                    endIndex = totalRecords - 1;  // Ensure the last partition handles any remaining records
                }
                context.putInt("startIndex", startIndex);
                context.putInt("endIndex", endIndex);
                partitionMap.put("partition" + i, context);
            }

            return partitionMap;
        };
    }


    @Bean
    public PartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(3);
        handler.setTaskExecutor(new SimpleAsyncTaskExecutor("多线程-"));
        handler.setStep(subStep());
        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            log.error("分区错误={}", e.getMessage());
            throw new RuntimeException(e);
        }
        return handler;
    }

    // 主步骤
    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep")
                .partitioner("masterP", partitioner())
                .partitionHandler(partitionHandler())
                .build();
    }


    @Bean("userJob")
    public Job job1() {
        return jobBuilderFactory.get("addUserJob2")
                .start(masterStep())
                .build();
    }

    @Bean
    public ItemReadListener<User> readListener() {

        return new ItemReadListener<>() {

            @Override
            public void beforeRead() {

            }

            @Override
            public void afterRead(User item) {
                if (item.getId() == 1) {
                    log.info("读取数据={}", item);
                }
            }

            @Override
            public void onReadError(Exception ex) {

            }
        };
    }
}
