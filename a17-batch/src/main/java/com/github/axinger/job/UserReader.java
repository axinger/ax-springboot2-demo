package com.github.axinger.job;

import com.github.axinger.domain.User;
import com.github.axinger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class UserReader implements ItemReader<User> {

    //    private final SqlSessionFactory sqlSessionFactory;
    private final Iterator<User> iterator;
    private final UserService userService;
    JobParameters jobParameters;

    @Autowired
    public UserReader(SqlSessionFactory sqlSessionFactory, UserService userService) {
//        this.sqlSessionFactory = sqlSessionFactory;
        this.userService = userService;
        this.iterator = readDataFromDatabase().iterator();
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        // 获取 JobParameters
        this.jobParameters = stepExecution.getJobExecution().getJobParameters();
    }
//        @Resource
//    private DynamicRoutingDataSource dynamicRoutingDataSource;

    private List<User> readDataFromDatabase() {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            UserMapper mapper = session.getMapper(UserMapper.class);
//        DynamicDataSourceContextHolder.push("db_ax_sub");
        List<User> users = new ArrayList<>();
        userService.selectAll(context -> users.add(context.getResultObject()));
//            mapper.selectAll(context -> users.add(context.getResultObject()));
//        DynamicDataSourceContextHolder.clear();
        return users;
//        }
    }

    @Override
    public User read() throws Exception, ParseException, NonTransientResourceException {
        if (iterator.hasNext()) {


            Long filename = jobParameters.getLong("time");
            log.info("获取参数: {}", filename);
            return iterator.next();
        }

        log.error("数据库已经读取结束=================");
        return null;
    }
}
