package com.ax.shiro.config;

import org.apache.shiro.authc.UsernamePasswordToken;

public class JwtToken extends UsernamePasswordToken {

    public JwtToken() {

        final String username = this.getUsername();


    }

}
