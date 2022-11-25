package com.high.spring.bean;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工厂
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class DateFactory implements FactoryBean<Date> {
    private String dateStr;

    private SimpleDateFormat simpleDateFormat;

    public DateFactory(String dateStr) {
        this.dateStr = dateStr;
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Date getObject() throws Exception {
        return simpleDateFormat.parse(dateStr);
    }

    @Override
    public Class<?> getObjectType() {
        return Date.class;
    }
}
