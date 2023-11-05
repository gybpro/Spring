package com.high.spring.service;

import com.high.spring.domain.Account;

/**
 * 账户服务
 *
 * @author programmer
 * @date 2023/11/05
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param fromActNo 转出账户
     * @param toActNo   转入账户
     * @param money     转账金额
     */
    void transfer(String fromActNo, String toActNo, double money);

    /**
     * 保存账户信息
     *
     * @param act 账户
     */
    void save(Account act);

}
