package com.high.spring.bean;

/**
 * 用户类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class User {
    private int age;

    public User() {
        System.out.println("User无参数构造方法执行了");
    }

    public User(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
