package com.github.axinger.job;

import com.github.axinger.domain.User;
import com.github.axinger.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
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

    private final SqlSessionFactory sqlSessionFactory;
    private final Iterator<User> iterator;

    JobParameters jobParameters;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        // 获取 JobParameters
        this.jobParameters = stepExecution.getJobExecution().getJobParameters();
    }

    @Autowired
    public UserReader(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.iterator = readDataFromDatabase().iterator();
    }

    private List<User> readDataFromDatabase() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> users = new ArrayList<>();
            mapper.selectAll(context -> users.add(context.getResultObject()));

            return users;
        }
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
