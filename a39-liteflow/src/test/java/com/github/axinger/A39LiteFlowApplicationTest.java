package com.github.axinger;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.property.LiteflowConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class A39LiteFlowApplicationTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void testConfig() {
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("c");

        try {


            LiteflowResponse response = flowExecutor.execute2Resp("chain1", user2, user1);


            boolean success = response.isSuccess();
            System.out.println("success = " + success);

            String message = response.getCause().getMessage();
            System.out.println("message = " + message);

            User contextBean = response.getContextBean(User.class);
            System.out.println("contextBean = " + contextBean);

            String message1 = response.getMessage();
            System.out.println("message1 = " + message1);

            String code = response.getCode();
            System.out.println("code = " + code);

            Object requestData = response.getSlot().getRequestData();
            System.out.println("requestData = " + requestData);
        } catch (Exception e) {

            log.error("错误 = {}", e.getMessage());

        }
    }
}
