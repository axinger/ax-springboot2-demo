package com.github.axinger.custom;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.common.engine.impl.interceptor.CommandExecutor;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;

import java.util.*;

public class CustomUserQueryImpl extends UserQueryImpl {

//    public CustomUserQueryImpl() {
//    }
//
//    public CustomUserQueryImpl(CommandContext commandContext) {
//        super(commandContext);
//    }
//
//    public CustomUserQueryImpl(CommandExecutor commandExecutor) {
//        super(commandExecutor);
//    }
//
//    @Override
//    public long executeCount(CommandContext commandContext) {
//        return this.executeList(commandContext).size();
//    }
//
//    @Override
//    public List<User> executeList(CommandContext commandContext) {
//        return queryUsers();
//    }

//    private static final Map<String, String> USERS = new HashMap<>();
//
//    static {
//        USERS.put("1", "user1");
//        USERS.put("2", "user2");
//        USERS.put("3", "user3");
//    }
//
//    private List<User> queryUsers() {
//        List<User> result = new ArrayList<>();
//        System.out.println("this.getId() = " + this.getId());
//        System.out.println("this.getIds() = " + this.getIds());
//        if (this.getId() != null) {
//            Optional.ofNullable(USERS.get(this.getId())).map(this::buildUser).ifPresent(result::add);
//        } else if (this.getIds() != null) {
//            result.addAll(USERS.entrySet()
//                    .stream()
//                    .filter(entry -> this.getIds().contains(entry.getKey()))
//                    .map(Map.Entry::getValue)
//                    .map(this::buildUser)
//                    .toList());
//        }
//        return result;
//    }
//
//    private UserEntityImpl buildUser(String username) {
//        UserEntityImpl userEntity = new UserEntityImpl();
//        userEntity.setDisplayName(username);
//        return userEntity;
//    }
//
//    @Override
//    public List<User> list() {
//        List<User> result = new ArrayList<>();
//        System.out.println("this.getId() = " + this.getId());
//        System.out.println("this.getIds() = " + this.getIds());
//
//        // 在这里添加自定义查询逻辑
//        // 例如，你可以添加额外的过滤条件，或改变排序方式
//        return super.list();
//    }
}
