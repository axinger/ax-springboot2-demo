package com.axing.service;


import com.axing.domain.UmsResource;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 后台用于管理Service
 * Created by macro on 2020/10/15.
 */
public interface UmsAdminService extends UserDetailsService {

    /**
     * 获取所以权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
