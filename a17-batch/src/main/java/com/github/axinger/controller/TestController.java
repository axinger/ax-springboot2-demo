package com.github.axinger.controller;

import com.github.axinger.item.UserReader;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
public class TestController {

    @Resource
    private JobLauncher jobLauncher;

    @Resource(name = "userJob")
    private Job job1;


    @Autowired
    private UserReader userReader;

    @Resource
    private JobExplorer jobExplorer;

    @Resource
    private JobRepository jobRepository;


    @GetMapping("/job")
    public Object runImportUserJob() throws Exception {

        StopWatch stopWatch = new StopWatch();
//        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
////                .addDate("date",name)
//                .addLong("time",new Date().getTime())
//                .getNextJobParameters(job1)
//                .toJobParameters();
        stopWatch.start();;
        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("date",name)
                .addLong("time",new Date().getTime())
                .toJobParameters();

        JobExecution run = jobLauncher.run(job1, jobParameters);
        ExitStatus exitStatus = run.getExitStatus();
        stopWatch.stop();

        Map<String, Object> map = new HashMap<>();
        map.put("耗时", stopWatch.getTotalTimeSeconds());
        map.put("status", exitStatus.getExitCode());
        map.put("status2", run.getStatus());
        log.info("任务结束={}", map);
        return map;
    }
}
