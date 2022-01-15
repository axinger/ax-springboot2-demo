// +----------------------------------------------------------------------
// | JavaWeb_Layui混编版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2021 上海JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <javaweb520@gmail.com>
// +----------------------------------------------------------------------
// | 免责声明:
// | 本软件框架禁止任何单位和个人用于任何违法、侵害他人合法利益等恶意的行为，禁止用于任何违
// | 反我国法律法规的一切平台研发，任何单位和个人使用本软件框架用于产品研发而产生的任何意外
// |  、疏忽、合约毁坏、诽谤、版权或知识产权侵犯及其造成的损失 (包括但不限于直接、间接、附带
// | 或衍生的损失等)，本团队不承担任何法律责任。本软件框架已申请版权保护，任何组织、单位和个
// | 人不得有任何侵犯我们的版权的行为(包括但不限于分享、转售、恶意传播，开源等等)，否则产生
// | 的一切后果和损失由侵权者全部承担，本软件框架只能用于公司和个人内部的法律所允许的合法合
// | 规的软件产品研发，详细声明内容请阅读《框架免责声明》附件；
// +----------------------------------------------------------------------

package com.ax.mall.shiro;


import com.ax.mall.entity.UserRole;
import com.ax.mall.entity.Userinfo;
import com.ax.mall.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private ILoginService loginService;

    /**
     * 授权权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.warn("开始执行授权操作.......");
        Userinfo user = (Userinfo) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 权限设置
        if (user.getId().equals(1)) {
            // 超级管理员
            simpleAuthorizationInfo.addStringPermission("*:*:*");
        } else {
            // 普通管理员及其他

            // 设置角色权限
            List<UserRole> roleList = user.getAuthorities();
            Set<String> roles = new HashSet<>();
            roleList.forEach(item -> {
                roles.add(item.getId().toString());
            });
            simpleAuthorizationInfo.addRoles(roles);

            // 设置菜单权限
            Set<String> permissions = new HashSet<>();
            permissions.add("a");
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        String password = new String(token.getPassword());

        Userinfo user = loginService.getByUserName(username);
        if (user == null) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}");
            throw new AuthenticationException("进行登录验证..验证未通过");
        }

        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                authenticationToken.getPrincipal(),                                  //用户名
                user.getPassword(),                    //密码
                ByteSource.Util.bytes(""),      //设置盐值
                getName()
        );
        return authenticationInfo;
    }
}
