package com.github.axinger.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmpty(message = "username不能为空")
    private String username;
    @NotEmpty(message = "password不能为空")
    private String password;
}
