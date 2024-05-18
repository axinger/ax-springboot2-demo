package com.github.axinger.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    /**
     * 获取当前的OAuth2 Client对象实例{@link OAuth2AuthorizedClient}
     * 和当前认证对象实例{@link Authentication}
     *
     * @param giteeOauth2client the gitee Oauth2 client
     * @return the map
     */
    @GetMapping("/gitee")
    public Map<String, Object> foo(@RegisteredOAuth2AuthorizedClient
                                   OAuth2AuthorizedClient giteeOauth2client) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> map = new HashMap<>(2);
        map.put("giteeOauth2client", giteeOauth2client);
        map.put("authentication", authentication);
        return map;
    }


    /**
     * 默认登录成功跳转页为 /  防止404状态
     *
     * @return the map
     */
    @GetMapping("/")
    public Map<String, String> index() {
        return Collections.singletonMap("msg", "oauth2Login success!");
    }


    /**
     * 获得当前用户的授权信息
     *
     * @return
     */
    @GetMapping("/info")
    public Map<String, Object> info() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> map = new HashMap<>(2);
        map.put("authentication", authentication);
        return map;

    }

    @RequestMapping("/hello")
    public String emailaa(Principal principal) {
        return "Hello1 " + principal.getName();
    }
}
