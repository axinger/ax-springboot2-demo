package com.ax.db.entity.db1;


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
