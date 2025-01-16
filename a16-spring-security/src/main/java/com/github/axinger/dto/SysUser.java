package com.github.axinger.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SysUser implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private List<SysRole> roles; // 用户角色集合


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /// 添加角色
        List<GrantedAuthority> authorities = new ArrayList<>(roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList());
        /// 添加权限
        for (SysRole role : roles) {
            authorities.addAll(role.getPermissions()
                    .stream()
                    .map(val -> new SimpleGrantedAuthority(val.getName()))
                    .toList());
        }
//        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities.toString());
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /*
     * 表示判断账户是否过期
     * */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /*
     * 表示判断账户是否被锁定
     * */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /*
     * 表示凭证{密码}是否过期
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /*
     * 是否可用
     * */
    @Override
    public boolean isEnabled() {
        return false;
    }
}


