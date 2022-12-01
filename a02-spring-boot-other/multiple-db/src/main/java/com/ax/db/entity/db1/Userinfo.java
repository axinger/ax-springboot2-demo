package com.ax.db.entity.db1;


import java.util.List;

/**
 * @author axing
 */
public class Userinfo {


    /**
     * 用户权限
     **/
    List<UserRole> authorities;
    private Long id;
    private String token;
    private String password;
    private String username;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserRole> authorities) {
        this.authorities = authorities;
    }


    @Override
    public String toString() {
        String sb = "{" + "\"id\":" +
                id +
                ",\"token\":\"" +
                token + '\"' +
                ",\"password\":\"" +
                password + '\"' +
                ",\"username\":\"" +
                username + '\"' +
                ",\"authorities\":" +
                authorities +
                '}';
        return sb;
    }
}
