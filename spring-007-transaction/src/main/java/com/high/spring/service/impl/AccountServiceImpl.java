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
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Resource(name = "accountService2")
    private AccountService accountService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transfer(String fromActNo, String toActNo, double money) {
        // 查询账户余额是否充足
        Account fromAct = accountDao.selectByActNo(fromActNo);
        if (fromAct.getBalance() < money) {
            throw new RuntimeException("账户余额不足");
        }
        // 余额充足，开始转账
        Account toAct = accountDao.selectByActNo(toActNo);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        int count = accountDao.update(fromAct);

        // 模拟异常
        /* String s = null;
        s.toString(); */

        count += accountDao.update(toAct);
        if (count != 2) {
            throw new RuntimeException("转账失败，请联系银行");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Account act) {
        // 这里调用dao的insert方法。
        accountDao.insert(act); // 保存act-003账户

        // 创建账户对象
        Account act2 = new Account("act-004", 1000.0);
        try {
            accountService.save(act2); // 保存act-004账户
        } catch (Exception e) {

        }
        // 继续往后进行当前1号事务自己的事儿。
    }

}
