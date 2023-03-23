package com.axing.demo.strategy.impl;

import com.axing.demo.annotation.MsgTypeHandler;
import com.axing.demo.entity.MessageInfo;
import com.axing.demo.entity.MsgTypeEnum;
import com.axing.demo.strategy.MessageStrategy;
import org.springframework.stereotype.Service;

@Service
@MsgTypeHandler(value = MsgTypeEnum.TEXT)
public class TextMessageStrategy implements MessageStrategy {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理文本消息 = " + messageInfo.getContent());
    }
}
