package com.high.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Bean后置处理器
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class LogBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第四步：Bean后置处理器中的初始化前处理");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第七步：Bean后置处理器中的初始化后处理");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
