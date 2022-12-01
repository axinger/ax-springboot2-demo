package com.axing.service.impl;

import com.axing.demo.annotation.MsgTypeHandler;
import com.axing.demo.entity.MessageInfo;
import com.axing.demo.entity.MsgTypeEnum;
import com.axing.service.MessageStrategy;
import org.springframework.stereotype.Service;

@Service
@MsgTypeHandler(MsgTypeEnum.IMAGE)
public class ImageMessageStrategy implements MessageStrategy {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理图片消息 " + messageInfo.getContent());
    }
}
