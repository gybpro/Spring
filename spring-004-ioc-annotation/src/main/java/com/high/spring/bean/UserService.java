package com.high.spring.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserService implements MyService {
    private final MyService accountService;

    public UserService(MyService accountService) {
        this.accountService = accountService;
        System.out.println("UserService实例化@Service生效");
    }
}
