package com.axing.common.quartz.service.impl;

import com.axing.common.quartz.job.MyJob;
import com.axing.common.quartz.model.CronTask;
import com.axing.common.quartz.service.MyQuartzService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xing
 * @date 2022/3/20 01:45
 */
@Service
@Slf4j
public class MyQuartzServiceImpl implements MyQuartzService {

    /**
     * 任务调度
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 开始执行所有任务
     */
    @Override
    public void startJob() {
        startJob(scheduler);
    }

    /**
     * 获取Job信息
     */
    @SneakyThrows
    @Override
    public String getJobInfo(String name, String group) {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改某个任务的执行时间
     */
    @SneakyThrows
    @Override
    public boolean modifyJob(String name, String group, String time) {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger =
                    TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     */
    @SneakyThrows
    @Override
    public void pauseAllJob() {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     */
    @SneakyThrows
    @Override
    public void pauseJob(String name, String group) {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     */
    @SneakyThrows
    @Override
    public void resumeAllJob() {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     */
    @SneakyThrows
    @Override
    public void resumeJob(String name, String group) {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除某个任务
     */
    @SneakyThrows
    @Override
    public void deleteJob(String name, String group) {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

    /**
     * 修改一个任务的触发时间
     * <p>
     * param triggerName 触发器名 param triggerGroupName 触发器组名 param cron 时间设置，参考quartz说明文档
     */
    @Override
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务
     * <p>
     * param jobName 任务名 param jobGroupName 任务组名 param triggerName 触发器名 param triggerGroupName 触发器组名
     */
    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取任务是否存在
     * <p>
     * STATE_BLOCKED 4 阻塞 STATE_COMPLETE 2 完成 STATE_ERROR 3 错误 STATE_NONE -1 不存在 STATE_NORMAL 0 正常 STATE_PAUSED 1 暂停
     */
    @Override
    public Boolean notExists(String triggerName, String triggerGroupName) {
        try {
            return scheduler
                    .getTriggerState(TriggerKey.triggerKey(triggerName, triggerGroupName)) == Trigger.TriggerState.NONE;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private void startJob(Scheduler scheduler) {
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withIdentity("job1", "group1").withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        // 启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 添加一个定时任务
     */
    @Override
    public void addJob(CronTask task) {
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类

            // Class<? extends Job> jobClass = (Class<? extends Job>)
            // (Class.forName(task.getBeanClass()).newInstance().getClass());
            // 任务名称和组构成任务key
            JobDetail jobDetail =
                    JobBuilder.newJob(task.getJobClass()).withIdentity(task.getJobName(), task.getTypeName())
                            // 参数
                            .usingJobData("parameter", task.getParameter()).build();
            // 判断map参数是否有值

            Optional.ofNullable(task.getParameterMap()).ifPresent(map -> {
                map.forEach((k, v) -> jobDetail.getJobDataMap().put(k, v));
            });

            // if (!StringUtils.isEmpty(task.getParameterMap())) {
            // Set<String> set = task.getParameterMap().keySet();
            // if (!StringUtils.isEmpty(task.getParameterMap().keySet())) {
            // for (String key : set) {
            // jobDetail.getJobDataMap().put(key, task.getParameterMap().get(key));
            // }
            // }
            // }
            // 定义调度触发规则、使用cornTrigger规则、触发器key
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getTypeName())
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     */
    @SneakyThrows
    @Override
    public List<CronTask> getAllJob() {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<CronTask> jobList = new ArrayList<CronTask>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                CronTask job = new CronTask();
                job.setJobName(jobKey.getName());
                job.setTypeName(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setTypeName(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     */
    @SneakyThrows
    @Override
    public List<CronTask> getRunningJob() {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<CronTask> jobList = new ArrayList<CronTask>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            CronTask job = new CronTask();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setTypeName(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(Long.parseLong(triggerState.name()));
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     */
    @SneakyThrows
    @Override
    public void pauseJob(CronTask task) {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getTypeName());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     * <p>
     * param task throws SchedulerException
     */
    @SneakyThrows
    @Override
    public void resumeJob(CronTask task) {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getTypeName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     * <p>
     * param task throws SchedulerException
     */
    @SneakyThrows
    @Override
    public boolean deleteJob(CronTask task) {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getTypeName());
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 立即执行job
     * <p>
     * param task throws SchedulerException
     */
    @SneakyThrows
    @Override
    public void runJobNow(CronTask task) {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getTypeName());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     * <p>
     * param task throws SchedulerException
     */
    @Override
    @SneakyThrows
    public void updateJobCron(CronTask task) {
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getTypeName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
