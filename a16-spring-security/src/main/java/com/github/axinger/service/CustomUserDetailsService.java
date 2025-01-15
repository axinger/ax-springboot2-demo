package com.github.axinger.service;

import com.github.axinger.dto.SysRole;
import com.github.axinger.dto.SysUser;
import com.github.axinger.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userMapper.findByUsername(username);
        log.info("查出了用户信息={}", user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        /// 添加角色
        List<GrantedAuthority> authorities = new ArrayList<>(user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList());
        /// 添加权限
        for (SysRole role : user.getRoles()) {
            authorities.addAll(role.getPermissions()
                    .stream()
                    .map(val -> new SimpleGrantedAuthority(val.getName()))
                    .toList());
        }
        // 返回 Spring Security 的 UserDetails 对象
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
