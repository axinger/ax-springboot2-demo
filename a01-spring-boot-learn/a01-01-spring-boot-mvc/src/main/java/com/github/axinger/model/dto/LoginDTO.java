package com.github.axinger.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {

    @NotEmpty(message = "username不能为空")
    private String username;

    @NotEmpty(message = "password不能为空")
    private String password;
}
