package com.high.spring.bean;

import org.springframework.stereotype.Repository;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Repository
public class UserDAO {
    public UserDAO() {
        System.out.println("UserDAO实例化@Repository生效");
    }
}
