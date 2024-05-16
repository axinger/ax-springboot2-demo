package com.ax.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ax.pojo.ChatRoomRequest;
import com.ax.pojo.ChatRoomResponse;
import com.ax.pojo.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
@Tag(name = "UserMsgController", description = "发送消息")
@Slf4j
public class UserMsgController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 根据ID 把消息推送给指定用户
     * 1. 这里用了 @SendToUser 和 返回值 其意义是可以在发送成功后回执给发送放其信息发送成功
     * 2. 非必须，如果实际业务不需要关心此，可以不用@SendToUser注解，方法返回值为void
     * 3. 这里接收人的参数是用restful风格带过来了，websocket把参数带到后台的方式很多，除了url路径，
     * 还可以在header中封装用@Header或者@Headers去取等多种方式
     * //     * @param accountId 消息接收人ID
     *
     * @param value          消息JSON字符串
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/alone")
    @SendToUser("/userTest/callBack")
    @Operation(summary = "单点")
    public JSONObject greeting(
            @RequestBody Message msg,
            StompHeaderAccessor headerAccessor
    ) {
        log.info("服务端收到单点消息");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", LocalDateTime.now());
        jsonObject.put("type", "发送成功");
        jsonObject.put("data", msg);
        template.convertAndSendToUser(msg.getTo(), "/message", msg);

        return jsonObject;
    }



}
