package com.github.axinger.config;

import com.github.axinger.job.UserReader;
import com.github.axinger.job.UserProcessor;
import com.github.axinger.job.UserWriter;
import com.github.axinger.domain.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private UserReader userReader;

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private UserWriter userWriter;
//
//    @Autowired
//    private DynamicRoutingDataSource dynamicRoutingDataSource;

//    @Bean
//    public JobRepository getJobRepository()  {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(batchDataSource);
//        factory.setTransactionManager(getTransactionManager());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }

//    @Bean
//    public BatchConfigurer batchConfigurer() {
//        return new CustomBatchConfigurer(dynamicRoutingDataSource);
//    }

    @Bean(name = "userJob")
    public Job job1() {
        return jobBuilderFactory.get("addUserJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1UserJob")
                .<User, User>chunk(1000)
                .reader(userReader)
                .processor(userProcessor)
                .writer(userWriter)
                .build();
    }
}
