package com.high.myspring.bean;

import com.high.myspringframework.beans.factory.annotation.Qualifier;
import com.high.myspringframework.beans.factory.annotation.Value;
import com.high.myspringframework.stereotype.Component;

/**
 * 妻子类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Component("wife")
public class Wife {
    @Value("小花")
    private String name;
    @Qualifier("husband")
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
