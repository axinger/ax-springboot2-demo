package com.github.axinger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Resource
    private JobLauncher jobLauncher;

    @Resource(name = "userJob")
    private Job job1;


    @GetMapping("/job")
    public Object runImportUserJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution run = jobLauncher.run(job1, jobParameters);
        ExitStatus exitStatus = run.getExitStatus();

        log.info("任务结束={}", exitStatus.getExitCode());

        BatchStatus status = run.getStatus();
        switch (status) {
            case STARTED:
                log.info("userJob===开始");
                break;

            case STOPPED:
                log.info("userJob===停止");
                break;

            case UNKNOWN:
                log.info("userJob===未知");
                break;

            case STARTING:
                log.info("userJob===开始中");
                break;

            case STOPPING:
                log.info("userJob==结束中");
                break;

            case ABANDONED:
                log.info("userJob==异常");
                break;
            case COMPLETED:
                log.info("userJob===完成");
                break;

            case FAILED:
                log.info("userJob===失败");
                break;
            default:
                break;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("time", System.currentTimeMillis());
        map.put("status", exitStatus.getExitCode());
        return map;
    }
}
