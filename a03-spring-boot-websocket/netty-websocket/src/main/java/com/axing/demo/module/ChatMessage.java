package com.axing.demo.module;

import com.axing.demo.service.ChatConstants;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChatMessage implements Serializable {
    //发送消息则
    private UserInfo from;

    //接收者列表
    private Map<String, UserInfo> to;

    //发送内容
    private String message;

    private Date createTime;

    public ChatMessage(UserInfo from, String message) {
        this.from = from;
        this.message = message;
        this.to = ChatConstants.onlines;
        this.createTime = new Date();
    }


}
