package com.ax.demo.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @author: 徐子木
 * @create: 2020-09-30 15:06
 **/
@Slf4j
@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {
//
//// 是消息通信dubbo类,请忽略
//    @Autowired
//    private ChatMsgService chatMsgService;
//// 是db存储类 ,请忽略
//    @Autowired
//    private BidPresentDao bidPresentDao;
//
//// 这里是netty可为指定的唯一key去与连接进行分组处理并存储
//    private HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
//
//    private final AttributeKey<String> userKey = AttributeKey.valueOf("user");
//
//    private final AttributeKey<String> sectionKey = AttributeKey.valueOf("section");
//
//    /**
//     * 装载标段与对应在线的用户
//     */
//    private static final Map<String, ChannelGroup> SECTION_GROUPS = new ConcurrentHashMap<>();
//
//
//    /**
//     * 维护某标段中的socket连接
//     *
//     * @param sectionId
//     * @param channel
//     */
    @Override
    public void putConnection(String userId, String sectionId, NioSocketChannel channel) {

//        channel.attr(userKey).set(userId);
//        channel.attr(sectionKey).set(sectionId);
//
//        bidPresentDao.comeOnlineByUserId(userId, sectionId);
//
//        //存储用户标段对应连接
//        ChannelGroup channelGroup = SECTION_GROUPS.get(sectionId);
//        if (null == channelGroup) {
//            //保存全局的,连接上的服务器的客户
//            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//            channelGroup.add(channel);
//            SECTION_GROUPS.put(sectionId, channelGroup);
//        } else {
//            channelGroup.add(channel);
//        }
    }
//
//    /**
//     * 判断一个通道是否有用户在使用
//     *
//     * @param channel
//     * @return
//     */
//    private boolean hasUser(Channel channel) {
//        return ((channel.hasAttr(userKey) || channel.hasAttr(sectionKey)));
//    }
//
//    /**
//     * 获取连接对应用户
//     *
//     * @param channel
//     * @return
//     */
//    @Override
//    public String getBindUserId(NioSocketChannel channel) {
//        if (hasUser(channel)) {
//            return channel.attr(userKey).get();
//        }
//        return null;
//    }
//
//    /**
//     * 获取连接对应标段Id
//     *
//     * @param channel
//     * @return
//     */
//    @Override
//    public String getBindSectionId(NioSocketChannel channel) {
//        if (hasUser(channel)) {
//            return channel.attr(sectionKey).get();
//        }
//        return null;
//    }
//
//
//    /**
//     * 用户退出标段在线连接
//     *
//     * @param channel
//     */
    @Override
    public void removeConnection(NioSocketChannel channel) {
//        String userId = getBindUserId(channel);
//        String sectionId = getBindSectionId(channel);
//
//        Iterator<Map.Entry<String, ChannelGroup>> iterator = SECTION_GROUPS.entrySet().iterator();
//        while (iterator.hasNext()) {
//            ChannelGroup channelGroup = iterator.next().getValue();
//            if (channelGroup.contains(channel)) {
//                channelGroup.remove(channel);
//            }
//            if (null == channelGroup || channelGroup.size() == 0) {
//                iterator.remove();
//            }
//        }
//        if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(sectionId)) {
//            bidPresentDao.exitOnlineByUserId(userId, sectionId);
//        }
    }
//
//    /**
//     * 根据用户Id获取连接
//     *
//     * @param userId
//     * @return
//     */
//    private NioSocketChannel getChannel(String userId) {
//        Iterator<Map.Entry<String, ChannelGroup>> iterator = SECTION_GROUPS.entrySet().iterator();
//        while (iterator.hasNext()) {
//            ChannelGroup channelGroup = iterator.next().getValue();
//            for (Channel channel : channelGroup) {
//                if (userId.equalsIgnoreCase(channel.attr(userKey).get())) {
//                    return (NioSocketChannel) channel;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 发送纯状态码的消息
//     *
//     * @param toUserId
//     * @param message
//     */
//    @Override
//    public void sendMessage(String toUserId, String message) {
//        NioSocketChannel channel = getChannel(toUserId);
//        if (channel != null) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("message", message);
//            channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(jsonObject)));
//        }
//    }
//
//    /**
//     * 向指定投标人发送状态
//     *
//     * @param toUserId
//     */
//    @Override
//    public void sendMessage(String toUserId, MessageEnum messageEnum) {
//        NioSocketChannel channel = getChannel(toUserId);
//        if (channel != null) {
//            MessageObject messageObject = MessageObject.builder().
//                    code(messageEnum.getCode())
//                    .build();
//            channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(messageObject)));
//        }
//    }
//
//    /**
//     * 向当前标段所有投标人发送消息
//     *
//     * @param sectionId
//     */
//    @Override
//    public void sendMessageAll(String sectionId, MessageEnum messageEnum) {
//        log.debug("标段Id: {},发送状态码: {}", sectionId, messageEnum);
//        MessageObject messageObject = MessageObject.builder()
//                .code(messageEnum.getCode())
//                .build();
//        sendMessageAll(sectionId, messageObject);
//    }
//
//    @Override
//    public void sendMessageAll(String sectionId, MessageObject messageObject) {
//        ChannelGroup channelGroup = SECTION_GROUPS.get(sectionId);
//        if (channelGroup == null || channelGroup.size() == 0) {
//            log.warn("暂时无客户端在线 sectionId：{}", sectionId);
//            return;
//        }
//        channelGroup.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(messageObject)));
//    }
//
//
//    /**
//     * 根据状态码处理消息
//     *
//     * @param message
//     */
//    @Override
//    public void sendMessage(MessageObject message, ChannelHandlerContext ctx) {
//        MessageEnum messageEnum = MessageEnum.valuesOf(message.getCode());
//        switch (messageEnum) {
//            case CHAT_SEND_MESSAGE:
//                ChatMsg msg = JSONObject.parseObject(message.getData().toString(), ChatMsg.class);
//                flushSectionChat(msg, ctx);
//                break;
//            case HEART_CONNECT:
//                //心跳连接
//                break;
//            case REDIRECT:
//                RedirectEntity redirectEntity = JSONObject.parseObject(message.getData().toString(), RedirectEntity.class);
//                flushSectionToObject(redirectEntity.getSectionId(), redirectEntity);
//                break;
//            case DECODE:
//                String sectionId = JSONObject.parseObject(message.getData().toString(), String.class);
//                sendMessageAll(sectionId, MessageEnum.DECODE);
//                break;
//            default:
//
//                break;
//        }
//
//    }
//
//    /**
//     * 向该标段中发送系统消息
//     *
//     * @param sectionId
//     * @param msg
//     */
//    @Override
//    public void flushSectionSystem(String sectionId, String msg) {
//        ChatMsg chatMsg = ChatMsg.builder()
//                .sectionId(sectionId)
//                .msgType(ChatMsgType.NOTICE.getCode())
//                .content(msg)
//                .build();
//        flushSectionChat(chatMsg, null);
//    }
//
//    /**
//     * 向当前标段的在线人员刷新一条消息
//     *
//     * @param chatMsg
//     */
//    private void flushSectionChat(ChatMsg chatMsg, ChannelHandlerContext ctx) {
//        if (ObjectUtil.isNotEmpty(ctx)) {
//            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
//            String ip = inetSocketAddress.getAddress().getHostAddress();
//            chatMsg.setIp(ip);
//        }
//
//        String sectionId = chatMsg.getSectionId();
//        MessageObject messageObject = MessageObject.builder()
//                .code(MessageEnum.CHAT_SEND_MESSAGE.getCode())
//                .data(chatMsg)
//                .build();
//        sendMessageAll(sectionId, messageObject);
//        chatMsgService.create(chatMsg);
//    }
//
//
//    /**
//     * 当前标段在线人员发送自定义数据
//     *
//     * @param sectionId
//     * @param object
//     */
//    private void flushSectionToObject(String sectionId, Object object) {
//        MessageObject messageObject = MessageObject.builder()
//                .code(MessageEnum.REDIRECT.getCode())
//                .data(object)
//                .build();
//
//        sendMessageAll(sectionId, messageObject);
//    }
//
//
//    /**
//     * 向指定用户发送自定义数据
//     *
//     * @param toUserId
//     */
//    @Override
//    public void sendMessage(String toUserId, Object object, MessageEnum messageEnum) {
//        NioSocketChannel channel = getChannel(toUserId);
//        if (channel != null) {
//            MessageObject messageObject = MessageObject.builder().
//                    code(messageEnum.getCode())
//                    .data(object)
//                    .build();
//            if(MessageEnum.EXTRACT_PARAM_RESULT.equals(messageEnum)){
//                log.debug("参数抽取发送内容：{}",JSONObject.toJSONString(messageObject));
//            }
//            channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(messageObject)));
//        }
//    }


}

