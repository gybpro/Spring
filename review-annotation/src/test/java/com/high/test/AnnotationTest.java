package com.high.test;

import com.high.annotation.MyAnnotation;
import com.high.bean.User;
import org.junit.Test;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class AnnotationTest {
    @Test
    public void testAnnotation() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("com.high.bean.User");
        MyAnnotation annotation = userClass.getAnnotation(MyAnnotation.class);
        String value = annotation.value();
        System.out.println(value);
    }
}
