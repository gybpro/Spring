package com.high.myspring.bean;

import com.high.myspringframework.beans.factory.annotation.Qualifier;
import com.high.myspringframework.beans.factory.annotation.Value;
import com.high.myspringframework.stereotype.Component;

/**
 * 丈夫类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Component("husband")
public class Husband {
    @Value("小贝")
    private String name;
    @Qualifier("wife")
    private Wife wife;

    public Husband() {
    }

    public Husband(String name, Wife wife) {
        this.name = name;
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "name='" + name + '\'' +
                // 这里没有.getName()会循环调用导致StackOverflow栈溢出异常
                ", wife=" + wife.getName() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
