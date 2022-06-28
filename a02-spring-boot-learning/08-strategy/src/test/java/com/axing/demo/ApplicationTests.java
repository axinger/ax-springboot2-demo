package com.axing.demo;

import com.axing.demo.annotation.MsgTypeHandler;
import com.axing.demo.entity.MessageInfo;
import com.axing.demo.entity.MsgTypeEnum;
import com.axing.demo.service.MessageStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class ApplicationTests {


    private Map<MsgTypeEnum, MessageStrategy> messageStrategyMap;

    @Resource
    private void setMessageStrategyMap(List<MessageStrategy> messageStrategies) {
        messageStrategyMap = messageStrategies.stream().collect(
                Collectors.toMap(item -> AnnotationUtils.findAnnotation(item.getClass(), MsgTypeHandler.class).value(), v -> v));
    }

    @Test
    public void test1() {
        MessageInfo messageInfo = new MessageInfo(MsgTypeEnum.TEXT, "这是一个文本消息");
        MessageStrategy messageStrategy = messageStrategyMap.get(messageInfo.getType());
        // 处理文本消息 这是一个文本消息
        messageStrategy.handleMessage(messageInfo);
    }

    @Test
    public void test2() {
        MessageInfo messageInfo = new MessageInfo(MsgTypeEnum.IMAGE, "这是一个图片消息");
        MessageStrategy messageStrategy = messageStrategyMap.get(messageInfo.getType());
        // 处理图片消息 这是一个图片消息
        messageStrategy.handleMessage(messageInfo);
    }

}
