package com.ax.master.entity;

//import org.springframework.security.core.GrantedAuthority;

//public class UserRole implements GrantedAuthority {
public class UserRole {
    private Long id;
    private String role;
    private transient Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    @Override
//    @JSONField(serialize = false)
//    public String getAuthority() {
//        return role;
//    }

    @Override
    public String toString() {
        String sb = "{" + "\"id\":" +
                id +
                ",\"role\":\"" +
                role + '\"' +
                '}';
        return sb;
    }
}
