package com.ax.demo.juc;

import lombok.Data;

@Data
public class ThreadPoolCfg {
    /**
     * 核心线程数
     */
    private Integer corePoolSize = 5;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize = 10;

    /**
     * 空闲线程存活时间
     */
    private Integer keepAliveSeconds = 60;

    /**
     * 等待队列长度
     */
    private Integer queueCapacity = 15;

}