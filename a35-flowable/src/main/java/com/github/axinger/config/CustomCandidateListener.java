package com.github.axinger.config;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEntityEventImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomCandidateListener implements FlowableEventListener {

    @Override
    public void onEvent(FlowableEvent event) {
//
//        if (event.getType().equals(FlowableEngineEventType.PROCESS_STARTED)) {
//            // 流程启动事件
//            System.out.println("流程已启动");
//        } else if (event.getType().equals(FlowableEngineEventType.TASK_COMPLETED)) {
//            // 任务完成事件
//            System.out.println("任务已完成");
//        }


        if (event.getType() == FlowableEngineEventType.TASK_CREATED) {
            // 自定义逻辑，比如设置候选人，记录日志，发送通知等

            FlowableEntityEventImpl entityEvent = (FlowableEntityEventImpl) event;
            TaskEntity task = (TaskEntity) entityEvent.getEntity();


            // 修改候选用户和候选组
//            task.getCandidateUsers().clear();
//            task.getCandidateGroups().clear();

            // 添加自定义的候选用户
//            task.addCandidateUser("john");
//            task.addCandidateUser("jane");
//
//            // 添加自定义的候选组
//            task.addCandidateGroup("sales");
//            task.addCandidateGroup("management");
            String owner = task.getOwner();
            log.info("监听-任务开始创建，owner={}", owner);
            log.info("监听-任务开始创建，id={}", task.getId());
//            task.setVariable("currentLeader", "101");
//            // 设置任务审批人
//            task.setAssignee("101");
        }
    }


    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return "";
    }
    @Override
    public boolean isFailOnException() {
        // 如果监听器失败，是否应使事务回滚
        return true;
    }

}
