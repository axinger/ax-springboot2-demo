package com.github.axinger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Collection<String> authorities;
}
