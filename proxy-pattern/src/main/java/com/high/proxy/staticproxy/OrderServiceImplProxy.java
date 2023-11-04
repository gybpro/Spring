package com.high.proxy.staticproxy;

import com.high.proxy.bean.OrderService;

/**
 * 静态代理实现类
 *      优点：符合OCP开闭原则，耦合度低
 *      缺陷：一个接口就要编写一个代理类，会导致类爆炸，可读性、可维护低
 *      动态代理可以解决静态代理的缺陷
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImplProxy implements OrderService {
    private final OrderService orderService;

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
