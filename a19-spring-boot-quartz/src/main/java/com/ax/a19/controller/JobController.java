package com.ax.a19.controller;

import com.ax.a19.job.MyJob;
import com.ax.a19.service.StudentService;
import com.ax.common.quartz.model.CronTask;
import com.ax.common.quartz.service.MyQuartzService;
import com.ax.common.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
@RequestMapping("/job")
public class JobController implements Job {

    @Autowired
    private MyQuartzService myQuartzService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private Scheduler scheduler;

    private CompletableFuture<Boolean> future = new CompletableFuture<>();

    @GetMapping("/{id}")
    public Result addJob(@PathVariable String id) {
        log.info("id = {}", id);

        CronTask cronTask = new CronTask();
        cronTask.setJobName(id);
        cronTask.setCronExpression("0/" + 5 + " * * * * ?");
        cronTask.setJobClass(this.getClass());
        final Map<String, Object> map = new HashMap<>(16);
        map.put("name", "jim");
        map.put("age", 18);
        cronTask.setParameterMap(map);

        myQuartzService.addJob(cronTask);

        final Boolean join = future.join();
        System.out.println("join = " + join);

        return Result.ok(join);
    }


    @GetMapping("/{id}")
    public Result addJob2(@PathVariable String id) {
        log.info("id = {}", id);

        CronTask task = new CronTask();
        task.setJobName(id);
        task.setCronExpression("0/" + 5 + " * * * * ?");
        task.setJobClass(this.getClass());
        final Map<String, Object> map = new HashMap<>(16);
        map.put("name", "jim");
        map.put("age", 18);


        // 任务名称和组构成任务key
        JobDetail jobDetail =
                JobBuilder.newJob(task.getJobClass()).withIdentity(task.getJobName(), task.getTypeName())
                        // 参数
                        .usingJobData("parameter", task.getParameter()).build();


        // 定义调度触发规则、使用cornTrigger规则、触发器key
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getTypeName())
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
        // 把作业和触发器注册到任务调度中
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }




        final Boolean join = future.join();
        System.out.println("join = " + join);

        return Result.ok(join);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTask cronTask = new CronTask();
        cronTask.setJobName(id);
        return Result.ok(myQuartzService.deleteJob(cronTask));
    }

    /**
     * 恢复
     *
     * @param id
     * @return
     */
    @RequestMapping("/resume/{id}")
    public Result resume(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTask cronTask = new CronTask();
        cronTask.setJobName(id);
        myQuartzService.resumeJob(cronTask);
        return Result.ok();
    }

    /**
     * 暂停
     *
     * @param id
     * @return
     */
    @RequestMapping("/pauseJob/{id}")
    public Result pauseJob(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTask cronTask = new CronTask();
        cronTask.setJobName(id);
        myQuartzService.resumeJob(cronTask);
        return Result.ok();
    }


    @RequestMapping("/list")
    public Result all() {
        List<Object> list = new ArrayList<>();
        list.add(studentService.getById(1L));
        list.add(myQuartzService.getAllJob());
        return Result.ok(list);
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        // 从定时任务中,取参数
//        final JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        System.out.println("dataMap = " + dataMap);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String name = jobExecutionContext.getJobDetail().getKey().getName();
        log.info("定时任务 key = {}", name);
        future.complete(true);
    }
}
