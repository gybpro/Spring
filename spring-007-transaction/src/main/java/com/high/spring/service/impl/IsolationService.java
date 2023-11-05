package com.high.spring.service.impl;

import com.high.spring.domain.Account;
import com.high.spring.mapper.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 隔离服务
 *
 * @author programmer
 * @date 2023/11/05
 */
@Service("isolationService")
public class IsolationService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    // 当前事务可以读取到别的事务没有提交的数据。
    //@Transactional(isolation = Isolation.READ_UNCOMMITTED)
    // 对方事务提交之后的数据我才能读取到。
    @Transactional(isolation = Isolation.READ_COMMITTED)
    // 可重复读
    // @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void getByActNo(String actNo) {
        Account account = accountDao.selectByActNo(actNo);
        System.out.println("查询到的账户信息：" + account);

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account = accountDao.selectByActNo(actNo);
        System.out.println("查询到的账户信息：" + account);
    }

}
