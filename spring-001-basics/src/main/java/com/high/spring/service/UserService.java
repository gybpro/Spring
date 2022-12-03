package com.high.spring.service;

import com.high.spring.mapper.UserMapper;
import org.springframework.context.annotation.Configuration;

/**
 * 用户服务
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class UserService {
    private UserMapper userMapper;

    public UserService() {
    }

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void create() {
        userMapper.insert();
    }
}
