package com.axing.demo.service;

import io.netty.channel.socket.nio.NioSocketChannel;

public interface MessageService {
    public void putConnection(String userId, String sectionId, NioSocketChannel channel);

    public void removeConnection(NioSocketChannel channel);
}
