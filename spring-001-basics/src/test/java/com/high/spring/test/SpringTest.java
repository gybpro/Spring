package com.high.spring.test;

import com.high.spring.bean.User;
import com.high.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class SpringTest {
    @Test
    public void testCreateBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        /* Object userBean = applicationContext.getBean("userBean");
        User userBean = (User) applicationContext.getBean("userBean");*/
        // 在Spring容器启动，配置文件加载的时候Spring就已经创建好了对象
        System.out.println("还没有getBean");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void testDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.create();
    }
}
