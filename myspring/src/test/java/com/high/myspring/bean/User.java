package com.high.myspring.bean;

import com.high.myspringframework.beans.factory.annotation.Value;
import com.high.myspringframework.stereotype.Component;

/**
 * 用户类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Component("user")
public class User {
    @Value("张三")
    private String name;
    @Value("20")
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
