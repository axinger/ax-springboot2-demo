package com.github.axinger.controller;

import com.axing.common.quartz.model.CronTaskPOJO;
import com.axing.common.quartz.service.JobService;
import com.axing.common.response.result.Result;
import com.axing.demo.job.MyJob;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
@Slf4j
@RequestMapping("/job")
@Tag(name = "任务")
public class JobController {

    private final CompletableFuture<Boolean> future = new CompletableFuture<>();
    @Autowired
    private JobService jobService;
//    @Autowired
//    private StudentService studentService;

    @Operation(summary = "添加一个任务", description = "添加描述")
    @GetMapping("/{id}")
    public Object addJob(@PathVariable String id) {
        log.info("id = {}", id);

        CronTaskPOJO cronTaskPOJO = new CronTaskPOJO();
        cronTaskPOJO.setJobName(id);
        cronTaskPOJO.setCronExpression("0/" + 5 + " * * * * ?");
        cronTaskPOJO.setJobClass(MyJob.class);
        final Map<String, Object> map = new HashMap<>(16);
        map.put("name", "jim");
        map.put("age", 18);
        cronTaskPOJO.setParameterMap(map);

        jobService.addJob(cronTaskPOJO);

//        final Boolean join = future.join();
//        System.out.println("join = " + join);

        return Result.ok();
//        return Result.ok(join);
    }


    @Operation(summary = "是否存在一个任务", description = "添加描述")
    @GetMapping("/notExists/{id}")
    public Object notExists(@PathVariable String id) {

        Boolean b = jobService.isExists(id, null);

//        final Boolean join = future.join();
//        System.out.println("join = " + join);

        return Result.ok(b ? "存在" : "不存在");
//        return Result.ok(join);
    }

//    @GetMapping("/{id}")
//    public Result addJob2(@PathVariable String id) {
//        log.info("id = {}", id);
//
//        CronTask task = new CronTask();
//        task.setJobName(id);
//        task.setCronExpression("0/" + 5 + " * * * * ?");
//        task.setJobClass(this.getClass());
//        final Map<String, Object> map = new HashMap<>(16);
//        map.put("name", "jim");
//        map.put("age", 18);
//
//
//        // 任务名称和组构成任务key
//        JobDetail jobDetail =
//                JobBuilder.newJob(task.getJobClass()).withIdentity(task.getJobName(), task.getTypeName())
//                        // 参数
//                        .usingJobData("parameter", task.getParameter()).build();
//
//
//        // 定义调度触发规则、使用cornTrigger规则、触发器key
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getTypeName())
//                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
//                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
//        // 把作业和触发器注册到任务调度中
//        try {
//            scheduler.scheduleJob(jobDetail, trigger);
//            // 启动
//            if (!scheduler.isShutdown()) {
//                scheduler.start();
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//
//        final Boolean join = future.join();
//        System.out.println("join = " + join);
//
//        return Result.ok(join);
//    }


    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTaskPOJO cronTaskPOJO = new CronTaskPOJO();
        cronTaskPOJO.setJobName(id);
        jobService.deleteJob(cronTaskPOJO);
        return Result.ok();
    }

    /**
     * 恢复
     *
     * @param id
     * @return
     */
    @GetMapping("/resume/{id}")
    public Object resume(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTaskPOJO cronTaskPOJO = new CronTaskPOJO();
        cronTaskPOJO.setJobName(id);
        jobService.resumeJob(cronTaskPOJO);
        return Result.ok();
    }

    /**
     * 暂停
     *
     * @param id
     * @return
     */
    @GetMapping("/pauseJob/{id}")
    public Object pauseJob(@PathVariable String id) {
        log.info("id = {}", id);
        final CronTaskPOJO cronTaskPOJO = new CronTaskPOJO();
        cronTaskPOJO.setJobName(id);
        jobService.resumeJob(cronTaskPOJO);
        return Result.ok();
    }


    @GetMapping("/list")
    public Result<?> all() {
        List<Object> list = new ArrayList<>();
        list.add(jobService.getAllJob());
//        list.add(studentService.list());
        return Result.ok(list);
    }
}
