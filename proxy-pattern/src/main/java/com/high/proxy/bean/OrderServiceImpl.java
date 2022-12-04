package com.high.proxy.bean;

/**
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void generate() {
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生成订单");
    }

    @Override
    public void modify() {
        try {
            Thread.sleep(1314);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("修改订单");
    }

    @Override
    public void detail() {
        try {
            Thread.sleep(234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单详情");
    }
}
