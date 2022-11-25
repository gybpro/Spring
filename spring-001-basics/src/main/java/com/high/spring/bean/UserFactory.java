package com.high.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 用户工厂Bean
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class UserFactory implements FactoryBean<User> {
    // 简单(静态)工厂方法
    /* public static User getUser() {
        return new User();
    } */

    // 工厂方法
    /* public User getUser() {
        return new User();
    } */

    // 实现FactoryBean的方式实例化Bean
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
