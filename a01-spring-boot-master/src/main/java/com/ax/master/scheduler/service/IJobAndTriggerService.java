package com.ax.master.scheduler.service;


import com.ax.master.dto.JobAndTriggerDto;


/**
 * @author lixin(1309244704 @ qq.com)
 * @version V1.0
 * @ClassName: IJobAndTriggerService
 * @Description: TODO(quartz接口)
 * @date 2018年3月15日 上午10:00:07
 */
public interface IJobAndTriggerService {
    /**
     * @Title: getPageJob
     * @Description: TODO(查询定时任务 ， 分页)
     * @param @param search
     * @param @return    参数
     * @return Map<String, Object>    返回类型
     * @throws
     */
//	public Map<String, Object> getPageJob(PageUtil search);

    /**
     * @param @return 参数
     * @return JobAndTriggerDto    返回类型
     * @throws
     * @Title: getPageJobmod
     * @Description: TODO(查询定时任务)
     */
    JobAndTriggerDto getPageJobmod();

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @param  cronExpression cron时间规则
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: addJob
     * @Description: TODO(添加任务)
     */
    void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @param  cronExpression cron时间规则
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: updateJob
     * @Description: TODO(更新定时任务)
     */
    void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: deleteJob
     * @Description: TODO(删除定时任务)
     */
    void deleteJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: pauseJob
     * @Description: TODO(暂停定时任务)
     */
    void pauseJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: resumejob
     * @Description: TODO(恢复任务)
     */
    void resumejob(String jobClassName, String jobGroupName) throws Exception;
}
