package com.ax.master.dto;

import com.ax.master.entity.valid.PasswordGroup;
import com.ax.master.entity.valid.UsernameGroup;
import jakarta.validation.constraints.NotNull;

/**
 * @author axing
 */
public class LoginDto {

    @NotNull(message = "姓名不能空", groups = UsernameGroup.class)
    private String username;

    @NotNull(message = "密码不能空", groups = PasswordGroup.class)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String sb = "{" + "\"username\":\"" +
                username + '\"' +
                ",\"password\":\"" +
                password + '\"' +
                '}';
        return sb;
    }
}
