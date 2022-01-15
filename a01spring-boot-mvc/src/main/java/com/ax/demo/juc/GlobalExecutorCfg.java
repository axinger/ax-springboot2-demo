package com.ax.demo.juc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
/***
 *    corePoolSize:常驻线程数量
 *    maximumPoolSize: 最大
 *    keepAliveTime: 是否存活
 *    unit:保存存活时间
 *    workQueue: 阻塞队列
 *    threadFactory: 线程工程
 *    handler: 拒绝策略
 *
 */
public class GlobalExecutorCfg {
    private SmsThreadPoolCfg smsThreadPoolCfg;

    public GlobalExecutorCfg(SmsThreadPoolCfg smsThreadPoolCfg) {
        super();
        this.smsThreadPoolCfg = smsThreadPoolCfg;
    }

    @Bean(name = "smsExecutor")
    public Executor getSmsExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(smsThreadPoolCfg.getCorePoolSize());
        executor.setMaxPoolSize(smsThreadPoolCfg.getMaxPoolSize());
        executor.setKeepAliveSeconds(smsThreadPoolCfg.getKeepAliveSeconds());
        executor.setQueueCapacity(smsThreadPoolCfg.getQueueCapacity());
        executor.setThreadNamePrefix("短信多线程池-");
        return executor;
    }
}