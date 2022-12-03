package com.high.spring.bean;

import org.springframework.stereotype.Controller;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Controller
public class UserController {
    public UserController() {
        System.out.println("UserController实例化@Controller生效");
    }
}
