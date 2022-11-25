package com.high.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 用户类
 * Bean的生命周期有十步：
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        InitializingBean, DisposableBean {
    private String name;

    public User() {
        System.out.println("第一步：实例化Bean");
    }

    public void setName(String name) {
        System.out.println("第二步：Bean属性赋值");
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("第三步：进入Bean后置处理器之前，意识到自己的name是" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("第三步：进入Bean后置处理器之前，意识到自己的classLoader是" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第三步：进入Bean后置处理器之前，意识到自己的beanFactory是" + beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第五步：在Bean后置处理器完成初始化前处理之后");
    }

    public void initUser() {
        System.out.println("第六步：Bean的初始化");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第九步：Bean被抛弃前");
    }

    public void destroyUser() {
        System.out.println("第十步：Bean销毁前");
    }
}
