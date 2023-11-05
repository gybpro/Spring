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
     * @param actNo
     * @return
     */
    Account selectByActNo(String actNo);

    /**
     * 更新账户
     *
     * @param act
     * @return
     */
    int update(Account act);

}
