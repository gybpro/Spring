package com.high.spring.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Service
public class AccountService implements MyService {
    private final MyService userService;

    @Lazy
    public AccountService(MyService userService) {
        this.userService = userService;
        // 底层是JDK动态代理(有实现接口)和CGLib动态代理(无实现接口)
        System.out.println("@Lazy解决构造注入循环依赖问题");
    }
}
