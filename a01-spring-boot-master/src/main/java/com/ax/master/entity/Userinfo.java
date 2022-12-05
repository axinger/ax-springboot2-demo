package com.ax.master.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author axing
 */
// public class Userinfo implements UserDetails {

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userinfo implements Serializable {

    /**
     * 用户权限
     **/
    List<UserRole> authorities;
    private Integer id;
    private String token;
    private String password;
    private String userName;
    private int userType;
//
//    public int getUserType() {
//        return userType;
//    }
//
//    public void setUserType(int userType) {
//        this.userType = userType;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//
////        if (id > 0 && username != null && password != null) {
////            token = AxJwtUtil.createJWT(id.toString(),username,password,0);
////        }
//    }
//
//    public String getToken() {
//        token = AxJwtUtil.createJWT(id.toString(), username, password, 0);
//        return token;
//    }
//
////    public void setToken(String token) {
////        this.token = token;
////    }
//
//    public void setPassword(String password) {
//        this.password = password;
////        if (id > 0 && username != null && password != null) {
////            token = AxJwtUtil.createJWT(id.toString(),username,password,0);
////        }
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
////        if (id > 0 && username != null && password != null) {
////            token = AxJwtUtil.createJWT(id.toString(),username,password,0);
////        }
//    }
//
//    public void setAuthorities(List<UserRole> authorities) {
//        this.authorities = authorities;
//    }
//
//    //    /**
////     * 添加用户拥有的权限和角色
////     *
////     * @return
////     */
////    @Override
//    public Collection<UserRole> getAuthorities() {
//        return authorities;
//    }
//
//    //
////    @Override
//    public String getPassword() {
////        return new BCryptPasswordEncoder().encode(password); //返回加密的密码
//        return password; //直接返回密码
//    }
//
//    //
////    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    /**
//     * 账户是否过期
//     *
//     * @return
//     */
//    @Override
//    @JSONField(serialize = false)
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     * 是否禁用
//     *
//     * @return
//     */
//    @Override
//    @JSONField(serialize = false)
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    /**
//     * 密码是否过期
//     *
//     * @return
//     */
//    @Override
//    @JSONField(serialize = false)
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /**
//     * 是否启用
//     *
//     * @return
//     */
//    @Override
//    @JSONField(serialize = false)
//    public boolean isEnabled() {
//        return true;
//    }


}
