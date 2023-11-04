package com.high.proxy.test;

import com.high.proxy.bean.OrderService;
import com.high.proxy.bean.OrderServiceImpl;
import com.high.proxy.cglibdynamicproxy.TimeMethodInterceptor;
import com.high.proxy.utils.CgLibDynamicProxyUtils;
import org.junit.Test;

/**
 * CGLIB动态代理测试类
 *
 * @author programmer
 * @version 1.0
 * @since 1.0
 */
public class CgLibDynamicProxyTest {
    @Test
    public void testCgLibDynamicProxy() throws Exception {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderService proxy = CgLibDynamicProxyUtils.newProxy(orderService, new TimeMethodInterceptor());
        proxy.generate();
        proxy.modify();
        proxy.detail();
    }
}
