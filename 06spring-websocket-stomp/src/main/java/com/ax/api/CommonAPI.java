package com.ax.api;

import javax.servlet.http.HttpServletRequest;

import com.ax.config.sercurity.UserPrincipal;
import com.ax.mongo.model.User;
import com.ax.mongo.service.RelationService;
import com.ax.mongo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用 API Created by Silence on 2017/4/22.
 */
@RestController
@RequestMapping("/api/common")
@Slf4j
public class CommonAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private RelationService relationService;

	@PostMapping(value = "/register")
	public boolean register(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		User user = new User(username, password, nickname);
		// TODO 参数校验
		log.info("注册user = "+user.toString());
		return userService.addUser(user);
	}

	@PostMapping("/add")
	public boolean add(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String friend) {
		return relationService.addFriend(userPrincipal.getUsername(), friend);
	}

}
