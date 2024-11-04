package com.github.axinger.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class NettyServer implements ApplicationRunner {

    //负责处理接受进来的链接
    private EventLoopGroup bossGroup;
    //负责处理已经被接收的连接上的I/O操作
    private EventLoopGroup workerGroup;
    //在这个场景中，它表示服务器的绑定操作的结果
    private ChannelFuture future;

//    @PostConstruct
    public void startServer() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap,这个类封装了服务器端的网络配置，使得我们可以轻松地设置服务器参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer());

            // 绑定端口并开始接受进来的连接
            future = bootstrap.bind(12002).sync();

            // 等待服务器套接字关闭
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @PreDestroy
    public void stopServer() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    @Override
    @Async
    public void run(ApplicationArguments args) throws Exception {
        startServer();
    }
}
