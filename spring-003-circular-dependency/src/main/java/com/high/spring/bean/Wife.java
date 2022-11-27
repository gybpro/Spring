package com.high.spring.bean;

/**
 * 妻子类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class Wife {
    private String name;
    private Husband husband;

    public Wife() {
    }

    public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }

    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                // 这里没有.getName()会循环调用导致StackOverflow栈溢出异常
                ", husband=" + husband.getName() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }
}
