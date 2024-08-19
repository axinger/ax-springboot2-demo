package com.github.axinger;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@SpringBootTest
public class FlowableServerTest {


    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;



    @Test
    void test_00(){
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        log.info("Deployment size={}",list.size());

        for (Deployment deployment : list) {

            JSONObject jsonObject =  new JSONObject();

            jsonObject.put("id", deployment.getId());
            jsonObject.put("name", deployment.getName());
            jsonObject.put("key", deployment.getKey());
            jsonObject.put("category", deployment.getCategory());
            jsonObject.put("发布时间",deployment.getDeploymentTime());
            System.out.println("jsonObject = " + jsonObject);

//            repositoryService.deleteDeployment(deployment.getId(), true);


        }

//        repositoryService.deleteDeployment(deployment.getId(), true);
    }

    // 发布流程，将流程定义部署到流程引擎中：
    // 需要把ui的流程加载到项目中，但是使用ui，直接发布也可以
    @Test
    void test_01() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/caiwu_01.bpmn20.xml")
                .deploy();
        String id = deployment.getId();
        System.out.println("id = " + id);
    }


    // 启动流程
    @Test
    void test_02() {

        // 根据流程id，获取流程实例;
        String userId = "001";
//        Authentication.setAuthenticatedUserId("001");//设置流程发起人，方式一
        String now = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
//        String processKey = "caiwu_01"; //我们创建流程图自定义的key
        String processKey = "caigou_01"; //我们创建流程图自定义的key
        // 根据流程key，获取流程实例
        Map<String, Object> variables = new HashMap<>();
        variables.put("data1", "001");
        variables.put("data2", "002");
        variables.put("currentLeader", "101");
        variables.put("date", now);

//        identityService.setAuthenticatedUserId(userId); //设置流程发起人，方式二

//        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processKey, variables);


        ProcessInstance instance = runtimeService.createProcessInstanceBuilder()
                .owner(userId) // 发起人
                .processDefinitionKey(processKey)
                .name(StrUtil.format("采购流程-{}",now))
                .variables(variables)
                .start();


        log.info("启动流程 id = {}，{},{}", instance.getId(), instance.getStartUserId(), instance.getProcessVariables());

//        Authentication.setAuthenticatedUserId(null);//这个方法最终使用一个ThreadLocal类型的变量进行存储，也就是与当前的线程绑定，所以流程实例启动完毕之后，需要设置为null，防止多线程的时候出问题。

//        identityService.setAuthenticatedUserId(null);

    }

    /**
     * 查询用户，历史流程
     */
    @Test
    void test_02_01() {

        String userId = "001";


        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
//                .startedBy(userId)
//                .processInstanceBusinessKey(userId) // 如果使用业务键存储发起人信息
                // 如果你想同时查询已完成和未完成的流程实例，
//                .includeProcessVariables() //可选，如果需要流程变量信息
//                .finished()
//                .unfinished()
//                .deleted()
//                .desc()
                .orderByProcessInstanceStartTime()
                .desc()
                .listPage(0, 10);
        ;


        log.info("size={}", list.size());
//        启动流程 id = ed58d12a-5e20-11ef-bc29-c88a9ada8d91，001,{date=2024-08-19T19:48:18.541185, data2=002, data1=001}
        for (HistoricProcessInstance instance : list) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", instance.getId());

            jsonObject.put("开始时间", LocalDateTimeUtil.of(instance.getStartTime()));
            jsonObject.put("结束时间", LocalDateTimeUtil.of(instance.getEndTime()));
            jsonObject.put("activity", instance.getPropagatedStageInstanceId());
            jsonObject.put("name", instance.getName());
            jsonObject.put("发起人id", instance.getStartUserId());
            jsonObject.put("参数", instance.getProcessVariables());
            jsonObject.put("businessKey", instance.getBusinessKey());
            jsonObject.put("processDefinitionKey", instance.getProcessDefinitionKey());
            jsonObject.put("processDefinitionId", instance.getProcessDefinitionId());
            jsonObject.put("processDefinitionName", instance.getProcessDefinitionName());
            jsonObject.put("processDefinitionVersion", instance.getProcessDefinitionVersion());
//            jsonObject.put("描述", Optional.ofNullable(instance.getDescription()).orElse(""));
            jsonObject.put("描述", instance.getDescription());

            log.info("{}", jsonObject.toString(JSONWriter.Feature.WriteMapNullValue));

            // 删除流程
//            try {
//                historyService.deleteHistoricProcessInstance(instance.getId());
//            } catch (Exception e) {
//                runtimeService.deleteProcessInstance(instance.getId(), "");
//            }
        }


    }

    /**
     * 删除用户，历史流程
     */
    @Test
    void test_02_02() {
//        historyService.deleteHistoricTaskInstance("1a69dd8a-5e1e-11ef-8e72-c88a9ada8d91"); // 这个不知道是什么id
        historyService.deleteHistoricProcessInstance("5389e399-5e1e-11ef-8fd8-c88a9ada8d91");
    }

    @Test
    void test_02_03() {
//        historyService.deleteHistoricTaskInstance("1a69dd8a-5e1e-11ef-8e72-c88a9ada8d91"); // 这个不知道是什么id
        runtimeService.deleteProcessInstance("66e25cab-5e20-11ef-8dc9-c88a9ada8d91", "");
    }

    // 用户待审批流程
    @Test
    void test_03() {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("002")
                .listPage(0, 10);
        System.out.println("list = " + list);
    }

    //审批
    @Test
    void test_04() {
        taskService.complete("", Map.of());
    }

}
