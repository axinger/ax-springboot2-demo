package com.axing.common.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Author: liangbl
 * @Date: 2019/1/21 13:46
 * @Description: 自定义定时任务类
 */
@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        log.info("定时任务 jobName = " + jobName);
    }
}
