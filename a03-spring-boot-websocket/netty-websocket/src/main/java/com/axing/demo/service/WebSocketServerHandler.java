package com.axing.demo.service;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @description
 * @author: 徐子木
 * @create: 2020-06-02 14:57
 **/
@Slf4j
@Component
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    public static final byte PING_MSG = 1;
    public static final byte PONG_MSG = 2;
    public static final byte CUSTOM_MSG = 3;
    public static WebSocketServerHandler webSocketServerHandler;
    private final int heartbeatCount = 0;
    // 配置客户端是否为https的控制
    @Value("${netty.ssl-enabled:false}")
    private Boolean useSsl;
    /**
     * 这里可以引入自己业务类来处理进行的客户端连接
     */
    @Autowired
    private MessageService messageService;
    private WebSocketServerHandshaker handshaker;

    @SuppressWarnings("deprecation")
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
//            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
//            res.content().writeBytes(buf);

            // buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 解决启动加载不到自己业务类
     */
    @PostConstruct
    public void init() {
        webSocketServerHandler = this;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // http请求和tcp请求分开处理
        if (msg instanceof HttpRequest) {
            handlerHttpRequest(ctx, (HttpRequest) msg);
        } else if (msg instanceof WebSocketFrame frame) {
            // 踩坑: simpleChannelInboundHandler 他会进行一次释放(引用计数器减一),参考源码,而我们释放的时候就变为了0,所以必须手动进行引用计数器加1
            frame.retain();
            handlerWebSocketFrame(ctx, frame);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * WebSocket 消息处理
     *
     * @param ctx
     * @param frame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            log.info("【" + ctx.channel().remoteAddress() + "】已关闭（服务器端）");
            // 移除channel
            NioSocketChannel channel = (NioSocketChannel) ctx.channel();
            webSocketServerHandler.messageService.removeConnection(channel);
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame);
            return;
        }
        // 判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            log.info("【ping】");
            return;
        }
        // 判断实时是pong消息
        if (frame instanceof PongWebSocketFrame) {
            log.info("【pong】");
            return;
        }
        // 本例子只支持文本，不支持二进制
        if (!(frame instanceof TextWebSocketFrame)) {
            log.info("【不支持二进制】");
            throw new UnsupportedOperationException("不支持二进制");
        }

        // 传送的消息 ,接收客户端指定格式(自己与客户端约定json格式)的消息,并进行处理
//        MessageObject messageObject = JSONObject.parseObject(((TextWebSocketFrame) frame).text().toString(), MessageObject.class);
//        webSocketServerHandler.messageService.sendMessage(messageObject, ctx);
    }

    /**
     * websocket第一次连接握手
     *
     * @param ctx
     */
    @SuppressWarnings("deprecation")
    private void handlerHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
// 这里接收客户端附加连接参数,根据自己业务与客户端指定需要哪些参数来辨别连接唯一性
        String userUid = null;
        String sectionId = null;
        if ("GET".equalsIgnoreCase(req.getMethod().toString())) {
            String uri = req.getUri();
            userUid = req.getUri().substring(uri.indexOf("/", 2) + 1, uri.lastIndexOf("/"));
            sectionId = req.getUri().substring(uri.lastIndexOf("/") + 1);
            // 对用户信息进行存储
            NioSocketChannel channel = (NioSocketChannel) ctx.channel();

            webSocketServerHandler.messageService.putConnection(userUid, sectionId, channel);
        }

        // http 解码失败
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equalsIgnoreCase(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, (FullHttpRequest) req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        // 可以通过url获取其他参数
        WebSocketServerHandshakerFactory factory;
// 这里主要用于 客户端为wss连接的处理
        if (useSsl != null && useSsl) {
            factory = new WebSocketServerHandshakerFactory(
                    "wss://" + req.headers().get("Host") + "/" + req.getUri() + "", null, false
            );
        } else {
            factory = new WebSocketServerHandshakerFactory(
                    "ws://" + req.headers().get("Host") + "/" + req.getUri() + "", null, false
            );
        }
        handshaker = factory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            // 进行连接
            handshaker.handshake(ctx.channel(), (FullHttpRequest) req);
        }
    }

    /**
     * 这里是保持服务器与客户端长连接  进行心跳检测 避免连接断开
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent stateEvent) {
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()) {
                // 读空闲（服务器端）
                case READER_IDLE:
                    // log.info("【" + ctx.channel().remoteAddress() + "】读空闲（服务器端）");
                    ctx.writeAndFlush(ping);
                    break;
                // 写空闲（客户端）
                case WRITER_IDLE:
                    // log.info("【" + ctx.channel().remoteAddress() + "】写空闲（客户端）");
                    ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    // log.info("【" + ctx.channel().remoteAddress() + "】读写空闲");
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 出现异常时
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        // 移除channel
        webSocketServerHandler.messageService.removeConnection((NioSocketChannel) ctx.channel());
        ctx.close();
        log.info("【" + ctx.channel().remoteAddress() + "】已关闭（服务器端）");
    }


}

