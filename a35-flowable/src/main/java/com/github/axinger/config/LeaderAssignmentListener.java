package com.github.axinger.config;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.IdentityService;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaderAssignmentListener implements TaskListener {

//    @Autowired
//    private IdentityService identityService;
//
//
//    private UserEntity getLeaderForUser(String userId) {
//        // 在这里实现获取当前用户直属领导的逻辑
//        // 这可以是通过数据库查询，LDAP查询，或者其他业务逻辑获取领导信息
//        return identityService.createUserQuery().userId(userId).singleResult().getManager();
//    }

    @Override
    public void notify(org.flowable.task.service.delegate.DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();

        if (assignee != null) {
            // 假设你有一个方法 getLeaderForUser 来获取直属领导
//            UserEntity leader = getLeaderForUser(assignee);

//            if (leader != null) {
                delegateTask.setVariable("currentLeader", "002");
//            }
        }
    }
}
