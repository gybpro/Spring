package com.high.spring.test;

import com.high.spring.config.SpringConfig;
import com.high.spring.domain.Account;
import com.high.spring.mapper.AccountDao;
import com.high.spring.service.AccountService;
import com.high.spring.service.impl.IsolationService;
import com.high.spring.service.impl.IsolationService2;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 账户测试
 *
 * @author programmer
 * @date 2023/11/05
 */
public class AccountTest {
    @Resource
    private AccountDao accountDao;

    @Test
    public void testTransfer() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try {
            accountService.transfer("act-001", "act-002", 10000);
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsolation1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        IsolationService isolationService = applicationContext.getBean("isolationService", IsolationService.class);
        isolationService.getByActNo("act-004");
    }

    @Test
    public void testIsolation2() throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        IsolationService2 isolationService2 = applicationContext.getBean("isolationService2", IsolationService2.class);
        Account act = new Account("act-004", 1000.0);
        isolationService2.save(act);
    }

    @Test
    public void testPropagation() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 获取1号service对象
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        Account act = new Account("act-003", 1000.0);
        accountService.save(act);
    }

    @Test
    public void testSpringTx() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try {
            accountService.transfer("act-001", "act-002", 10000);
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnnotation() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try {
            accountService.transfer("act-001", "act-002", 10000);
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
