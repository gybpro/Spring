package com.high.spring.service;

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
     * @param fromActNo
     * @param toActNo
     * @param money
     */
    void transfer(String fromActNo, String toActNo, double money);

}
