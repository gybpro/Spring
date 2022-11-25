package com.high.spring.test;

import com.high.spring.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean生命周期测试
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class BeanLifecycleTest {
    @Test
    public void testBeanLifecycle() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("第八步：使用Bean，" + user);
        applicationContext.close();
    }

    @Test
    public void testBeanRegister() {
        // 将自己new的对象交给Spring管理
        User user = new User();
        System.out.println(user);

        // 创建默认可列举Bean工厂，DefaultListableBeanFactory是BeanFactory的默认实现
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        // 注册为单例Bean
        defaultListableBeanFactory.registerSingleton("user", user);
        User user1 = defaultListableBeanFactory.getBean("user", User.class);
        System.out.println(user1);
    }
}
