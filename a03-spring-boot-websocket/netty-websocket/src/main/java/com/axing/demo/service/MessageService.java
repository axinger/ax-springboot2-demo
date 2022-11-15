package com.axing.demo.service;

import io.netty.channel.socket.nio.NioSocketChannel;

public interface MessageService {
    void putConnection(String userId, String sectionId, NioSocketChannel channel);

    void removeConnection(NioSocketChannel channel);
}
