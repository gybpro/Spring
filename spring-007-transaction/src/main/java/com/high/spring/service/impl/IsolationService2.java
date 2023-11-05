package com.high.spring.service.impl;

import com.high.spring.domain.Account;
import com.high.spring.mapper.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 隔离服务2
 *
 * @author programmer
 * @date 2023/11/05
 */
@Service("isolationService2")
public class IsolationService2 {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    // @Transactional(timeout = 10) // 设置事务超时时间为10秒
    // 坑：事务超时时间是所有DML执行完的超时时间，如果所有DML语句已经执行完了，那么之后的代码超时也不会回滚
    // @Transactional(readOnly = true) // 只读事务会优化提高select的执行效率
    // @Transactional(rollbackFor = RuntimeException.class) // 设置要回滚的异常RuntimeException
    @Transactional(noRollbackFor = NullPointerException.class) // 设置不回滚的异常
    public void save(Account act) throws IOException {
        // 睡眠一会
        /*try {
            Thread.sleep(1000 * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        accountDao.insert(act);
        // accountDao.update(act);

        // 睡眠一会
        /* try {
            Thread.sleep(1000 * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */

        // 可以在这里写一个无关紧要的DML语句

        // 模拟异常
        if (1 == 1) {
            // throw new IOException();
            // throw new RuntimeException();
            throw new NullPointerException();
        }
    }

}
