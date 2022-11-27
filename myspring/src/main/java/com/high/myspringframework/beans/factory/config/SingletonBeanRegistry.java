package com.high.myspringframework.beans.factory.config;

/**
 * 单例Bean注册器接口
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
