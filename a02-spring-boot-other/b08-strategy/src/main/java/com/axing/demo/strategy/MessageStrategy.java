package com.axing.demo.strategy;

import com.axing.demo.entity.MessageInfo;

public interface MessageStrategy {

    void handleMessage(MessageInfo messageInfo);
}
