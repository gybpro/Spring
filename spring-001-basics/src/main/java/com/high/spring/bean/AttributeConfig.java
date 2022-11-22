package com.high.spring.bean;

import java.util.Properties;

/**
 * 属性配置类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class AttributeConfig {
    private Properties properties;

    public AttributeConfig() {
    }

    public AttributeConfig(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "AttributeConfig{" +
                "properties=" + properties +
                '}';
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
