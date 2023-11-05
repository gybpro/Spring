package com.high.spring.test;

import com.high.spring.config.SpringConfig;
import com.high.spring.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringAOP测试
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class SpringAOPTest {

    @Test
    public void testAnnotation() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
        orderService.modify();
        orderService.detail();
    }

    @Test
    public void testXML() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
        orderService.modify();
        orderService.detail();
    }

}
