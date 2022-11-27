package com.high.myspring.test;

import com.high.myspring.bean.Husband;
import com.high.myspring.bean.Wife;
import com.high.myspring.service.UserService;
import com.high.myspringframework.context.ApplicationContext;
import com.high.myspringframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * MySpring测试类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class MySpringTest {
    @Test
    public void testMySpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myspring.xml");
        Object user = applicationContext.getBean("user");
        System.out.println(user);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
        Object husband = applicationContext.getBean("husband");
        Object wife = applicationContext.getBean("wife");
        System.out.println(husband);
        System.out.println(wife);
    }
}
