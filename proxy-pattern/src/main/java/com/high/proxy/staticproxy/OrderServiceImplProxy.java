package com.high.proxy.staticproxy;

import com.high.proxy.bean.OrderService;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImplProxy implements OrderService {
    private OrderService orderService;

    public OrderServiceImplProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void generate() {
        long start = System.currentTimeMillis();
        orderService.generate();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }

    @Override
    public void modify() {
        long start = System.currentTimeMillis();
        orderService.modify();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }

    @Override
    public void detail() {
        long start = System.currentTimeMillis();
        orderService.detail();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "执行累计耗时" + (end - start));
    }
}
