package com.high.myspring.service;

import com.high.myspring.dao.UserDao;

/**
 * 用户相关业务类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class UserService {
    private UserDao userDao;

    public UserService() {
    }

    public void save() {
        userDao.insert();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
