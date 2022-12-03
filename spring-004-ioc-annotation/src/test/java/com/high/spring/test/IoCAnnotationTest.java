package com.high.spring.test;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class IoCAnnotationTest {
    @Test
    public void testSelectivityInstance() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }
}
