package com.github.axinger.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.axinger.domain.SysUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysUserMapperTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void test1() {
        List<SysUserEntity> sysUserEntities = sysUserMapper.selectList(null);
        System.out.println("sysUserEntities = " + sysUserEntities);
    }

    @Test
    void test2() {
        SysUserEntity sysUser = SysUserEntity.builder()
                .username("jim")
                .email("123@qq.com")
                .phone("189000")
                .age(10)
                .build();
        sysUserMapper.insert(sysUser);
    }

    @Test
    void test3() {
        /// username和email均有唯一索引，任意一个重复均,就会失败
        /// Duplicate entry 'jim' for key 'sys_user.username' 键 'sys_user.username' 的重复条目 'jim'
        SysUserEntity sysUser = SysUserEntity.builder()
                .username("jim")
                .email("1234@qq.com")
                .phone("189000")
                .age(10)
                .build();
        sysUserMapper.insert(sysUser);
    }


    @Test
    void test11() {

        /// username和email均有唯一索引，任意一个重复均会触发更新,
        List<SysUserEntity> users = new ArrayList<>();
        users.add(SysUserEntity.builder().username("jim").email("123@qq.com").phone("189000").age(13).build());
//        users.add(SysUserEntity.builder().username("jim").email("123@qq.com").age(13).build());

        sysUserMapper.batchInsertOrUpdate(users);
    }

    @Test
    void test21() {
        SysUserEntity sysUser = sysUserMapper.getUserByMap(Map.of("username", "jim"));
        System.out.println("sysUser = " + sysUser);

        Map<String, Object> map = new HashMap<>();
        map.put("username", null);
        SysUserEntity sysUser2 = sysUserMapper.getUserByMap(map);
        System.out.println("sysUser2 = " + sysUser2);
    }

    @Test
    void test22() {
        SysUserEntity sysUser = sysUserMapper.getUserByIdAndName("jim");
        System.out.println("sysUser = " + sysUser);

        SysUserEntity sysUser2 = sysUserMapper.getUserByIdAndName(null);
        System.out.println("sysUser2 = " + sysUser2);
    }

    @Test
    void test23() {
        SysUserEntity sysUser1 = SysUserEntity.builder()
                .username("jim")
                .build();
        SysUserEntity sysUser = sysUserMapper.getUserByQuery(sysUser1);
        System.out.println("sysUser = " + sysUser);

        SysUserEntity sysUser21 = SysUserEntity.builder()
                .build();
        SysUserEntity sysUser2 = sysUserMapper.getUserByQuery(sysUser21);
        System.out.println("sysUser2 = " + sysUser2);
    }

    @Test
    void test31() {
        int i = sysUserMapper.deleteById(1L);

        List<SysUserEntity> list = sysUserMapper.selectList(null);
        System.out.println("list = " + list);

    }

    @Test
    void test32() {
        Wrapper<SysUserEntity> wrapper = Wrappers.<SysUserEntity>lambdaQuery()
                .eq(SysUserEntity::getDeleted, 1);

//        LambdaQueryWrapper<SysUserEntity> wrapper2 = new LambdaQueryWrapper<>();
//        wrapper2.eq(SysUserEntity::getDeleted, 1);


        List<SysUserEntity> list = sysUserMapper.selectList(wrapper);
        System.out.println("list = " + list);

    }
}
