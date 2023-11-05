package com.high.spring.mapper;

import com.high.spring.domain.Account;

/**
 * 账号 DAO
 *
 * @author programmer
 * @date 2023/11/05
 */
public interface AccountDao {

    /**
     * 根据账号查询余额
     *
     * @param actNo 账户号
     * @return 账户
     */
    Account selectByActNo(String actNo);

    /**
     * 更新账户
     *
     * @param act 账户
     * @return int
     */
    int update(Account act);

    /**
     * 插入
     *
     * @param act 账户
     * @return int
     */
    int insert(Account act);

}
