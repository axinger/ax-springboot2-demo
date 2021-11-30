package com.ax.mongo.service;

import com.ax.mongo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Silence on 2017/4/23.
 */
@Service
@Slf4j
public class RelationService {

    private final ConcurrentHashMap<String, List<String>> relations;
    @Autowired
    private UserService userService;

    public RelationService() {
        relations = new ConcurrentHashMap<>();
    }

    // 添加好友
    public boolean addFriend(String username, String friendName) {
        User user = userService.getByUsername(friendName);
        if (user == null) {
            log.info("用户不存在：" + friendName);
            return false;
        }
        if (username.equals(friendName)) {
            log.info("不能添加自己为好友：" + friendName);
            return false;
        }
        this.establishRelation(username, friendName);
        this.establishRelation(friendName, username);
        return true;

    }

    private void establishRelation(String username, String friendName) {
        List<String> friends = relations.get(username);
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(friendName);
        relations.put(username, friends);
    }

    // 好友列表
    public List<User> listFriends(String username) {
        List<User> users = new ArrayList<>();
        List<String> friends = relations.get(username);
        if (friends != null) {
            for (String friend : friends) {
                User user = userService.getByUsername(friend);
                users.add(user);
            }
        }
        return users;
    }

}
