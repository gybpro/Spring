package com.high.proxy.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class JDKDynamicProxyUtil {
    private JDKDynamicProxyUtil() {
    }

    public static Object newProxy(Object target, InvocationHandler handler) throws Exception {
        Class<?> clazz = target.getClass();
        /*
        newProxyInstance()的三个参数：
            第一个参数：类加载器(JDK动态代理要求目标类对象和代理类对象必须为同一个类加载器加载)
            第二个参数：目标类和代理类的公共接口(接口字节码数组)
            第三个参数：调用处理程序(发生调用事件时调用的事件句柄方法/回调方法)
         */
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}
