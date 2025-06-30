package com.pig.server.service;

import com.pig.api.User;
import com.pig.api.UserService;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Long id) {
        return User.builder()
            .id(++id)
            .name("张三")
            .build();
    }
}
