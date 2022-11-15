package com.ax.master.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author lixin(1309244704 @ qq.com)
 * @version V1.0
 * @ClassName: HelloJob
 * @Description: TODO()
 * @date 2018年3月15日 上午10:02:16
 */
public class HelloJob implements Job {

    private static final Logger _log = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.info("info Hello Job执行时间: " + new Date());
        System.out.println("Hello Job执行时间: " + new Date());
    }
}
