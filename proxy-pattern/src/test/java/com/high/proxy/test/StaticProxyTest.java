package com.high.proxy.test;

import com.high.proxy.bean.OrderServiceImpl;
import com.high.proxy.staticproxy.OrderServiceImplProxy;
import org.junit.Test;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class StaticProxyTest {
    @Test
    public void testStaticProxy() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceImplProxy orderServiceImplProxy = new OrderServiceImplProxy(orderService);
        orderServiceImplProxy.generate();
        orderServiceImplProxy.modify();
        orderServiceImplProxy.detail();
    }
}
