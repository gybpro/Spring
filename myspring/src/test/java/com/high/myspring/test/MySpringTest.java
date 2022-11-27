package com.high.myspring.test;

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
    }
}
