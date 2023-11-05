package com.high.spring.service.impl;

import com.high.spring.domain.Account;
import com.high.spring.mapper.AccountDao;
import com.high.spring.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户服务
 *
 * @author programmer
 * @date 2023/11/05
 */
@Service("accountService2")
public class AccountServiceImpl2 implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String fromActNo, String toActNo, double money) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Account act) {
        accountDao.insert(act);
        // 模拟异常
        String s = null;
        s.toString();

        // 事儿没有处理完，这个大括号当中的后续也许还有其他的DML语句。
    }

}
