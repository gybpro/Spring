package com.high.spring.service.impl;

import com.high.spring.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 目标类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public void generate() {
        try {
            Thread.sleep(123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生成订单");
    }

    @Override
    public void modify() {
        try {
            Thread.sleep(135);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("修改订单");
    }

    @Override
    public void detail() {
        try {
            Thread.sleep(246);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单详情");
        throw new RuntimeException("运行时异常。。。");
    }
}
