package com.high.spring.bean;

import java.util.Date;

/**
 * 人类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class People {
    private Date birth;

    public People() {
    }

    public People(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "People{" +
                "birth=" + birth +
                '}';
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
