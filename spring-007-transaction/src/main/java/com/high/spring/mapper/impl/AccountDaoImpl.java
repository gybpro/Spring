package com.high.spring.mapper.impl;

import com.high.spring.domain.Account;
import com.high.spring.mapper.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 账户 DAO 实现
 *
 * @author programmer
 * @date 2023/11/05
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectByActNo(String actNo) {
        String sql = "select act_no, balance from t_act where act_no = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), actNo);
    }

    @Override
    public int update(Account act) {
        String sql = "update t_act set balance = ? where act_no = ?";
        return jdbcTemplate.update(sql, act.getBalance(), act.getActNo());
    }

    @Override
    public int insert(Account act) {
        String sql = "insert into t_act(act_no, balance) values(?,?)";
        return jdbcTemplate.update(sql, act.getActNo(), act.getBalance());
    }

}
