package com.high.proxy.test;

import com.high.proxy.bean.OrderService;
import com.high.proxy.bean.OrderServiceImpl;
import com.high.proxy.jdkdynamicproxy.TimeInvocationHandler;
import com.high.proxy.utils.JDKDynamicProxyUtils;
import org.junit.Test;

/**
 * JDK动态代理测试类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class JDKDynamicProxyTest {
    @Test
    public void testJDKDynamicProxy() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderService proxy = JDKDynamicProxyUtils.newProxy(orderService, new TimeInvocationHandler(orderService));
        proxy.generate();
        proxy.modify();
        proxy.detail();
    }
}
