package com.github.axinger.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();

        // 添加一个字符串解码器，用于将接收到的ByteBuf转换成字符串
        // 这里假设使用的是UTF-8字符集
        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));

        // 添加一个字符串编码器，用于将发送的字符串转换成ByteBuf
        // 这样服务器发送字符串时，客户端可以直接接收到字符串
        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

        // 添加自定义的ChannelInboundHandlerAdapter来处理业务逻辑
        pipeline.addLast("handler", new MyChannelHandler());
    }
}
