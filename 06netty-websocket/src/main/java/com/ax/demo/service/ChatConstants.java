package com.ax.demo.service;

import com.ax.demo.module.UserInfo;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatConstants {
    public static final AttributeKey<String> CHANNEL_TOKEN_KEY = AttributeKey.valueOf("netty.channel.token");  
	/**用来保存当前在线人员*/
	public static Map<String, UserInfo> onlines = new ConcurrentHashMap<>();
	
	public static void addOnlines(String sessionId, UserInfo val) {
		onlines.putIfAbsent(sessionId, val);
	}
	
	public static void removeOnlines(String sessionId) {
		if(StringUtils.isNotBlank(sessionId) && onlines.containsKey(sessionId)){
			onlines.remove(sessionId);
		}
	}


}
