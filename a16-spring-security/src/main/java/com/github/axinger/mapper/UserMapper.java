package com.github.axinger.mapper;

import com.github.axinger.dto.SysPermission;
import com.github.axinger.dto.SysRole;
import com.github.axinger.dto.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查询用户信息
    @Select("SELECT id, username, password FROM users WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", column = "id",
                    many = @Many(select = "com.github.axinger.mapper.UserMapper.findRolesByUserId"))
    })
    SysUser findByUsername(String username);

    // 查询用户的角色信息
    @Select("SELECT r.id, r.name, r.description FROM roles r " +
            "JOIN user_roles ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "permissions", column = "id",
                    many = @Many(select = "com.github.axinger.mapper.UserMapper.findPermissionsByRoleId"))
    })
    List<SysRole> findRolesByUserId(Integer userId);

    // 查询角色的权限信息
    @Select("SELECT p.id, p.name, p.description FROM permissions p " +
            "JOIN role_permissions rp ON p.id = rp.permission_id " +
            "WHERE rp.role_id = #{roleId}")
    List<SysPermission> findPermissionsByRoleId(Integer roleId);
}
