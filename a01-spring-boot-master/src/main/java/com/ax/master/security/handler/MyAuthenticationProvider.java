// package com.ax.master.security.handler;
//
//
// import com.ax.master.entity.IpLog;
// import com.ax.master.entity.Userinfo;
// import com.ax.master.service.ILoginService;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Component;
//
// import java.util.Collection;
// import java.util.Date;
//
//@Slf4j
//@Component
/////AuthenticationProvider  和 UserDetailsService 任用一个
// public class MyAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    ILoginService loginService;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        System.out.println("登录顺序: 请求授权................");
//        String username = authentication.getName();// 这个获取表单输入中返回的用户名;
//        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码；
//
//        System.out.println("授权username = " + username+"password = " + password
//                +"authentication.getPrincipal() = " +  authentication.getPrincipal());
//
//        // 这里调用我们的自己写的获取用户的方法；
//        Userinfo userInfo = loginService.getByUserName(username);
//
//        log.info("获取用户 userInfo = "+ userInfo);
//
//        if (userInfo == null) {
//            throw new BadCredentialsException(" 用户名不存在");
//        }
//
//        if (!userInfo.getPassword().equals(password)) {
//            throw new BadCredentialsException(" 密码不正确");
//        }
//
//
//        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
//        // 构建返回的用户登录成功的token
//        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        // TODO Auto-generated method stub
//        // 这里直接改成retrun true;表示是支持这个执行
//        return true;
//    }
//}
