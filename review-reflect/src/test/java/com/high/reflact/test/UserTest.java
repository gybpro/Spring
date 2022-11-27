package com.high.reflact.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class UserTest {
    @Test
    public void testUser() throws Exception {
        /*
        已知有一个类，类名是：com.powernode.reflect.User
        该类中有String类型的name属性和int类型的age属性。
        另外你也知道该类的设计符合javabean规范。（也就是说属性私有化，对外提供setter和getter方法）
        你如何通过反射机制给User对象的name属性赋值zhangsan，给age属性赋值20岁。
         */
        String className = "com.high.reflect.bean.User";
        String propertyName0 = "name";
        String propertyName1 = "age";
        String name = "zhangsan";
        int age = 20;

        // 反射调用
        // 获取类
        Class<?> clazz = Class.forName(className);
        // 获取对象
        Object obj = clazz.getDeclaredConstructor().newInstance();
        // 获取方法名
        String methodName = "set" + propertyName0.toUpperCase().charAt(0) + propertyName0.substring(1);
        // 获取属性类型
        Class<?> fieldType = clazz.getDeclaredField(propertyName0).getType();
        // 根据方法名和参数类型获取方法
        Method method = clazz.getDeclaredMethod(methodName, fieldType);
        // 调用方法传递参数
        method.invoke(obj, name);

        methodName = "set" + propertyName1.toUpperCase().charAt(0) + propertyName1.substring(1);
        fieldType = clazz.getDeclaredField(propertyName1).getType();
        method = clazz.getDeclaredMethod(methodName, fieldType);
        method.invoke(obj, age);

        System.out.println(obj);
    }
}
