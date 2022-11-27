package com.high.dev.service;

import com.high.dev.dao.UserDao;

/**
 * 用户相关业务类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void service() {
        System.out.println(userDao);
    }
}
