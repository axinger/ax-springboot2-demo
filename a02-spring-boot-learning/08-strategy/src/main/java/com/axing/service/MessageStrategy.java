package com.axing.service;

import com.axing.demo.entity.MessageInfo;

public interface MessageStrategy {

    void handleMessage(MessageInfo messageInfo);
}
