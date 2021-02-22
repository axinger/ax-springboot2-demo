package com.ax.demo.module;

import com.ax.demo.service.ChatConstants;
import lombok.*;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserInfo implements Serializable{

	public static Map<String, UserInfo> map = new ConcurrentHashMap<>();
	private String phone;

}
