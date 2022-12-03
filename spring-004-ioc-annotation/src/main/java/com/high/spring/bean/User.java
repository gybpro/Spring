package com.high.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Component
public class User {
    @Qualifier
    private String name;

    public User() {
        System.out.println("User实例化@Component生效");
    }
}
