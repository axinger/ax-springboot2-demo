package com.github.axinger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配器模式
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //获取客户端发送过来的消息
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
//    }

    //5.1 taskQueue任务队列
    //如果Handler处理器有一些长时间的业务处理，可以交给taskQueue异步处理
    //。怎么用呢，请看代码演示：
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取到线程池 eventLoop，添加线程，执行
        ctx.channel().eventLoop().execute(() -> {
            try {
                //长时间操作，不至于长时间的业务操作导致Handler阻塞
                Thread.sleep(1000);
                System.out.println("长时间的业务处理");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


//    5.2 scheduleTaskQueue延时任务队列
//            延时任务队列和上面介绍的任务队列
//    非常相似，只是多了一个可延迟一定时间再执行的设置，请看代码演示：

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ctx.channel().eventLoop().schedule(() -> {
//            try {
//                //长时间操作，不至于长时间的业务操作导致Handler阻塞
//                Thread.sleep(1000);
//                System.out.println("长时间的业务处理");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, 5, TimeUnit.SECONDS);//5秒后执行
//    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //发送消息给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息，并给你发送一个问号?", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常，关闭通道
        ctx.close();
    }
}
