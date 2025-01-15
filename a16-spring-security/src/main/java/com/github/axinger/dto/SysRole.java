package com.github.axinger.dto;

import lombok.Data;

import java.util.List;

@Data
public class SysRole {
    private Integer id;
    private String name; // 角色标识，例如 "ADMIN", "USER"
    private String description; // 角色描述，例如 "管理员角色"
    private List<SysPermission> permissions; // 角色权限集合
}
