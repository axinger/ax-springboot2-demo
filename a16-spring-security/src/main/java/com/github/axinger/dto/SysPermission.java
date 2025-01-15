package com.github.axinger.dto;

import lombok.Data;

@Data
public class SysPermission {
    private Integer id;
    private String name; // 权限标识，例如 "USER_READ", "USER_WRITE"
    private String description; // 权限描述，例如 "用户读取权限"
}
