package com.ax.demo.service;

import com.ax.demo.module.UserInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * MyWebSocketHandler
 * WebSocket处理器，处理websocket连接相关
 *
 * @author zhengkai.blog.csdn.net
 * @date 2019-06-12
 */
@Slf4j
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！" + ctx.channel());
        //添加到channelGroup通道组
        MyChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        MyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel channel = ctx.channel();
        String token = channel.attr(ChatConstants.CHANNEL_TOKEN_KEY).get();
        log.info("接收消息 token = " + token);
        log.info("接收消息 msg = " + msg.text());
        if (token == null) {
            return;
        }
        UserInfo from = ChatConstants.onlines.get(token);
        if (from == null) {
            sendAllMessage("OK");
        } else {
            sendAllMessage(msg.text());
        }
    }

    private void sendAllMessage(String message) {
        //收到信息后，群发给所有channel
        MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }
}